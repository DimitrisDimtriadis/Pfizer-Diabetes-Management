package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.model.Book;
import gr.codehub.teamOne.resource.BookResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class BookServerResource extends ServerResource implements BookResource {
    @Override
    public Book getBook(){
        Book book=new Book();
        book.setPrice(10.2);
        book.setTitle("Animal Farm");
        return book;

    };
}
