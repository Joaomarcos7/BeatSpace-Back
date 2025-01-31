package com.beatspace.beatspace.services;
import com.beatspace.beatspace.models.Token;
import com.beatspace.beatspace.util.Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Map;

@Service
public class SpotifyService {


    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;
    private final WebClient spotifyClient;


    public SpotifyService(WebClient spotifyClient){
        this.spotifyClient = spotifyClient;
    }

    public Token getAcessToken() throws Exception{
       String json = this.spotifyClient.post().uri("/token")
               .bodyValue(Map.of("grant_type","client_credentials","client_id",
                       clientId,"client_secret",clientSecret))
               .retrieve()
               .bodyToMono(String.class)
               .block();


        ObjectMapper objectMapper = new ObjectMapper();
        Token token = objectMapper.readValue(json,Token.class);
        return token;
    }

    public String login(){
        String state = Util.generateRandomString(16);
        String scope = "user-read-private user-read-email";

        String uri = UriComponentsBuilder.fromHttpUrl("https://accounts.spotify.com/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("scope", scope)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("state", state)
                .toUriString();

        return uri;
    }

}
