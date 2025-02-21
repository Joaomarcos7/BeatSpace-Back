package com.beatspace.beatspace.models.Playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddItemsPlaylistRequest {

    @JsonProperty("uris")
    public List<String> uris;
    @JsonProperty("position")
    public int position;
}
