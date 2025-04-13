package commands;


import java.io.IOException;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;


public class HttpRequisitions {


    private static final String API_URL = "http://localhost:11434/api/generate";
    private Logger logger = Logger.getLogger(HttpRequisitions.class.getName());

    public String getAIResponse(String message) {
        
        OkHttpClient client = new OkHttpClient.Builder()
                                                .connectTimeout(10, TimeUnit.MINUTES) 
                                                .writeTimeout(10, TimeUnit.MINUTES)   
                                                .readTimeout(10, TimeUnit.MINUTES)    
                                                .build(); 
                                               
                                      
                                
        String json = "{"
        + "\"model\": \"mario\","
        + "\"prompt\": \"" + message + "\""
        + "}";

        RequestBody body = RequestBody.create(json, MediaType
                                                    .get("application/json"));

        Request request = new Request.Builder()
                                    .url(API_URL)
                                    .post(body)
                                    .build();

        try(Response response = client.newCall(request).execute()){

            if (!response.isSuccessful()) {
                logger.warning("Erro na API: " + response.code() + " - " + response.message());
                return "Erro na API: " + response.message();
            }
            if (response.body() == null) {
                logger.warning("Resposta da API está vazia.");
                return "Erro: Resposta da API está vazia.";
            }
            
            
            String responseBody = response.body().string();
            return processResponse(responseBody);

        } catch (IOException e){
            logger.warning("Erro ao conectar na API: " + e.getMessage());
             return "Erro ao conectar na API.";
        }

    }

    private String processResponse(String responseBody) {
        StringBuilder finalResponse = new StringBuilder();
        try {
         
            String[] lines = responseBody.split("\n");
            for (String line : lines) {
               JSONObject json = new JSONObject(line);
               String piece = json.optString("response", "");

               if (!piece.isEmpty()) {
                    finalResponse.append(piece);
                
               }

            }
        } catch (Exception e) {
            logger.warning("Erro ao processar a resposta da API: " + e.getMessage());
            return "Erro ao processar a resposta da IA.";
        }
        return finalResponse.toString().trim();
}

}
