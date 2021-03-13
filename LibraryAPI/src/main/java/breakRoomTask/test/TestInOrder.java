package breakRoomTask.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class TestInOrder extends AllMethodsForLibraryAPI{

    AllMethodsForLibraryAPI lib = new AllMethodsForLibraryAPI();
    String bookID = "1122334455";
    String getMessage;
    String deleteMessage;

    @Test(priority = 0)
    public void TestAddNewAndGetID() {
        //String bookID = validateAddBookAndGetIDCreated();
        System.out.println("Book Added. ID = " +bookID);
        //Assert.assertEquals(bookID, "successfully added", "successfully added");
    }

    @Test(priority = 1)
    public void TestAddDuplicateAndGetMsg() {
        String duplicateMessage = validateAddDuplicateBookAndGetMessage();
        System.out.println("Following book already added");
        Assert.assertEquals(duplicateMessage,"Add Book operation failed, looks like the book already exists","Not a valid message for duplicate books additions");
    }

   /** @Test//(dependsOnMethods = "TestAddMethod")
    public void TestGetMethod(){
        getMessage = validateGetBook(bookID);
        //System.out.println(getMessage);
    } **/

    @Test(priority = 2)
    public void TestDeleteAddAgainAndGetAgain() {
        deleteMessage = validateDeleteBook(bookID);
        System.out.println("Deleted the book. Adding Again....");

        //String bookID = validateAddBookAndGetIDCreated();
        Assert.assertEquals(bookID, "successfully added", "successfully added");


    }

    @Test(priority = 3)
    public void TestDeleteMethod() {
        deleteMessage = validateDeleteBook(bookID);
        System.out.println(deleteMessage);
        Assert.assertEquals(deleteMessage,"{\"msg\":\"book is successfully deleted\"}");
    }

    @Test
    public void testAddManyBooksAndGetByAuthor() {
        validateAddManyBooksAndGetByAuthor();
    }

}
