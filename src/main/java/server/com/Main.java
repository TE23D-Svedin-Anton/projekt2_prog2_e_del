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


public class Main {
    public static void main(String[] args) {
        String baseUrl = "http://10.151.168.5:3102/"; //URL till server tjänsten
    }
}