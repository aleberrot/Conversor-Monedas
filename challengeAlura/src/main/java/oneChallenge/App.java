package oneChallenge;
import modelos.ConversorApp;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) {
      	ConversorApp.doConversion();
        //HttpClient client = HttpClient.newHttpClient(); // 1. Crear el cliente
        
        //HttpRequest request = HttpRequest.newBuilder() // 2. Construir la solicitud
        //        .uri(URI.create("https://v6.exchangerate-api.com/v6/65e6956dca87c9ec438a2c89/latest/CLP"))
        //        .GET() // O .method("GET", HttpRequest.BodyPublishers.noBody())
         //       .build();

        
        //try {
            // 3. Enviar y obtener la respuesta (síncrono)
        //    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // 4. Procesar la respuesta
        //    System.out.println("Estado de la respuesta: " + response.statusCode());
        //    System.out.println("Cuerpo de la respuesta: " + response.body());
            
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}  
    }
}
