package afal.app.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import afal.app.business.book.BookEJB;
import afal.app.model.Book;

@Named
@RequestScoped
public class BookController {

    @Inject
    private BookEJB bookEJB;

    private Book book = new Book();

    public String createBook() {
        FacesContext context = FacesContext.getCurrentInstance();
        String res = null;

        if( StringUtils.isEmpty( book.getName() ) ) {
            context.addMessage( "bookForm:Name", new FacesMessage( FacesMessage.SEVERITY_WARN, "Wrong name", "You should enter the name" ) );
        }
        if( StringUtils.isEmpty( book.getAuthor() ) ) {
            context.addMessage( "bookForm:Author", new FacesMessage( FacesMessage.SEVERITY_WARN, "Wrong author", "You should enter the author" ) );
        }

        if ( context.getMessageList().size() == 0 ) {
            try {
                book = bookEJB.add( book );
                context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Book created", "The book named " + book.getName() + " was created with ID=" + book.getId() ) );
                res = "booksShow";
            } catch ( Exception e ) {
                context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Book hasn't been created", e.getMessage() ) );
        }   }

        return res;
    }

    public BookEJB getBookEJB() {
        return bookEJB;
    }

    public void setBookEJB( BookEJB bookEJB ) {
        this.bookEJB = bookEJB;
    }

    public Book getBook() {
        return book;
    }

    public void setBook( Book book ) {
        this.book = book;
    }
}
