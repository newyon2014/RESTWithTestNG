package com.spotify.oauth2.api.applicationApi.BeforeRefactor;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.applicationApi.BeforeRefactorToken.TokenManagerSeven;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import io.restassured.response.Response;

public class PlaylistApiSeven {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - refactored with the renewToken() method from TokenManagerSeven.java
     *         - removed the hard coded access-token string from the playlist api class ( check ver. six file)
     *         - Class created under src/test/java/api/BeforeRefactor folder
     *  *****************************************************************************************/

   // static String access_token = "PMAK-5ff2d720d2a39a004250e5da-c658c4a8a1cee3516762cb1a51cba6c5e2";

    public static Response post(PlaylistBuilderPatternThree requestPlaylist){
        return RestResource.post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists", TokenManagerSeven.renewToken(),requestPlaylist);
    }

    public static Response post(String token, PlaylistBuilderPatternThree requestPlaylist){
        return RestResource.post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists",token,requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestResource.get("/playlists" + playlistId,TokenManagerSeven.renewToken());
    }

    public static Response update(String playlistId, PlaylistBuilderPatternThree requestPlaylist){
        return RestResource.update("/playlists" + playlistId,TokenManagerSeven.renewToken(),requestPlaylist);
    }
}
