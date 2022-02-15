
package com.spotify.oauth2.pojo.BeforeRefactor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.oauth2.pojo.ExternalUrls;
import com.spotify.oauth2.pojo.Followers;
import com.spotify.oauth2.pojo.Owner;
import com.spotify.oauth2.pojo.Tracks;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter @Setter
@Jacksonized // @Jacksonized & @Builder have to be used together
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistEighteen {
    @JsonProperty("collaborative")
    Boolean collaborative;
    @JsonProperty("description")
    String description;
    @JsonProperty("external_urls")
    ExternalUrls externalUrls;
    @JsonProperty("followers")
    Followers followers;
    @JsonProperty("href")
    String href;
    @JsonProperty("id")
    String id;
    @JsonProperty("images")
    List<Object> images;
    @JsonProperty("name")
    String name;
    @JsonProperty("owner")
    Owner owner;
    @JsonProperty("primary_color")
    Object primaryColor;
    @JsonProperty("public")
    Boolean _public;
    @JsonProperty("snapshot_id")
    String snapshotId;
    @JsonProperty("tracks")
    Tracks tracks;
    @JsonProperty("type")
    String type;
    @JsonProperty("uri")
    String uri;
}
