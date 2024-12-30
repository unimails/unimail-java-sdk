package space.i_curve;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

public class UniResponse {
    public Integer code;
    public String msg;
    public Object data;

    public boolean isSuccess() {
        return this.code != null && this.code == 0;
    }

    /**
     * Create a new Uni Response.
     *
     * @param response raw HTTP response
     */
    public UniResponse(final HttpResponse<JsonNode> response) {
        if (response == null || response.getStatus() != 200) {
            this.code = 400;
            this.msg = "network error";
            return;
        }
        JSONObject body = response.getBody().getObject();
        if (body == null) {
            this.code = 500;
            this.msg = "server error";
            return;
        }

        this.code = body.has("code") ? body.getInt("code") : 500;
        this.msg = body.has("msg") ? body.getString("msg") : "server error";
        this.data = body.has("data") ? body.get("data") : null;
    }
}