
package com.spotify.oauth2.pojo.BeforeRefactor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.oauth2.pojo.ExternalUrls;
import com.spotify.oauth2.pojo.Followers;
import com.spotify.oauth2.pojo.Owner;
import com.spotify.oauth2.pojo.Tracks;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistBuilderPatternThree {
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

    public Boolean getCollaborative() {
        return collaborative;
    }

    public String getDescription() {
        return description;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public Followers getFollowers() {
        return followers;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public List<Object> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public Object getPrimaryColor() {
        return primaryColor;
    }

    public Boolean get_public() {
        return _public;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    public void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    public PlaylistBuilderPatternThree setDescription(String description) {
        this.description = description;
        return this;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public PlaylistBuilderPatternThree setName(String name) {
        this.name = name;
        return this;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setPrimaryColor(Object primaryColor) {
        this.primaryColor = primaryColor;
    }

    public PlaylistBuilderPatternThree set_public(Boolean _public) {
        this._public = _public;
        return this;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
