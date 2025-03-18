package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Playlist.CreatePlaylistRequest;
import com.beatspace.beatspace.models.Track.DeleteTrackRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public ResponseEntity<?> getSpotifyRefreshToken(@RequestBody Map<String,String> requestBody) throws IOException{
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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> getSpotifyToken ( @RequestBody Map<String, String> requestBody) throws IOException {
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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @GetMapping("/albuns/new-releases")
    public ResponseEntity<?> getAlbuns(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/browse/new-releases") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @GetMapping("/albuns/saved")
    public ResponseEntity<?> getAlbunsSaved(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @DeleteMapping("/albuns/remove")
    public ResponseEntity<?> removeAlbunsSaved(@RequestHeader("Authorization") String token,@RequestBody String saveAlbumRequest) throws IOException{


        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(saveAlbumRequest,MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .delete(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @PutMapping("/albuns/add")
    public ResponseEntity<?> addAlbunsSaved(@RequestHeader("Authorization") String token,@RequestBody String saveAlbumRequest) throws IOException{


        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(saveAlbumRequest,MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @DeleteMapping("/albuns/remove")
    public ResponseEntity<?> removeAlbunsSaved(@RequestHeader("Authorization") String token,@RequestBody String saveAlbumRequest) throws IOException{


        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(saveAlbumRequest,MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/albums") // API do Spotify
                .delete(requestBody)
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @GetMapping("/tracks/top")
    public ResponseEntity<?> getMyTopTracks(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/tracks") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @GetMapping("/artists/top")
    public ResponseEntity<?> getMyTopArtists(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/artists") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @GetMapping("/user/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) throws IOException{


        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @GetMapping("user/me/playlists")
    public ResponseEntity<?> getCurrentUserPlaylists(@RequestHeader("Authorization") String token) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/playlists") // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

    @DeleteMapping("playlists/{id}/tracks")
    public ResponseEntity<?> deletePlaylistTrack(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody(required = true) String deleteRequest) throws IOException{

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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @PostMapping("/playlists/{id}/create")
    public ResponseEntity<?> createPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody(required = true) CreatePlaylistRequest createRequest) throws IOException{

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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @PutMapping("/playlists/{id}/update")
    public ResponseEntity<?> updatePlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody(required = true) CreatePlaylistRequest createRequest) throws IOException{

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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @PutMapping("/playlists/{id}/images")
    public ResponseEntity<?> addCoverImagePlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody String base64) throws IOException{

        okhttp3.RequestBody requestBody = FormBody.create(base64.getBytes(), MediaType.parse("image/jpeg"));


        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id + "/images") // API do Spotify
                .put(requestBody)
                .addHeader("Authorization",token)
                .addHeader("Content-Type", "image/jpeg")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }


    @PostMapping("/playlists/{id}/tracks")
    public ResponseEntity<?> addItemsPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id,  @RequestBody String AddItemsPlaylistRequest) throws IOException{

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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }



    @GetMapping("playlists/{id}")
    public ResponseEntity<?> getPlaylist(@RequestHeader("Authorization") String token, @PathVariable("id") String id) throws IOException{

        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + id) // API do Spotify
                .get()
                .addHeader("Authorization",token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }





    @GetMapping("/search")
    public ResponseEntity<?> searchItem(@RequestHeader("Authorization") String token,@RequestParam("type") String type ,@RequestParam("query") String query) throws IOException{
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
                    if (!response.isSuccessful()) {
                        return ResponseEntity.status(response.code()).body(response.body().string());
                    }
                    return ResponseEntity.ok(response.body().string());
                }
            }


            @GetMapping("albums/{id}")
            public ResponseEntity<?> getAlbum(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws IOException{
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
                    if (!response.isSuccessful()) {
                        return ResponseEntity.status(response.code()).body(response.body().string());
                    }
                    return ResponseEntity.ok(response.body().string());
                }
            }

    @GetMapping("tracks/{id}")
    public ResponseEntity<?> getTrack(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws IOException{
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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }



    @GetMapping("/albuns/several")
    public ResponseEntity<?> getSeveralAlbuns(@RequestHeader("Authorization") String token,@RequestParam String ids) throws IOException{
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
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.body().string());
            }
            return ResponseEntity.ok(response.body().string());
        }
    }

}
