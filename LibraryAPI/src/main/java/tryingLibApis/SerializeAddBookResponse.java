package tryingLibApis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SerializeAddBookResponse {

    @JsonProperty("ID")
    public String id;

    @JsonProperty("Msg")
    public String message;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String msg) {
        this.message = msg;
    }
}
