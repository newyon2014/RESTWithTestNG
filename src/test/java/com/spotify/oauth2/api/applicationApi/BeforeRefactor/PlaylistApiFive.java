package com.spotify.oauth2.api.applicationApi.BeforeRefactor;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistTwo;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFour.getRequestSpec;
import static com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFour.getResponseSpec;
import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class PlaylistApiFive {
    static String access_token = "PMAK-5ff2d720d2a39a004250e5da-c658c4a8a1cee3516762cb1a51cba6c5e2";
    public static Response post(PlaylistBuilderPatternThree requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + access_token)
                //In this framework , assumption is that User account remain the same
          .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
          .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String token, PlaylistBuilderPatternThree requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization",  "Bearer " + token)
                //In this framework , assumption is that User account remain the same
           .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
           .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String playlistId){
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + access_token)
            .when().get("playlists/"+ playlistId)
            .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response update(String playlistId, PlaylistBuilderPatternThree requestPlaylist){
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + access_token)
                .body(requestPlaylist)
          .when().put("playlists/" + playlistId)
          .then().spec(getResponseSpec())
                .extract()
                .response();
    }
}
