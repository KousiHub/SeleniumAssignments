package tryingLibApis;

import breakRoomTask.response.SerializeAddBookResponse;
import breakRoomTask.response.SerializeGetBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class TestAddAndGetBooks {

    @Test
    public void validateAddBook() {

        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookReq = new AddBookRequest();
        addBookReq.setBookName("Web Technology");
        //addBookReq.setBookISBN(boTask.ReUse.rawToJson("112233"));
        addBookReq.setBookISBN("112233");
        addBookReq.setBookAisle("4455");
        addBookReq.setBookAuthor("Peter Parker");

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(addBookReq).when().post("/Library/AddBook.php")
                .then()
                //.log().all().assertThat()
                .statusCode(200).extract().response();
        //System.out.println(responsePassed.asString());
        SerializeAddBookResponse addBookRes = responsePassed.body().as(SerializeAddBookResponse.class);
        Assert.assertEquals(addBookRes.getMessage(),"Good","bad");
        //System.out.println("ID passed:" + addBookRes.getId());
    }

    @Test
    public void validateBookByID() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID","797577775")//"57579999")
                .header("Content-Type","application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Automation\",\"isbn\":\"7975\",\"aisle\":\"77775\",\"author\":\"Harry\"}]");
    }

    @Test
    public void validateBookByAuthor() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("author","Harry")
                .header("Content-Type","application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Automation\",\"isbn\":\"7975\",\"aisle\":\"77775\",\"author\":\"Harry\"}]");
    }

    @Test
    public void validateBooksByAllParamSerialized() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID","7977777").queryParam("ID","57579999")
                .header("Content-Type","application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        SerializeGetBookResponse[] book = response.as(SerializeGetBookResponse[].class);
        Assert.assertEquals(book[0].getBookName(),"Automation","Book Name not valid");
        Assert.assertEquals(book[0].getBookISBN(),"797","Book ISBN not valid");
        Assert.assertEquals(book[0].getBookAisle(),"7777","Book Aisle not valid");
        Assert.assertEquals(book[0].getBookAuthor(),"ONLYMEEE","Book Author not valid");
        //Assert.assertEquals(book[1].getBookAuthor(),"MEEE","Invalid author name");
    }

    @Test
    public void validateAddBookAndGetTheSame() {

        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookReq = new AddBookRequest();
        addBookReq.setBookName("Control Systems");
        addBookReq.setBookISBN("454545");
        addBookReq.setBookAisle("667788");
        addBookReq.setBookAuthor("Hanks");

        Response responsePassed = given()
                .header("Content-Type","application/json")
                .body(addBookReq)
                .when()
                .post("/Library/AddBook.php")
                .then()
                .log().all().assertThat()
                .statusCode(200)
                .extract().response();

        SerializeAddBookResponse addBookRes = responsePassed.body().as(SerializeAddBookResponse.class);
        System.out.println("ID passed:" +addBookRes.getId());

        Response response = given().queryParam("ID",addBookRes.getId())
                .header("Content-Type","application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        SerializeGetBookResponse[] book = response.as(SerializeGetBookResponse[].class);
        Assert.assertEquals(book[0].getBookAuthor(),"SpiderMan","Book Author not valid");
    }

    @Test
    public void validateDeleteBookByID() {

        breakRoomTask.request.DeleteBookRequest delBook1 = new breakRoomTask.request.DeleteBookRequest();
        delBook1.setId("7975127777512");
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID","7975127777512")
                .header("Content-Type","application/json")
                .body(delBook1)
                .when()
                .post("/Library/DeleteBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals(response.asString(),"[{\"msg\":\"book is successfully deleted\"}]");
    }
}
