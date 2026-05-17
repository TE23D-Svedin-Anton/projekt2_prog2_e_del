// Anton Svedin TE23D Klassen Library hanterar metoderna för hämtning och lagring av böcker och tidningar från servern
//inklusive egna tillägg av böcker och tidningar.

package server.com;

//Importerar Gson objekt
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
//Importerar Type så att gson kan omvandla data
import java.lang.reflect.Type;
//Importerar unirest objekt
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import server.Book;
import server.Magazine;

//Gör så vi kan ha arraylist för att lagra objekt
import java.util.ArrayList;

public class Library {

    // URL till server tjänsten
    private String baseUrl = "http://localhost:3000/";

    // Skapa Gson instans
    private Gson gson = new Gson();
    // Skapa listor av böcker och magaziner
    private ArrayList<Book> bookShelf;
    private int nextBookId = 1;
    private ArrayList<Magazine> magazineShelf;
    private int nextMagazineId = 1;

    // Konstruktorn
    public Library() {

        bookShelf = new ArrayList<>();
        magazineShelf = new ArrayList<>();
    }

    public void fetchBooks() {
        HttpResponse<String> get_all_response = Unirest.get(baseUrl + "books").asString();

        String json_data = get_all_response.getBody();

        Type PublicationType = new TypeToken<ArrayList<Book>>() {
        }.getType();

        bookShelf = gson.fromJson(json_data, PublicationType);

        for (Book b : bookShelf) {
            int id = Integer.parseInt(b.getId());
            nextBookId = Math.max(nextBookId, id + 1);
        }
        System.out.println("Books fetched");
    }

    public void addBook(String title, String author, String genre, int pages) {
        String id = String.valueOf(nextBookId);
        nextBookId++;
        Boolean isAvailable = true;
        Book book = new Book(id, title, author, genre, pages, isAvailable);
        bookShelf.add(book);

        System.out.println("Book added");
    }

    public void fetchMagazines() {
        HttpResponse<String> get_all_response = Unirest.get(baseUrl + "magazines").asString();

        String json_data = get_all_response.getBody();

        Type PublicationType = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazineShelf = gson.fromJson(json_data, PublicationType);

        for (Magazine m : magazineShelf) {
            int id = Integer.parseInt(m.getId());
            nextMagazineId = Math.max(nextMagazineId, id + 1);
        }

        System.out.println("Magazines fetched");
    }

    public void addMagazine(String title, int issueNumber, String category, int publisherYear) {
        String id = String.valueOf(nextMagazineId);
        nextMagazineId++;
        Boolean isAvailable = true;
        Magazine magazine = new Magazine(id, title, issueNumber, category, publisherYear, isAvailable);
        magazineShelf.add(magazine);

        System.out.println("Magazine added");
    }

    public ArrayList<Book> getBookShelf() {
        return bookShelf;
    }

    public ArrayList<Magazine> getMagazineShelf() {
        return magazineShelf;
    }
}