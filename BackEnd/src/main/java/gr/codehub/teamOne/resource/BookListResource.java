package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.model.Book;
import org.restlet.resource.Get;

import java.util.List;


public interface BookListResource {
    @Get("json")
    public List<Book> getBooks();

}

