package com.spotify.oauth2.api.applicationApi.BeforeRefactorRestResource;

import com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFive;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResourceTen {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - added a generic spec builder for tokens getAccountRequestSpec() from SpecBuilderTen.java
     *         - moved the access_token from this file to PlaylistApiFive
     *  *****************************************************************************************/

    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer" +token).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec())
                .formParams(formParams)
                .log().all()
          .when().post("/api/token")
          .then().spec(SpecBuilderFive.getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec()).
                header("Authorization", "Bearer" +token).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                header("Authorization", "Bearer" +token).
                body(requestPlaylist).
        when().put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}
