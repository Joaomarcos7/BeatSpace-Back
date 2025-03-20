package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Playlist.CreatePlaylistRequest;
import com.beatspace.beatspace.models.Track.DeleteTrackRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {


    @Value("${spotify.client.id}")  // Pegando do application.properties
    private String clientId;

    @Value("${spotify.client.secret}") // Pegando do application.properties
    private String clientSecret;

    @Value("${spotify.redirect.uri}") // Pegando do application.properties
    private String redirectUri;



    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper(); // Para converter o objeto em JSO

    @Autowired
    public SpotifyController() {
    }

    @PostMapping("/refresh-token")
    public String getSpotifyRefreshToken(@RequestBody Map<String,String> requestBody) throws IOException{
        String token = requestBody.get("refresh_token");


        // Codificação Base64 do client_id e client_secret
        String base64Auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));

        okhttp3.RequestBody body = new FormBody.Builder()
                .add("refresh_token", token)
                .add("grant_type", "refresh_token")
                .build();

        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token") // API do Spotify
                .post(body)
                .addHeader("Authorization", "Basic " + base64Auth)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @PostMapping("/token")
    public String getSpotifyToken ( @RequestBody Map<String, String> requestBody) throws IOException {
        String code = requestBody.get("code"); // Código enviado pelo frontend


        // Codificação Base64 do client_id e client_secret
        String base64Auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));


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
            return response.body().string();

        }
    }


    @GetMapping("/albuns/new-releases")
    public String getAlbuns(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/browse/new-releases") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @GetMapping("/albuns/saved")
    @Cacheable(value = "albunsSaved", key = "'albunsSaved'")

    public String getAlbunsSaved(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @DeleteMapping("/albuns/remove")
    public String removeAlbunsSaved(@RequestHeader("Authorization") String token,@RequestBody String saveAlbumRequest) throws IOException{


        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(saveAlbumRequest,MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .delete(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @PutMapping("/albuns/add")
    public String addAlbunsSaved(@RequestHeader("Authorization") String token,@RequestBody String saveAlbumRequest) throws IOException{


        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(saveAlbumRequest,MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    

    @GetMapping("/tracks/top")
    @Cacheable(value = "topTracks", key = "'topTracks'")

    public String getMyTopTracks(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/tracks") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @GetMapping("/artists/top")
    @Cacheable(value = "topArtists",key = "'topArtists'")

    public String getMyTopArtists(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/artists") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @GetMapping("/user/me")
    public String getCurrentUser(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @DeleteMapping("/unfollow")
    public String UnfollowUser(@RequestHeader("Authorization") String token,@RequestBody String UnfollowingRequest) throws IOException{
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(UnfollowingRequest,MediaType.get("application/json"));


        // Criando a URL com query parameters
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https") // Protocolo (http ou https)
                .host("api.spotify.com") // Host
                .addPathSegment("v1")
                .addPathSegment("me")
                .addPathSegment("following")
                .addQueryParameter("type","user")
                .build();

        Request request = new Request.Builder()
                .url(url) // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @GetMapping("/artist/{id}")
    @Cacheable(value = "artistDetails", key = "#id")

    public String GetArtist(@RequestHeader("Authorization") String token, @PathVariable("id") String id ) throws IOException{
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/artists/" + id) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @GetMapping("/isFollowed/{id}")

    public String checkIfFollowed(@RequestHeader("Authorization") String token,@PathVariable("id") String id) throws IOException{
// Criando a URL com query parameters
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https") // Protocolo (http ou https)
                .host("api.spotify.com") // Host
                .addPathSegment("v1")
                .addPathSegment("me")
                .addPathSegment("following")
                .addPathSegment("contains")
                .addQueryParameter("type","user")
                .addQueryParameter("ids",id)
                .build();

        Request request = new Request.Builder()
                .url(url) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @PutMapping("user/following/{type}")
    public String followUser(@RequestHeader("Authorization") String token,@RequestBody String FollowingRequest,@PathVariable("type") String type) throws IOException{

        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(FollowingRequest,MediaType.get("application/json"));


        // Criando a URL com query parameters
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https") // Protocolo (http ou https)
                .host("api.spotify.com") // Host
                .addPathSegment("v1")
                .addPathSegment("me")
                .addPathSegment("following")
                .addQueryParameter("type",type)
                .build();

        Request request = new Request.Builder()
                .url(url) // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @GetMapping("user/playlists/{id}")
    @Cacheable(value = "userPlaylists", key = "#id")
    public String getUsersPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/users/" + id + "/playlists") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }



    @GetMapping("/user/{userId}")
    @Cacheable(value = "userInfo",key = "#userId")
    public String getUser(@RequestHeader("Authorization") String token, @PathVariable("userId") String userId) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/users/" + userId) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @GetMapping("user/me/playlists")
    @Cacheable(value = "currentUserPlaylists",key = "'currentUserPlaylists'")

    public String getCurrentUserPlaylists(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/playlists") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }

    @DeleteMapping("playlists/{id}/tracks")
    public String deletePlaylistTrack(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody(required = true) String deleteRequest) throws IOException{

        // Cria o corpo da requisição DELETE
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(
                deleteRequest, MediaType.get("application/json"));
        System.out.println("https://api.spotify.com/v1/playlists/" + id + "/tracks");
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id + "/tracks") // API do Spotify
                .delete(requestBody)
                .addHeader("Authorization",token)
                .addHeader("Content-Type","application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @PostMapping("/playlists/{id}/create")
    public String createPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody(required = true) CreatePlaylistRequest createRequest) throws IOException{

// Converte o objeto DeleteTrackRequest para JSON
        String jsonRequestBody = objectMapper.writeValueAsString(createRequest);
        System.out.println("JSON enviado: " + objectMapper.writeValueAsString(createRequest));

        // Cria o corpo da requisição POST
        okhttp3.RequestBody requestBody = FormBody.create(jsonRequestBody,MediaType.get("application/json"));


        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/users/" + id + "/playlists") // API do Spotify
                .post(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @PutMapping("/playlists/{id}/update")
    public String updatePlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody(required = true) CreatePlaylistRequest createRequest) throws IOException{

// Converte o objeto DeleteTrackRequest para JSON
        String jsonRequestBody = objectMapper.writeValueAsString(createRequest);
        System.out.println("JSON enviado: " + objectMapper.writeValueAsString(createRequest));

        // Cria o corpo da requisição POST
        okhttp3.RequestBody requestBody = FormBody.create(jsonRequestBody,MediaType.get("application/json"));


        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id) // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @PutMapping("/playlists/{id}/images")
    public String addCoverImagePlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody String base64) throws IOException{

        okhttp3.RequestBody requestBody = FormBody.create(base64.getBytes(), MediaType.parse("image/jpeg"));


        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id + "/images") // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .addHeader("Content-Type", "image/jpeg")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }


    @PostMapping("/playlists/{id}/tracks")
    public String addItemsPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody String AddItemsPlaylistRequest) throws IOException{

        // Cria o corpo da requisição POST
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(
                AddItemsPlaylistRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id + "/tracks") // API do Spotify
                .post(requestBody)
                .addHeader("Authorization",token)
                .addHeader("Content-Type", "application/json") // Certifique-se de definir o tipo corretamente
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }



    @GetMapping("playlists/{id}")
    @Cacheable(value = "playlistsInfo",key = "#id")

    public String getPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {

            return response.body().string();

        }
    }





    @GetMapping("/search")
    @Cacheable(value= "searchItem", key = "{#type,#query}")
    public String searchItem(@RequestHeader("Authorization") String token,@RequestParam("type") String type ,@RequestParam("query") String query) throws IOException{
                // Criando a URL com query parameters
                HttpUrl url = new HttpUrl.Builder()
                        .scheme("https") // Protocolo (http ou https)
                        .host("api.spotify.com") // Host
                        .addPathSegment("v1")
                        .addPathSegment("search")
                        .addQueryParameter("type",type)
                        .addQueryParameter("q", query) // Parâmetro 2
                        .build();

                Request request = new Request.Builder()
                        .url(url) // API do Spotify
                        .get()
                        .addHeader("Authorization",token)
                        .build();


                try (Response response = client.newCall(request).execute()) {

                    return response.body().string();

                }
            }


            @GetMapping("albums/{id}")
            @Cacheable(value = "albumInfo",key = "#id")
            public String getAlbum(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws IOException{
                // Criando a URL com query parameters
                HttpUrl url = new HttpUrl.Builder()
                        .scheme("https") // Protocolo (http ou https)
                        .host("api.spotify.com") // Host
                        .addPathSegment("v1")
                        .addPathSegment("albums")
                        .addPathSegment(id)
                        .build();

                Request request = new Request.Builder()
                        .url(url) // API do Spotify
                        .get()
                        .addHeader("Authorization",token)
                        .build();


                try (Response response = client.newCall(request).execute()) {
                    return response.body().string();
                }
            }

    @GetMapping("tracks/{id}")
    @Cacheable(value = "trackInfo",key = "#id")
    public String getTrack(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws IOException{
        // Criando a URL com query parameters
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https") // Protocolo (http ou https)
                .host("api.spotify.com") // Host
                .addPathSegment("v1")
                .addPathSegment("tracks")
                .addPathSegment(id)
                .build();

        Request request = new Request.Builder()
                .url(url) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();


        try (Response response = client.newCall(request).execute()) {

           return response.body().string();
        }
    }



    @GetMapping("/albuns/several")
    @Cacheable(value = "SeveralAlbuns", key = "#ids")
    public String getSeveralAlbuns(@RequestHeader("Authorization") String token,@RequestParam String ids) throws IOException{
        // Criando a URL com query parameters
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https") // Protocolo (http ou https)
                .host("api.spotify.com") // Host
                .addPathSegment("v1")
                .addPathSegment("albums")
                .addQueryParameter("ids",ids)
                .build();

        Request request = new Request.Builder()
                .url(url) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();


        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
