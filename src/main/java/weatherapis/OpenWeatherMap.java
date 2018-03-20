package weatherapis;

import database.DBWriter;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class OpenWeatherMap {

    public OpenWeatherMap(){
        start();
    }

    public void start(){

        String url = "http://api.openweathermap.org/data/2.5/weather?id=524901&&lang={ru}&units=metric&appid=87e746b4c32f8499d3712682045bc5ff";
        HttpURLConnection connection = null;
        StringBuilder response = null;
        try{
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputline;
                response = new StringBuilder();
                while((inputline = br.readLine()) != null){
                    response.append(inputline);
                }
                br.close();
            }
        }catch (IOException e){
            System.err.println(e);
        }finally {
            if(connection != null){
                connection.disconnect();
            }
        }

        if(response != null){
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject jsonMain = new JSONObject(jsonObject.getJSONObject("main").toString());
            Double degrese = jsonMain.getDouble("temp");
            int pressure = jsonMain.getInt("pressure");
            Date date = Calendar.getInstance().getTime();
            DBWriter d = new DBWriter(degrese, pressure, date);
        }
    }
}
