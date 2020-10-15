package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.model.Book;
import gr.codehub.teamOne.resource.BookListResource;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.List;

public class BookListServerResource extends ServerResource implements BookListResource {

    private List<Book> books;

    public BookListServerResource() {
        books = new ArrayList<>();
        Book book=new Book();
        book.setPrice(10.2);
        book.setTitle("Animal Farm");
        books.add(book);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }
}
