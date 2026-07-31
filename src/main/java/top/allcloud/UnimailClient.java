package top.allcloud;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UnimailClient {
    private String host;
    private String key;
    private String lang = "zh";
    private List<String> supportLang = new ArrayList<String>() {{
        add("zh");
        add("en");
        add("id");
        add("vi");
        add("th");
        add("gu");
    }};

    public UnimailClient(String key) {
        this.host = "https://uniapi.allcloud.top/unimail";
        this.key = key;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean setLanguage(String lang) {
        if (!this.supportLang.contains(lang)) {
            return false;
        }
        this.lang = lang;
        return true;
    }


    public UniResponse checkConnection() {
        HttpResponse<JsonNode> response = Unirest.post(this.host + "/v2/checkConnection")
                .header("Content-Type", "application/json")
                .header("authorization", this.key)
                .header("accept-language", this.lang)
                .body("{}")
                .asJson();
        UniResponse result = new UniResponse(response);
        return result;
    }

    /**
     * 发送邮件
     *
     * @param req 请求体
     * @return UniResponse
     */
    public UniResponse sendEmail(UnimailReq req) {
        if (req == null || req.receivers == null || req.receivers.isEmpty()) {
            return this.errorResponse(400, "receivers is required");
        }
        if (req.subject == null || req.subject.trim().isEmpty()) {
            return this.errorResponse(400, "subject is required");
        }
        if (req.txtContent == null && req.htmlContent == null) {
            return this.errorResponse(400, "txtContent or htmlContent is required");
        }

        MultipartBody body = Unirest.post(this.host + "/v2/sendEmail")
                .header("accept-language", this.lang)
                .field("authorization", this.key)
                .field("receiver", String.join(";", req.receivers))
                .field("subject", req.subject);
        if (req.route != null && !req.route.trim().isEmpty()) {
            body.field("route", req.route.trim());
        }
        if (req.from != null && !req.from.trim().isEmpty()) {
            body.field("from", req.from.trim());
        }
        if (req.cc != null && !req.cc.trim().isEmpty()) {
            body.field("cc", req.cc.trim());
        }
        if (req.bcc != null && !req.bcc.trim().isEmpty()) {
            body.field("bcc", req.bcc.trim());
        }
        if (req.txtContent != null && !req.txtContent.trim().isEmpty()) {
            body.field("txtContent", req.txtContent.trim());
        }
        if (req.htmlContent != null && !req.htmlContent.trim().isEmpty()) {
            body.field("htmlContent", req.htmlContent.trim());
        }

        List<InputStream> streamsToClose = new ArrayList<>();
        for (int i = 0; i < req.attachments.size(); i++) {
            EmailAttachment attachment = req.attachments.get(i);
            if (attachment == null) {
                continue;
            }
            String attachmentName = attachment.name == null || attachment.name.trim().isEmpty()
                    ? String.format("attachment-%d", i)
                    : attachment.name;
            body.field(String.format("attachments[%d].name", i), attachmentName);
            if (attachment.fileAttachment != null) {
                body.field(String.format("attachments[%d].fileAttachment", i), attachment.fileAttachment, attachmentName);
                streamsToClose.add(attachment.fileAttachment);
            } else if (attachment.urlAttachment != null && !attachment.urlAttachment.trim().isEmpty()) {
                body.field(String.format("attachments[%d].urlAttachment", i), attachment.urlAttachment);
            }
        }

        try {
            HttpResponse<JsonNode> response = body.asJson();
            return new UniResponse(response);
        } finally {
            for (InputStream stream : streamsToClose) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    private UniResponse errorResponse(int code, String msg) {
        UniResponse response = new UniResponse(null);
        response.code = code;
        response.msg = msg;
        response.data = null;
        return response;
    }
}
