package afal.app.business.book;

import java.util.List;

import javax.ejb.Local;

import afal.app.model.Book;

@Local
public interface BookLocal {
    List<Book> findAll();
    Book add( Book book );
}
