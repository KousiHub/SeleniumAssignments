package tryingLibApis;

public class AddBookRequest {

    private String name;
    private String isbn;
    private String aisle;
    private String author;

    public String getBookName() {
        return name;
    }
    public void setBookName(String name) {
        this.name = name;
    }
    public String getBookISBN() {
        return isbn;
    }
    public void setBookISBN(String isbn) {
        this.isbn = isbn;
    }
    public String getBookAisle() {
        return aisle;
    }
    public void setBookAisle(String aisle) {
        this.aisle = aisle;
    }
    public String getBookAuthor() {
        return author;
    }
    public void setBookAuthor(String author) {
        this.author = author;
    }
}
