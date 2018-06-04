package afal.app.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table
@NamedQuery( name=Book.FIND_ALL, query = "SELECT c FROM BOOK c" )
public class Book {
    public final static String FIND_ALL = "FIND_ALL";

    private long id;

    private String name;

    private String author;

    private int pageNumber;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    @Column( nullable = false, length = 50 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Column( nullable = false )
    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    @Column
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber( int pageNumber ) {
        this.pageNumber = pageNumber;
    }
}
