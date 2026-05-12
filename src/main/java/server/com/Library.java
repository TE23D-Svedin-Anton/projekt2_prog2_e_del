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
    private String baseUrl = "http://10.151.168.5:3102/";

    // Skapa Gson instans
    private Gson gson = new Gson();
    //Skapa listor av böcker och magaziner
    private ArrayList<Book> Bookshelf;
    private ArrayList<Magazine> Magazineshelf;

    // Konstruktorn
    public Library() {

        Bookshelf = new ArrayList<>();
        Magazineshelf = new ArrayList<>();
    }

    public void fetchBooks() {
        HttpResponse<String> get_all_response = Unirest.get(baseUrl + "books").asString();

        String json_data = get_all_response.getBody();

        Type PublicationType = new TypeToken<ArrayList<Book>>() {
        }.getType();

        Bookshelf = gson.fromJson(json_data, PublicationType);

        for (Book b : Bookshelf) {
            System.out.println(b);
        }
    }

    public void fetchMagazine() {
         HttpResponse<String> get_all_response = Unirest.get(baseUrl+"magazines").asString();

         String json_data = get_all_response.getBody();

         Type PublicationType = new TypeToken <ArrayList<Magazine>>(){}.getType();

         Magazineshelf = gson.fromJson(json_data, PublicationType);

        for (Magazine m : Magazineshelf) {
        System.out.println(m);
    }
}
}
