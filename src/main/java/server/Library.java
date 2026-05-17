// Anton Svedin TE23D Klassen Library hanterar metoderna för hämtning och lagring av böcker och tidningar från servern
package server;

//inklusive egna tillägg av böcker och tidningar.
//Importerar Gson objekt
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
//Importerar Type så att gson kan omvandla data
import java.lang.reflect.Type;
//Importerar unirest objekt
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.HttpResponse;

//Gör så vi kan ha arraylist för att lagra objekt
import java.util.ArrayList;

public class Library {

    // URL till server tjänsten
    private String baseUrl = "http://localhost:3000/";

    // Skapa Gson instans
    private Gson gson = new Gson();
    // Skapa listor av böcker och magaziner
    private ArrayList<Book> bookShelf;
    private ArrayList<Magazine> magazineShelf;
    private ArrayList<User> CustomerList;
    private ArrayList<SuspendedUser> BannedList;

    // Konstruktorn
    public Library() {

        bookShelf = new ArrayList<>();
        magazineShelf = new ArrayList<>();
    }

    // hämtar all böcker från servern
    public void fetchBooks() {
        HttpResponse<String> getAllResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
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
    }

    // hämtar en bok från servern
    public void fetchBook(String id) {
        HttpResponse<String> getOneResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
        try {
            getOneResponse = Unirest.get(baseUrl + "books/" + id).asString();
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

    // hämtar all tidningar från servern
    public void fetchMagazines() {
        HttpResponse<String> getAllResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
        try {
            getAllResponse = Unirest.get(baseUrl + "magazines").asString();
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

        Type PublicationType = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazineShelf = gson.fromJson(json_data, PublicationType);
    }

    // hämtar en tidning från servern
    public void fetchMagazine(String id) {
        HttpResponse<String> getOneResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
        try {
            getOneResponse = Unirest.get(baseUrl + "magazines/" + id).asString();
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

    // hämtar alla användare från servern
    public void fetchUsers() {
        HttpResponse<String> getAllResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
        try {
            getAllResponse = Unirest.get(baseUrl + "users").asString();
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

        Type PublicationType = new TypeToken<ArrayList<User>>() {
        }.getType();

        CustomerList = gson.fromJson(json_data, PublicationType);
    }

    // hämtar en användare från servern
    public void fetchUser(String id) {
        HttpResponse<String> getOneResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
        try {
            getOneResponse = Unirest.get(baseUrl + "users/" + id).asString();
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

        User user = gson.fromJson(getOneBody, User.class);

        CustomerList.add(user);
    }

        // hämtar alla bannade användare från servern
    public void fetchSuspendedUsers() {
        HttpResponse<String> getAllResponse;

        // Letar efter undantag så programet ej stängs ner om det blir fel
        try {
            getAllResponse = Unirest.get(baseUrl + "suspended").asString();
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

        Type PublicationType = new TypeToken<ArrayList<SuspendedUser>>() {
        }.getType();

        BannedList = gson.fromJson(json_data, PublicationType);
    }

    public ArrayList<Book> getBookShelf() {
        return bookShelf;
    }

    public ArrayList<Magazine> getMagazineShelf() {
        return magazineShelf;
    }
}