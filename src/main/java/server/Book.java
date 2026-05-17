// Anton Svedin TE23D Detta är en klass för objektet bok.
package server;

import server.com.Publication;

public class Book extends Publication {

    private String author;
    private String genre;
    private int pages;

    public Book(String id, String title, String author, String genre, int pages, boolean isAvailable) {
        super(id, title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isAvailable=" + isAvailable
                + ", genre=" + genre + ", pages=" + pages + "]";
    }

}