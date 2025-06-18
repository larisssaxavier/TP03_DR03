package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExercicioAPI {
    public static void main(String[] args) throws IOException {
        HttpURLConnection conn = null;

        try {
            URL apiEntidades = new URL("https://apichallenges.eviltester.com/sim/entities");
            conn = (HttpURLConnection) apiEntidades.openConnection();
            conn.setRequestMethod("GET");
            int statusCode = conn.getResponseCode();
            System.out.println("Código de Status HTTP: " + statusCode);

            StringBuilder responseBody = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
            }
            System.out.println("Resposta do Servidor (JSON):");
            System.out.println(responseBody.toString());
        }
        catch (IOException e) {
            System.err.println("Ocorreu um erro ao fazer a requisição: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
