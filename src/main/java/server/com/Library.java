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

    //URL till server tjänsten
    private String baseUrl = "http://localhost:3000/";

    //Skapa Gson instans
    private Gson gson = new Gson();
    private ArrayList<Book> Bookshelf; 
    private ArrayList<Magazine> Magazineshelf;

    //Konstruktorn
    public Library(){

        Bookshelf = new ArrayList<>();
    }

    public void fetchbooks() {
         String json = Unirest.get(baseUrl + "books")
                .asString()
                .getBody();

        Book[] result = gson.fromJson(json, Book[].class);

        Bookshelf.addAll(Arrays.asList(result));

        for (Book b : Bookshelf) {
        System.out.println(b);
    }
}
}
