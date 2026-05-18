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

import java.util.HashMap;

public class Library {

    // URL till server tjänsten
    private String baseUrl = "http://10.151.168.5:3102/";

    // Skapa Gson instans
    private Gson gson = new Gson();
    // Skapa listor av böcker och magaziner
    private ArrayList<Book> bookShelf;
    private ArrayList<Magazine> magazineShelf;
    private ArrayList<User> customerList;
    private HashMap<String, Book> bookMap;
    private HashMap<String, Magazine> magazineMap;
    private HashMap<String, User> customerMap;
    private ArrayList<SuspendedUser> bannedList;

    // Konstruktorn
    public Library() {

        bookShelf = new ArrayList<>();
        magazineShelf = new ArrayList<>();
        customerList = new ArrayList<>();
        bannedList = new ArrayList<>();

        bookMap = new HashMap<>();
        magazineMap = new HashMap<>();
        customerMap = new HashMap<>();
    }

    // hämtar all böcker från servern
    public void fetchBooks() {

        // Anropar servern
        HttpResponse<String> getAllResponse;
        try {
            getAllResponse = Unirest.get(baseUrl + "books").asString();
            // Fångar undantag så programet ej stängs ner om det blir fel
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        // Tar in statusen
        int status = getAllResponse.getStatus();
        System.out.println("statusKod: " + status);

        // Kollar om det gick bra
        if (status != 200) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        // Tar json koden från responsen
        String getAllBody = getAllResponse.getBody();

        // Informationen till Gson vilken typ som ska skapas
        Type PublicationType = new TypeToken<ArrayList<Book>>() {
        }.getType();

        // konverterar Json datan till böcker och lägger in i bok listan
        bookShelf = gson.fromJson(getAllBody, PublicationType);

        for (Book book : bookShelf) {
            bookMap.put(book.getTitle(), book);
        }

        System.out.println("Antal böcker i listan: " + bookShelf.size());
    }

    // hämtar en bok från servern
    public void fetchBook(String id) {
        // Anropar servern
        HttpResponse<String> getOneResponse;
        try {
            getOneResponse = Unirest.get(baseUrl + "books/" + id).asString();
            // Fångar undantag så programet ej stängs ner om det blir fel
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        // Tar in statusen
        int status = getOneResponse.getStatus();
        System.out.println("statusKod: " + status);

        // Kollar om det gick bra
        if (status != 200) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }
        // Tar json koden från responsen
        String getOneBody = getOneResponse.getBody();

        // Konverterar json data så det blir till en book
        Book book = gson.fromJson(getOneBody, Book.class);

        // Lägger till boken i bok listan
        bookShelf.add(book);

        bookMap.put(book.getTitle(), book);

        System.out.println("Hämtad bok: " + book);
    }

    // Sparar en book i servern
    public void addbook(String title, String author, String genre, int pages) {

        // Skapar en bok
        Book newBook = new Book("0", title, author, genre, pages, true);

        // Konverterar boken till json data
        String jsonbody = gson.toJson(newBook);

        // anropar servern
        HttpResponse<String> addResponse;
        try {
            // berättar att vi skickar in json data
            addResponse = Unirest.post(baseUrl + "books")
                    .header("Content-Type", "application/json")
                    .body(jsonbody).asString();
            // Fångar undantag så programet ej stängs ner om det blir fel
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        // Kollar om det gick bra
        int status = addResponse.getStatus();
        if (status != 200 && status != 201) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        System.out.println("Sparad på servern:" + newBook);
    }

    // Hittar book genom title
    public void findBookByTitle(String title) {

        Book book = bookMap.get(title);

        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Ingen bok hittades");
        }
    }

    // hämtar all tidningar från servern
    public void fetchMagazines() {
        HttpResponse<String> getAllResponse;
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

        String getAllBody = getAllResponse.getBody();

        Type PublicationType = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazineShelf = gson.fromJson(getAllBody, PublicationType);

        for (Magazine magazine : magazineShelf) {
            magazineMap.put(magazine.getTitle(), magazine);
        }

        System.out.println("Antal tidningar i listan: " + magazineShelf.size());
    }

    public void fetchMagazine(String id) {
        HttpResponse<String> getOneResponse;
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

            magazineMap.put(magazine.getTitle(), magazine);

        System.out.println("Hämtad tidningar: " + magazine);
    }

    // Sparar en tidning i servern
    public void addMagazine(String title, int issueNumber, String category, int publisherYear) {

        Magazine newMagazine = new Magazine("0", title, issueNumber, category, publisherYear, true);

        String jsonbody = gson.toJson(newMagazine);

        HttpResponse<String> addResponse;
        try {
            addResponse = Unirest.post(baseUrl + "magazines")
                    .header("Content-Type", "application/json")
                    .body(jsonbody).asString();
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        int status = addResponse.getStatus();
        if (status != 200 && status != 201) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        System.out.println("Sparad på servern:" + newMagazine);
    }

    // Hittar magazine genom title
    public void findMagazineByTitle(String title) {

        Magazine magazine = magazineMap.get(title);

        if (magazine != null) {
            System.out.println(magazine);
        } else {
            System.out.println("Ingen bok hittades");
        }
    }

    // hämtar alla användare från servern
    public void fetchUsers() {
        HttpResponse<String> getAllResponse;

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

        String getAllBody = getAllResponse.getBody();

        Type PublicationType = new TypeToken<ArrayList<User>>() {
        }.getType();

        customerList = gson.fromJson(getAllBody, PublicationType);

        for (User user : customerList) {
            customerMap.put(user.getEmail(), user);
        }

        System.out.println("Antal användare i listan: " + customerList.size());
    }

    public void fetchUser(String id) {
        HttpResponse<String> getOneResponse;

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

        customerList.add(user);

        customerMap.put(user.getEmail(), user);

        System.out.println("Hämtad användare: " + user);
    }

    // Sparar en användare i servern
    public void addUser(String name, String email) {

        User newUser = new User("0", name, email);

        String jsonbody = gson.toJson(newUser);

        HttpResponse<String> addResponse;
        try {
            addResponse = Unirest.post(baseUrl + "users")
                    .header("Content-Type", "application/json")
                    .body(jsonbody).asString();
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        int status = addResponse.getStatus();
        if (status != 200 && status != 201) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        System.out.println("Sparad på servern:" + newUser);
    }

    // Hittar användare genom email
    public void findUserByEmail(String email) {
        User user = customerMap.get(email);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Ingen användare hittades");
        }
    }

    // hämtar alla bannade användare från servern
    public void fetchSuspendedUsers() {
        HttpResponse<String> getAllResponse;
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

        String getAllBody = getAllResponse.getBody();

        Type PublicationType = new TypeToken<ArrayList<SuspendedUser>>() {
        }.getType();

        bannedList = gson.fromJson(getAllBody, PublicationType);

        System.out.println("Antal bannade användare i listan: " + bannedList.size());
    }

    // Sparar en bannad användare i servern
    public void addSuspendedUser(String customerId) {

        SuspendedUser newSuspendedUser = new SuspendedUser("0", customerId);

        String jsonbody = gson.toJson(newSuspendedUser);

        HttpResponse<String> addResponse;
        try {
            addResponse = Unirest.post(baseUrl + "suspended")
                    .header("Content-Type", "application/json")
                    .body(jsonbody).asString();
        } catch (UnirestException e) {
            System.out.println("Undantag" + e.getLocalizedMessage());
            return;
        }

        int status = addResponse.getStatus();
        if (status != 200 && status != 201) {
            System.out.println("Fel från server, statusKod: " + status);
            return;
        }

        System.out.println("Sparad på servern:" + newSuspendedUser);
    }
}