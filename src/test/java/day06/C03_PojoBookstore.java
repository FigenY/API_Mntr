package day06;

import POCOTEMP.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class C03_PojoBookstore {

    private String bookStoreBaseUrl="https://bookstore.toolsqa.com";

    @Test
    public void t_getAllBooks() {
        /**
         * CONVERT JSON OBJECT TO POJO(PLAIN OLD JAVA OBJECT -- CUSTOM JAVA CLASS) WITH AS() METHOD (DE-SERIALIZITION)
         * Send GET request to bookstore to get all books
         * Convert response(json body) to custom java class
         * Make validations...
         */


        Response response = RestAssured.given().accept(ContentType.JSON)
                .get(bookStoreBaseUrl + "/BookStore/v1/Books");

        POCOTEMP.Bookstore bookstore=response.as(POCOTEMP.Bookstore.class);

        List<Book> books = bookstore.getBooks();
        Book book=books.get(0);
        System.out.println("book.getTitle() = " + book.getTitle());

        //verify that first ısbn number is 9781449325862

        String firstIsbn="9781449325862";
        String actualIsbn=book.getIsbn();
        Assert.assertEquals(actualIsbn,firstIsbn);


        // verify that last author of last book is "Nicholas C. Zakas"

        String expectedAuthor="Nicholas C. Zakas";
        Book bookLast=books.get(books.size()-1);
        String actualAuthor=bookLast.getAuthor();
        Assert.assertEquals(actualAuthor,expectedAuthor);

    }
}
