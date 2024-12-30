package space.i_curve;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnimailClient {
    public UnimailClient(String key) {
        this.host = "https://unimail-back.allcloud.top";
        this.key = key;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setKey(String token) {
        this.key = token;
    }

    public boolean setLang(String lang) {
        if (!this.supportLang.contains(lang)) {
            return false;
        }
        this.lang = lang;
        return true;
    }

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

    public boolean checkConnection() {
        HttpResponse<JsonNode> response = Unirest.post(this.host + "/api/email/checkConnection")
                .header("Content-Type", "application/json")
                .header("authorization", this.key)
                .header("accept-language", this.lang)
                .body(new JSONObject())
                .asJson();
        UniResponse result = new UniResponse(response);
        return result.isSuccess();
    }

    /**
     * 发送邮件
     *
     * @param receiver 收件人
     * @param subject  邮件标题
     * @param content  邮件正文
     * @return UniResponse
     */
    public UniResponse sendEmail(String receiver, String subject, String content) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("receiver", receiver);
        data.put("title", subject);
        data.put("content", content);
        HttpResponse<JsonNode> response = Unirest.post(this.host + "/api/email/sendEmail")
                .header("Content-Type", "application/json")
                .header("authorization", this.key)
                .header("accept-language", this.lang)
                .body(new JSONObject(data))
                .asJson();
        return new UniResponse(response);
    }

    /**
     * 批量发送邮件
     *
     * @param receivers 接收人
     * @param subject   邮件标题
     * @param content   邮件正文
     * @return UniResponse
     */
    public UniResponse batchSendEmail(List<String> receivers, String subject, String content) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("receivers", receivers);
        data.put("title", subject);
        data.put("content", content);
        HttpResponse<JsonNode> response = Unirest.post(this.host + "/api/email/batchSendEmail")
                .header("Content-Type", "application/json")
                .header("authorization", this.key)
                .header("accept-language", this.lang)
                .body(new JSONObject(data))
                .asJson();
        return new UniResponse(response);
    }
    //public UniResponse sendEmailAsync(String toUser, String title, String content) {
    //    return sendEmail(toUser, title, content);
    //}
}


