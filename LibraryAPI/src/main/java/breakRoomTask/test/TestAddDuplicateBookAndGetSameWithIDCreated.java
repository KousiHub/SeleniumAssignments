package breakRoomTask.test;

import breakRoomTask.response.AddBookResponse;
import breakRoomTask.response.AddDuplicateBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAddDuplicateBookAndGetSameWithIDCreated {
    @Test
    public void validateAddBookAndGetIDCreated() {

        RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book1 = new breakRoomTask.request.AddBookRequest();
        book1.setName("Control Systems");
        book1.setIsbn("878787890");
        book1.setAisle("9790");
        book1.setAuthor("Mr Hanks");

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book1).when().post("/Library/Addbook.php")
                .then()
                .statusCode(404).extract().response();
        System.out.println(responsePassed.asString());

        AddDuplicateBookResponse dupBookRes = responsePassed.body().as(AddDuplicateBookResponse.class);
        Assert.assertEquals(dupBookRes.getMsg(),"Add Book operation failed, looks like the book already exists","Not a valid message for duplicate books additions");
    }

}
