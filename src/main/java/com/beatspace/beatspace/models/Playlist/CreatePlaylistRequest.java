package com.beatspace.beatspace.models.Playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePlaylistRequest {

    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("public")
    public boolean isPublic;
}
