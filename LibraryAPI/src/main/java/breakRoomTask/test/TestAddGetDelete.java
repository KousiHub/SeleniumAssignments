package breakRoomTask.test;

import breakRoomTask.response.AddBookResponse;
import breakRoomTask.response.DeleteBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAddGetDelete {

    @Test
    public void validateAddGetDeleteGetAddAgain() {

        RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book1 = new breakRoomTask.request.AddBookRequest();
        book1.setName("Jurassic Park");
        book1.setIsbn("50999123123");
        book1.setAisle("509992342");
        book1.setAuthor("Spielberg");

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book1).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();
        AddBookResponse addBookRes = responsePassed.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookRes.getMessage(),"successfully added","successfully added");

       Response response = given().queryParam("ID",addBookRes.getId())
                .header("Content-Type","application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        //System.out.println(response.asString());
        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Jurassic Park\",\"isbn\":\"50999123123\",\"aisle\":\"509992342\",\"author\":\"Spielberg\"}]");


        breakRoomTask.request.DeleteBookRequest delBook1 = new breakRoomTask.request.DeleteBookRequest();
        delBook1.setId(addBookRes.getId());
        Response responseDel = given().header("Content-Type", "application/json")
                .body(delBook1).when().post("/Library/DeleteBook.php")
                .then()
                .statusCode(200).extract().response();
        //System.out.println(response.asString());
        //DeleteBookResponse delBookRes = responseDel.body().as(DeleteBookResponse.class);
        Assert.assertEquals(responseDel.asString(),"{\"msg\":\"book is successfully deleted\"}");
    }

}
