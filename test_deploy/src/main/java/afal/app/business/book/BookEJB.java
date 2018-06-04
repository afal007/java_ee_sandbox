package afal.app.business.book;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import afal.app.model.Book;

@Named
@Stateless
public class BookEJB implements BookLocal{

    @PersistenceContext( unitName = "PU" )
    EntityManager em;

    @Override
    public List<Book> findAll() {
        return em.createNamedQuery( Book.FIND_ALL, Book.class ).getResultList();
    }

    @Override
    public Book add( Book book ) {
        em.persist( book );
        return book;
    }
}
