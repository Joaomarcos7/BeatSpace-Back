package com.beatspace.beatspace.models.Track;

import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.DeepRecursiveFunction;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.Delete;

import java.util.List;

@Getter
@Setter
public class DeleteTrackRequest {

    @JsonProperty("tracks")
    private List<Track> tracks;
    @JsonProperty("snapshot_id")
    private String snapshot_id;
    public void DeleteTrackRequest(){

    }

    // Classe interna para os Tracks
    public static class Track {

        public void Track(){

        }
        @JsonProperty("uri")
        private String uri;

        public String getUri() {
            return uri;
        }



        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
