import weatherapis.OpenWeatherMap;

public class Main {
    public static void main(String[] args){
        OpenWeatherMap d = new OpenWeatherMap();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main(null);
    }
}
