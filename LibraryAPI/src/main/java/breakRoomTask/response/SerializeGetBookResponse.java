package breakRoomTask.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SerializeGetBookResponse {

    @JsonProperty("book_name")
    public String bookName;
    public String isbn;
    public String aisle;
    public String author;

    public String getBookName() {
        return bookName;
    }

    public String getBookISBN() {
        return isbn;
    }

    public String getBookAisle() {
        return aisle;
    }

    public String getBookAuthor() {
        return author;
    }
}
