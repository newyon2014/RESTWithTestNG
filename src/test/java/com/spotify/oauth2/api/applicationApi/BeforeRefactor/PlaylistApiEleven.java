package com.spotify.oauth2.api.applicationApi.BeforeRefactor;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Route;
import com.spotify.oauth2.api.applicationApi.BeforeRefactorToken.TokenManagerEight;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.applicationApi.BeforeRefactorToken.TokenManagerEight.getToken;

public class PlaylistApiEleven {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - Hard coded End points are replaced with constant from Route class
     *         - Class created under src/test/java/api/BeforeRefactor folder
     *  *****************************************************************************************/

   // static String access_token = "PMAK-5ff2d720d2a39a004250e5da-c658c4a8a1cee3516762cb1a51cba6c5e2";

    public static Response post(PlaylistBuilderPatternThree requestPlaylist){
        return RestResource.post(Route.USERS + "/c0sb51wjod0fli2g7k0r2lc5e/" + Route.PLAYLISTS,
                                  getToken(),requestPlaylist);
    }

    public static Response post(String token, PlaylistBuilderPatternThree requestPlaylist){
        return RestResource.post(Route.USERS + "/c0sb51wjod0fli2g7k0r2lc5e/" + Route.PLAYLISTS,token,requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestResource.get(Route.PLAYLISTS + "/" + playlistId, TokenManagerEight.getToken());
    }

    public static Response update(String playlistId, PlaylistBuilderPatternThree requestPlaylist){
        return RestResource.update(Route.PLAYLISTS + "/" + playlistId,TokenManagerEight.getToken(),requestPlaylist);
    }
}
