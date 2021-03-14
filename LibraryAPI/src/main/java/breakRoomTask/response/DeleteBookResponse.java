package breakRoomTask.response;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookResponse {

    @JsonProperty("ID")
    private String idToDelete;

    @JsonProperty("msg")
    private String deleteMessage;


    public String getId() {
        return idToDelete;
    }

    public void setId(String id) {
        this.idToDelete = id;
    }

    public String getMessage() {
        return deleteMessage;
    }

    public void setMessage(String msg) {
        this.deleteMessage = msg;
    }

}
