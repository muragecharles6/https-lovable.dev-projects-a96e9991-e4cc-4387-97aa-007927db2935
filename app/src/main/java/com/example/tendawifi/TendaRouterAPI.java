import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TendaRouterAPI {
    private String routerIP;
    private String username;
    private String password;

    public TendaRouterAPI(String routerIP, String username, String password) {
        this.routerIP = routerIP;
        this.username = username;
        this.password = password;
    }

    private HttpURLConnection createConnection(String endpoint) throws IOException {
        URL url = new URL("http://" + routerIP + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    public String authenticate() throws IOException {
        HttpURLConnection connection = createConnection("/auth/login");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        String jsonInputString = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        connection.getOutputStream().write(jsonInputString.getBytes());
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String getStatus() throws IOException {
        HttpURLConnection connection = createConnection("/status");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}