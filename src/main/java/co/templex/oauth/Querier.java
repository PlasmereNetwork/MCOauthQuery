package co.templex.oauth;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Querier {

    private final JsonParser parser = new JsonParser();
    private final Gson gson = new Gson();

    @SneakyThrows(MalformedURLException.class)
    public OAuthResponse query(String token) throws IOException {
        if (token == null) {
            return null;
        }
        URL oauthURL = new URL("https://mc-oauth.net/api/api?token");
        HttpsURLConnection connection = (HttpsURLConnection) oauthURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("token", token);
        JsonElement response;
        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            response = parser.parse(reader);
        }
        if (response.getAsJsonObject().getAsJsonPrimitive("status").getAsString().equals("success")) {
            return gson.fromJson(response, SuccessfulOAuthResponse.class);
        } else {
            return gson.fromJson(response, OAuthResponse.class);
        }
    }

}
