package breakRoomTask.AllFunctionalities;

import breakRoomTask.response.AddBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import tryingLibApis.SerializeGetBookResponse;

import static io.restassured.RestAssured.given;

public class AddNewBookAndGetID {

    public String validateAddBookAndGetIDCreated() {

        RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book1 = new breakRoomTask.request.AddBookRequest();
        book1.setName("Control Systems");
        book1.setIsbn("878787890");
        book1.setAisle("9790");
        book1.setAuthor("Mr Hanks");

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book1).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();
        System.out.println(responsePassed.asString());
        AddBookResponse addBookRes = responsePassed.body().as(AddBookResponse.class);
        System.out.println("Book Added");
        //Assert.assertEquals(addBookRes.getMessage(),"successfully added","successfully added");

        Response response = given().queryParam("ID",addBookRes.getId())
                .header("Content-Type","application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("Added Book");
        System.out.println(response.asString());
        SerializeGetBookResponse[] book = response.as(SerializeGetBookResponse[].class);
        return(book[0].getBookAuthor());
        //Assert.assertEquals(response.asString(),"[{\"book_name\":\"Control Systems\",\"isbn\":\"878787890\",\"aisle\":\"9790\",\"author\":\"Mr Hanks\"}]");
    }
}
