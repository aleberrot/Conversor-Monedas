package modelos;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ApiClient {
    private String apiKey;
    private String currency;
    public ApiClient(String apiKey) {
       this.apiKey = apiKey; 
       }
    
    public Exchange request(){
        HttpClient client = HttpClient.newHttpClient(); // 1. Crear el cliente
        // Construir URL
        String url = "https://v6.exchangerate-api.com/v6/%s/latest/%s".formatted(this.apiKey, this.currency);

        
        HttpRequest request = HttpRequest.newBuilder() // 2. Construir la solicitud
                .uri(URI.create(url))
                .GET() // O .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        
        try {
            // 3. Enviar y obtener la respuesta (síncrono)
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            Gson gson = new Gson();
            ExchangeResponse data = gson.fromJson(response.body(), ExchangeResponse.class);
            // 4. Procesar la respuesta
            if ("success".equalsIgnoreCase(data.result())){
              //System.out.println(data);
              Exchange exchange = new Exchange(data.base_code(), data.conversion_rates());
              return exchange;

            }

            // convertir la  respuesta a objeto java
            
            
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        return null;
    
    }
  // getter 
    public String getCurrency(){
      return this.currency;
    }
    public void setCurrency(String currency){
      this.currency  = currency;
  }
}
