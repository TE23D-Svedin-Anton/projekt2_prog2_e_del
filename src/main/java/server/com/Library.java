package server.com;

//Importerar Gson objekt
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
//Importerar Type så att gson kan omvandla data
import java.lang.reflect.Type;
//Importerar unirest objekt
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import server.Book;
import server.Magazine;

//Gör så vi kan ha arraylist för att lagra objekt
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    // URL till server tjänsten
    private String baseUrl = "http://localhost:3000/";

    // Skapa Gson instans
    private Gson gson = new Gson();
    // Skapa listor av böcker och magaziner
    private ArrayList<Book> bookShelf;
    private ArrayList<Magazine> magazineShelf;

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
            System.out.println(b);
        }
    }

    public void fetchBook() {
        HttpResponse<String> get_one_response = Unirest.get(baseUrl + "books" + "/2").asString();

        String json_data = get_one_response.getBody();

        Book book = gson.fromJson(json_data, Book.class);

        bookShelf.add(book);

        for (Book b : bookShelf) {
            System.out.println(b);
        }
    }

    public void fetchMagazines() {
        HttpResponse<String> get_all_response = Unirest.get(baseUrl + "magazines").asString();

        String json_data = get_all_response.getBody();

        Type PublicationType = new TypeToken<ArrayList<Magazine>>() {
        }.getType();

        magazineShelf = gson.fromJson(json_data, PublicationType);

        for (Magazine m : magazineShelf) {
            System.out.println(m);
        }
    }

    public void fetchMagazine() {
        HttpResponse<String> get_one_response = Unirest.get(baseUrl + "magazines" + "/2").asString();

        String json_data = get_one_response.getBody();

        Magazine magazine = gson.fromJson(json_data, Magazine.class);

        magazineShelf.add(magazine);

        for (Magazine m : magazineShelf) {
            System.out.println(m);
        }
    }
}
