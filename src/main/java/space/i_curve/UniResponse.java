package space.i_curve;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

public class UniResponse {
    public String requestId;
    public String code;
    public String msg;
    public int status;
    public String data;

    /**
     * Create a new Uni Response.
     *
     * @param response raw HTTP response
     * @throws UniException if catch error
     */
    public UniResponse(final HttpResponse<JsonNode> response) throws UniException {
        JSONObject body = response.getBody().getObject();
        this.status = response.getStatus();

        if (body != null && body.has("code")) {
            this.code = body.getString("code");
            this.msg = body.getString("msg");

            if (!code.equals("200")) {
                throw new UniException(this.msg, code, this.requestId);
            }
            this.data = body.getString("data");
        } else {
            throw new UniException(response.getStatusText(), "-1");
        }
    }
}