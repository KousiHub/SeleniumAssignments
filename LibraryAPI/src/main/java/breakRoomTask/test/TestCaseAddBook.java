package breakRoomTask.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCaseAddBook extends AllMethodsForLibraryAPI{

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
    void addNewBookAndGetID() {
        validateAddBookAndGetIDCreated(bookDetails());
        //validateAddBookAndGetIDCreated(String bookName, String bookISBN, String bookAisle, String bookAuthor);
    }

}
