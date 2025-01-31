package com.beatspace.beatspace.config;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Bean;
        import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpotifyClientConfig{
    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl("\"https://accounts.spotify.com/api/").build();
    }
}

