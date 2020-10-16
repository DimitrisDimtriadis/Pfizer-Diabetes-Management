package gr.codehub.teamOne.resource;

import gr.codehub.teamOne.model.Book;
import org.restlet.resource.Get;

public interface BookResource {
    @Get("json")
    public Book getBook();
}
