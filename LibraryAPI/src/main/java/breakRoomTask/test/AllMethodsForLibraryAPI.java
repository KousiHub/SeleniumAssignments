package breakRoomTask.test;

import breakRoomTask.response.AddBookResponse;
import breakRoomTask.response.AddDuplicateBookResponse;
import breakRoomTask.response.DeleteBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tryingLibApis.SerializeGetBookResponse;

import static io.restassured.RestAssured.given;

public class AllMethodsForLibraryAPI {

    public String validateAddBookAndGetIDCreated(String bookName, String bookISBN, String bookAisle, String bookAuthor) {

        //RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book = new breakRoomTask.request.AddBookRequest();
        book.setName(bookName.toString());
        book.setIsbn(bookISBN.toString());
        book.setAisle(bookAisle.toString());
        book.setAuthor(bookAuthor.toString());

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book).when().post("/Library/Addbook.php")
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

        return response.asString();
    }

    public String validateAddDuplicateBookAndGetMessage(String bookName, String bookISBN, String bookAisle, String bookAuthor) {

        //RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book = new breakRoomTask.request.AddBookRequest();
        book.setName(bookName.toString());
        book.setIsbn(bookISBN.toString());
        book.setAisle(bookAisle.toString());
        book.setAuthor(bookAuthor.toString());

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book).when().post("/Library/Addbook.php")
                .then()
                .statusCode(404).extract().response();

        AddDuplicateBookResponse dupBookRes = responsePassed.body().as(AddDuplicateBookResponse.class);
        return dupBookRes.getMsg();
    }

    public String validateGetBook(String idToGet) {
        //RestAssured.baseURI = "http://216.10.245.166";
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
        //RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.DeleteBookRequest delBook = new breakRoomTask.request.DeleteBookRequest();
        delBook.setId(idToDelete);
        Response responseDel = given().header("Content-Type", "application/json")
                .body(delBook).when().post("/Library/DeleteBook.php")
                .then()
                //.statusCode(200)
                .extract().response();
        //System.out.println(responseDel.asString());
        DeleteBookResponse delBookRes = responseDel.body().as(DeleteBookResponse.class);
        return responseDel.asString();
    }

    public String validateAddManyBooksAndGetByAuthor(String bookName, String bookISBN, String bookAisle, String bookAuthor) {
        //RestAssured.baseURI = "http://216.10.245.166";
        breakRoomTask.request.AddBookRequest book = new breakRoomTask.request.AddBookRequest();
        book.setName(bookName.toString());
        book.setIsbn(bookISBN.toString());
        book.setAisle(bookAisle.toString());
        book.setAuthor(bookAuthor.toString());

        Response responsePassed = given().header("Content-Type", "application/json")
                .body(book).when().post("/Library/Addbook.php")
                .then()
                .statusCode(200).extract().response();

        AddBookResponse addBookRes = responsePassed.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookRes.getMessage(), "successfully added", "successfully added");

        Response response = given().queryParam("AuthorName", bookAuthor)
                //.header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();

        //Assert.assertEquals(response.asString(),"[{\"book_name\":\"Control Systems\",\"isbn\":\"454545\",\"aisle\":\"667788\"},{\"book_name\":\"Flights Control\",\"isbn\":\"1454545\",\"aisle\":\"1667788\"},{\"book_name\":\"Landing Controls\",\"isbn\":\"2454545\",\"aisle\":\"2667788\"}]");
        SerializeGetBookResponse[] books = response.as(SerializeGetBookResponse[].class);
        String[] bookNames = new String[books.length];

        for(int i = 0; i < books.length; i++) {
            System.out.println(bookNames[i] = books[books.length-1].getBookName());
            break;
        }
        return addBookRes.getId();
    }

    public String validateGetByAuthor(String authorName) {

        //RestAssured.baseURI = "http://216.10.245.166";
        Response getByAuthorResponse = given()
                .queryParam("AuthorName",authorName)
                .when()
                .get("/Library/GetBook.php")
                .then()
                .log()
                .body().extract().response();
        //System.out.println(getByAuthorResponse.body().asString());
        return getByAuthorResponse.asString();
    }

}
