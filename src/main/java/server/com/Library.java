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
import kong.unirest.UnirestException;
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

    // hämtar all böcker från servern
    public void fetchBooks() {
        HttpResponse<String> getAllResponse;

        try {
            getAllResponse = Unirest.get(baseUrl + "books").asString();
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        int status = getAllResponse.getStatus();
        System.out.println("statusKod: " + status);

        if (status != 200) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        String json_data = getAllResponse.getBody();

        Type PublicationType = new TypeToken<ArrayList<Book>>() {
        }.getType();

        bookShelf = gson.fromJson(json_data, PublicationType);

        for (Book b : bookShelf) {
            int id = Integer.parseInt(b.getId());
            nextBookId = Math.max(nextBookId, id + 1);
        }
    }

    // hämtar en bok från servern
    public void fetchBook(String id) {
        HttpResponse<String> getOneResponse;
        try {
            getOneResponse = Unirest.get(baseUrl + "books/" + id).asString();
            // Letar efter Undantag så programet ej stängs ner om det blir fel
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        int status = getOneResponse.getStatus();
        System.out.println("statusKod: " + status);

        if (status != 200) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        String getOneBody = getOneResponse.getBody();

        Book book = gson.fromJson(getOneBody, Book.class);

        bookShelf.add(book);
    }

    // Lägger till en ny book i bookhyllan
    public void addBook(String title, String author, String genre, int pages) {
        String id = String.valueOf(nextBookId);
        nextBookId++;
        Boolean isAvailable = true;
        Book book = new Book(id, title, author, genre, pages, isAvailable);
        bookShelf.add(book);

        System.out.println("Book added");
    }

    // hämtar all magazine från servern
    public void fetchMagazines() {
        HttpResponse<String> magazinesResponse = Unirest.get(baseUrl + "magazines").asString();

        String json_data = magazinesResponse.getBody();

        Type PublicationType = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazineShelf = gson.fromJson(json_data, PublicationType);

        for (Magazine m : magazineShelf) {
            int id = Integer.parseInt(m.getId());
            nextMagazineId = Math.max(nextMagazineId, id + 1);
        }

        System.out.println("Magazines fetched");
    }

    // hämtar en bok från servern
    public void fetchMagazines(String id) {
        HttpResponse<String> getOneResponse;
        try {
            getOneResponse = Unirest.get(baseUrl + "magazines/" + id).asString();
            // Letar efter Undantag så programet ej stängs ner om det blir fel
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        int status = getOneResponse.getStatus();
        System.out.println("statusKod: " + status);

        if (status != 200) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        String getOneBody = getOneResponse.getBody();

        Magazine magazine = gson.fromJson(getOneBody, Magazine.class);

        magazineShelf.add(magazine);
    }

    // lägg till ny tidning i tidningshyllan
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