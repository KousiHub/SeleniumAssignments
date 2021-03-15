package tryingLibApis;

import breakRoomTask.libraryfunctionalities.AllMethodsForLibraryAPI;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCaseAddBook extends AllMethodsForLibraryAPI {

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

    @Test(dataProvider = "bookDetails")
    void addNewBookAndGetID(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        String addedMessage = validateAddBookAndGetIDCreated(bookName, bookISBN, bookAisle, bookAuthor);
        Assert.assertEquals(addedMessage,"[{\"book_name\":\"Control Systems\",\"isbn\":\"878787890\",\"aisle\":\"9790\",\"author\":\"Mr Hanks\"}]");
    }

}
