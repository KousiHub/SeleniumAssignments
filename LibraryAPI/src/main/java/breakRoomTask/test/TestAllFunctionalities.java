package breakRoomTask.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class TestAllFunctionalities extends AllMethodsForLibraryAPI {

    @DataProvider
    public Object[][] bookDetails()
    {
        Object[][] book = new Object[1][4];

        book[0][0] = "Control Systems";
        book[0][1] = "878787890";
        book[0][2] = "9790";
        book[0][3] = "Mr Hanks";

        return book;
    }
    String bookIDCreated;

    @Test(dataProvider = "bookDetails", priority = 0)
    public void testAddNewBookAndGetID(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        String addedMessage = validateAddBookAndGetIDCreated(bookName, bookISBN, bookAisle, bookAuthor);
        Assert.assertEquals(addedMessage,"[{\"book_name\":\"Control Systems\",\"isbn\":\"878787890\",\"aisle\":\"9790\",\"author\":\"Mr Hanks\"}]");
    }

    @Test(dataProvider = "bookDetails", priority = 1)
    public void testAddExistingBookAndGetErrorMessage(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        String duplicateMessage = validateAddDuplicateBookAndGetMessage(bookName, bookISBN, bookAisle, bookAuthor);
        Assert.assertEquals(duplicateMessage,"Add Book operation failed, looks like the book already exists","Not a valid message for duplicate books additions");
    }


    @Test(dataProvider = "bookDetails", priority = 2)
    public void testDeleteBook(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        String id = bookISBN.concat(bookAisle);
        String deleteMessage = validateDeleteBook(id);
        Assert.assertEquals(deleteMessage,"{\"msg\":\"book is successfully deleted\"}");
    }

    @Test(dataProvider = "bookDetails", priority = 3)
    public void testAddNewBookGetIDAndDelete(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        String addedMessage = validateAddBookAndGetIDCreated(bookName, bookISBN, bookAisle, bookAuthor);
        Assert.assertEquals(addedMessage,"[{\"book_name\":\"Control Systems\",\"isbn\":\"878787890\",\"aisle\":\"9790\",\"author\":\"Mr Hanks\"}]");
        String id = bookISBN.concat(bookAisle);
        String deleteMessage = validateDeleteBook(id);
        Assert.assertEquals(deleteMessage,"{\"msg\":\"book is successfully deleted\"}");
    }

    @DataProvider
    public Object[][] manyBooksDetails()
    {
        Object[][] book = new Object[3][4];

        book[0][0] = "Control Systems";
        book[0][1] = "878787890";
        book[0][2] = "9790";
        book[0][3] = "Mr Hanks";

        book[1][0] = "Flights Control";
        book[1][1] = "1878787890";
        book[1][2] = "19790";
        book[1][3] = "Mr Hanks";

        book[2][0] = "Landing Technology";
        book[2][1] = "2878787890";
        book[2][2] = "29790";
        book[2][3] = "Mr Hanks";

        return book;
    }


    @Test(dataProvider = "manyBooksDetails", priority = 4)
    public void testAddManyNewBookAndPrintByGetAuthor(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        validateAddManyBooksAndGetByAuthor(bookName, bookISBN, bookAisle, bookAuthor);
    }


   @Test(priority = 5)
   public void testGetBooksByInvalidAuthorAndValidAuthors() {
       String invalidAuthor = "Danks";
       String validAuthor = "Hanks";

       String noValidBooksByInvalidAuthor = validateGetByAuthor(invalidAuthor);
       Assert.assertEquals(noValidBooksByInvalidAuthor,"{\"msg\":\"The book by requested bookid \\/ author name does not exists!\"}");

       String validBooksByValidAuthor = validateGetByAuthor(validAuthor);
       Assert.assertEquals(validBooksByValidAuthor,"[{\"book_name\":\"Control Systems\",\"isbn\":\"878787890\",\"aisle\":\"9790\"},{\"book_name\":\"Flights Control\",\"isbn\":\"1878787890\",\"aisle\":\"19790\"},{\"book_name\":\"Landing Technology\",\"isbn\":\"2878787890\",\"aisle\":\"29790\"}]");
   }
}
