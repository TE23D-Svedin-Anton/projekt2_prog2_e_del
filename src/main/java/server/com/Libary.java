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
//Gör så vi kan ha arraylist för att lagra objekt
import java.util.ArrayList;
import java.util.List;


public class Libary {

    private String baseUrl = "http://10.151.168.5:3102/"; //URL till server tjänsten

    private Gson gson = new Gson(); //Skapa Gson instans
    private ArrayList<Publication> Shelf; 



}
