
package POCOTEMP;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Bookstore {

    @SerializedName("books")
    @Expose
    private List<POCOTEMP.Book> books;

    public List<POCOTEMP.Book> getBooks() {
        return books;
    }

    public void setBooks(List<POCOTEMP.Book> books) {
        this.books = books;
    }

}
