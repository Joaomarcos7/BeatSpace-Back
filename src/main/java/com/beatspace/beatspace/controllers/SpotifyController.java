package com.beatspace.beatspace.controllers;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {


    @Value("${spotify.client.id}")  // Pegando do application.properties
    private String clientId;

    @Value("${spotify.client.secret}") // Pegando do application.properties
    private String clientSecret;

    @Value("${spotify.redirect.uri}") // Pegando do application.properties
    private String redirectUri;

    private final WebClient webClient;

    @Autowired
    public SpotifyController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://accounts.spotify.com").build();
    }

    @PostMapping("/token")
    public ResponseEntity<?> getSpotifyToken ( @RequestBody Map<String, String> requestBody) throws IOException {
        String code = requestBody.get("code"); // Código enviado pelo frontend

        OkHttpClient client = new OkHttpClient();

        // Codificação Base64 do client_id e client_secret
        String base64Auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));

        System.out.println(base64Auth);

        // Constrói o corpo da requisição corretamente
        okhttp3.RequestBody body = new FormBody.Builder()
                .add("code", code)
                .add("redirect_uri", "http://localhost:4200/home")
                .add("grant_type", "authorization_code")
                .build();

        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token") // API do Spotify
                .post(body)
                .addHeader("Authorization", "Basic " + base64Auth)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

}
