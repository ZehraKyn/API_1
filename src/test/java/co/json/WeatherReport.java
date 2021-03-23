package co.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherReport {


        public static void main(String[] args) throws IOException, ParseException {

            String apiKey = "8947c516ec2a26a6845025c87752719e";
            String serviceAPI = "https://api.openweathermap.org/data/2.5/weather?q=";
            String location = "Istanbul";
            String units = "metric";
            String URI = serviceAPI + location + "&apiKey=" + apiKey + "&units=" + units;

            System.out.println(URI);

            // Connection
            URL url = new URL(URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);


            //System.out.println(responseCode);

             //Check response code
            if (responseCode != 200) {
                System.out.println("Web Service Response Code: " + responseCode);
                System.exit(0);
            }
            // Download all data from web
            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext())
                inline.append(scanner.nextLine());

            System.out.println(inline);


            scanner.close();
            //Using JSON simple library parse the string into json object
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline.toString());
            System.out.println("LOcation                       : " + location);

            //Coordinates
            JSONObject coord = (JSONObject) data_obj.get("coord");
            System.out.println("LOcation                       : " + coord.get("lat"));
            System.out.println("LOcation                       : " + coord.get("lon"));

            //Weather Report
            JSONObject main = (JSONObject) data_obj.get("main");
            System.out.printf("Current Temperature (C)  : %s %n", main.get("temp"));
            System.out.printf("Feels Like               : %s %n", main.get("feels_like"));
            System.out.printf("Temps (min)              : %s %n", main.get("temp_min"));
            System.out.printf("Temps (max)              : %s %n", main.get("temp_max"));
            System.out.printf("Humidity                 : %s %n", main.get("humidity"));

            //ARRAY
            JSONArray weather = (JSONArray) data_obj.get("weather");
            JSONObject weatherInfo =(JSONObject) weather.get(0);
            System.out.printf("Condition                 : %s %n", weatherInfo.get("main"));
            System.out.printf("Condition                 : %s %n", weatherInfo.get("description"));

        }
}
