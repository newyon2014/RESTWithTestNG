package com.spotify.oauth2.api.applicationApi.BeforeRefactorRestResource;

import com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFive;
import com.spotify.oauth2.api.Route;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResourceEightteen {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - Refactored the test case to use the auth().oauth2() method from RestAssured
     *  *****************************************************************************************/

    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec())
                .formParams(formParams)
                .log().all()
          .when().post(Route.API+ Route.TOKEN)
          .then().spec(SpecBuilderFive.getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec()).
                auth().oauth2(token).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                auth().oauth2(token).
                body(requestPlaylist).
        when().put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}
