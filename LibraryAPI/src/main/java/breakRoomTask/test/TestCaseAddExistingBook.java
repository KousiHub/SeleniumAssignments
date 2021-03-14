package breakRoomTask.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCaseAddExistingBook extends AllMethodsForLibraryAPI{

    @DataProvider
    public Object[][] existingBookDetails()
    {
        Object[][] book = new Object[1][4];

        book[0][0] = "Control Systems";
        book[0][1] = "878787890";
        book[0][2] = "9790";
        book[0][3] = "Mr Hanks";

        return book;
    }

    @Test(dataProvider = "existingBookDetails")
    public void addExistingBookAndGetErrorMessage(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        String duplicateMessage = validateAddDuplicateBookAndGetMessage(bookName, bookISBN, bookAisle, bookAuthor);
        Assert.assertEquals(duplicateMessage,"Add Book operation failed, looks like the book already exists","Not a valid message for duplicate books additions");
    }
}
