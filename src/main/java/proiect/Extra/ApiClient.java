package proiect.Extra;

import com.google.gson.Gson;
import okhttp3.*;
import proiect.DatabaseTables.Users;

import java.io.IOException;

public class ApiClient {

    private final OkHttpClient client;
    private static String baseUrl= "http://localhost:8080";
    private final Gson gson;
    public ApiClient(String baseUrl) {
        this.client = new OkHttpClient();
        ApiClient.baseUrl = baseUrl;
        this.gson=new Gson();
    }

    public Users registerUser(Users newUser) throws IOException {
        String jsonRequestBody = gson.toJson(newUser);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonRequestBody
        );

        Request request = new Request.Builder()
                .url(baseUrl + "/users/register")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return gson.fromJson(responseBody, Users.class);
            } else {
                return null;
            }
        }
    }

    public Users authenticateUser(String email, String password) throws IOException {
        String jsonRequestBody = gson.toJson(new Users(email, password));

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonRequestBody
        );

        Request request = new Request.Builder()
                .url(baseUrl + "/users/login")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return gson.fromJson(responseBody, Users.class);
            } else {
                return null;
            }
        }
    }

}
