package breakRoomTask.test;

import breakRoomTask.response.AddBookResponse;
import breakRoomTask.response.AddDuplicateBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tryingLibApis.SerializeGetBookResponse;

import static io.restassured.RestAssured.given;

public class AllMethodsForLibraryAPI {

    public void validateAddBookAndGetIDCreated(Object[][] objects) {

        RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book1 = new breakRoomTask.request.AddBookRequest();
        book1.setName(objects[0][0].toString());
        book1.setIsbn(objects[0][1].toString());
        book1.setAisle(objects[0][2].toString());
        book1.setAuthor(objects[0][3].toString());

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book1).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();

        AddBookResponse addBookRes = responsePassed.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookRes.getMessage(),"successfully added","successfully added");

        Response response = given().queryParam("ID", addBookRes.getId())
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Control Systems\",\"isbn\":\"878787890\",\"aisle\":\"9790\",\"author\":\"Mr Hanks\"}]");
        //SerializeGetBookResponse[] book = response.as(SerializeGetBookResponse[].class);
        //return (book[0].getBookAuthor());
    }

    public String validateAddDuplicateBookAndGetMessage() {

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
        return dupBookRes.getMsg();
        //Assert.assertEquals(dupBookRes.getMsg(),"Add Book operation failed, looks like the book already exists","Not a valid message for duplicate books additions");
    }

    public String validateGetBook(String idToGet) {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID", idToGet)
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        //System.out.println(response.asString());
    return response.asString();
    }

    public String validateDeleteBook(String idToDelete) {
        RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.DeleteBookRequest delBook1 = new breakRoomTask.request.DeleteBookRequest();
        delBook1.setId(idToDelete);
        Response responseDel = given().header("Content-Type", "application/json")
                .body(delBook1).when().post("/Library/DeleteBook.php")
                .then()
                .statusCode(200).extract().response();
        //System.out.println(responseDel.asString());
        return responseDel.asString();
    }

    public void validateAddManyBooksAndGetByAuthor() {

        RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book1 = new breakRoomTask.request.AddBookRequest();
        book1.setName("Control Systems");
        book1.setIsbn("454545");
        book1.setAisle("667788");
        book1.setAuthor("Hanks");

        breakRoomTask.request.AddBookRequest book2 = new breakRoomTask.request.AddBookRequest();
        book2.setName("Flights Control");
        book2.setIsbn("1454545");
        book2.setAisle("1667788");
        book2.setAuthor("Hanks");

        breakRoomTask.request.AddBookRequest book3 = new breakRoomTask.request.AddBookRequest();
        book3.setName("Landing Controls");
        book3.setIsbn("2454545");
        book3.setAisle("2667788");
        book3.setAuthor("Hanks");

        Response responsePassed1 = given().header("Content-Type", "application/json")
                .body(book1).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();
        AddBookResponse addBookRes1 = responsePassed1.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookRes1.getMessage(),"successfully added","successfully added");

        Response responsePassed2 = given().header("Content-Type", "application/json")
                .body(book2).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();
        AddBookResponse addBookRes2 = responsePassed2.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookRes2.getMessage(),"successfully added","successfully added");

        Response responsePassed3 = given().header("Content-Type", "application/json")
                .body(book3).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();
        AddBookResponse addBookRes3 = responsePassed3.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookRes3.getMessage(),"successfully added","successfully added");

        Response response = given().queryParam("AuthorName", book1.getAuthor())
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.asString(),"[{\"book_name\":\"Control Systems\",\"isbn\":\"454545\",\"aisle\":\"667788\"},{\"book_name\":\"Flights Control\",\"isbn\":\"1454545\",\"aisle\":\"1667788\"},{\"book_name\":\"Landing Controls\",\"isbn\":\"2454545\",\"aisle\":\"2667788\"}]");
        SerializeGetBookResponse[] book = response.as(SerializeGetBookResponse[].class);
        Assert.assertEquals(book[0].getBookName(),"Control Systems","Book Author not valid");
        Assert.assertEquals(book[1].getBookName(),"Flights Control","Book Author not valid");
        Assert.assertEquals(book[2].getBookName(),"Landing Controls","Book Author not valid");
    }
}
