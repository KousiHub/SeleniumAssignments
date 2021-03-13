package breakRoomTask.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddDuplicateBookResponse {

    @JsonProperty("msg")
    private String msgForDuplicateRecord;

    public String getMsg() {
        return msgForDuplicateRecord;
    }

    public void setMessage(String msg) {
        this.msgForDuplicateRecord = msg;
    }
}
