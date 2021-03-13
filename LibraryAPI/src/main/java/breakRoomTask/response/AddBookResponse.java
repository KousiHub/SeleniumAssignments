package breakRoomTask.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookResponse {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("Msg")
    private String message;


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
