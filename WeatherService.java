import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class WeatherService {

    private final String apiKey;
    private final HttpClient httpClient;

    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getWeatherData(String city) {
        try {
            // Construir la URL de la API
            String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apiKey);

            // Crear una solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Analizar y devolver los datos del tiempo
            return parseWeatherResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener los datos del tiempo.";
        }
    }

    private String parseWeatherResponse(String response) {
        // Convertir la respuesta en un objeto JSON
        JSONObject jsonObject = new JSONObject(response);

        // Extraer y devolver los datos necesarios
       
        String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = jsonObject.getJSONObject("main").getDouble("temp");

        return "Descripción: " + weatherDescription + "\nTemperatura: " + temperature + "°C";
    }
}


