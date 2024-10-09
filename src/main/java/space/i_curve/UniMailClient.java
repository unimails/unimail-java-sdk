package space.i_curve;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

public class UniMailClient {
    public UniMailClient(String host, String token) {
        this.host = host;
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    private String host;
    private String token;
    private String lang = "en";

    /**
     * 发送邮件
     *
     * @param toUser  收件人
     * @param title   标题
     * @param content 邮件正文
     * @return UniResponse
     */
    public UniResponse sendEmail(String toUser, String title, String content) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("toUser", toUser);
        data.put("title", title);
        data.put("content", content);
        HttpResponse<JsonNode> response = Unirest.post(this.host)
                .header("Content-Type", "application/json")
                .header("authorization", this.token)
                .header("accept-language", this.lang)
                .body(new JSONObject(data))
                .asJson();
        return new UniResponse(response);
    }

    //public UniResponse sendEmailAsync(String toUser, String title, String content) {
    //    return sendEmail(toUser, title, content);
    //}
}


