package com.spotify.oauth2.api.applicationApi.BeforeRefactorToken;

import com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFive;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class TokenManagerSeven {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - Original design of the Token Manager Class
     *         - Class created under src/test/java/api/applicationApi/BeforeRefactorToken folder
     *  *****************************************************************************************/
    public static String renewToken(){
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", "18fdbf0c328448989601580ce4e0d7ca");
        formParams.put("client_secret", "f31ab0445ba241dd8a7fdef9be2fa081");
        formParams.put("refresh_token", "AQCzj--buTG0dudUBRtoF2n3DZrOEnaqKPS9myv8gmcxW6rOnp8vIV_p9XLbSAdzAkZXDujoAqYcFXGU96ctW_1v5Tezc6xXz-qjcHp4JyYOG7CjBGoF81BTKVbzxInqmD8");
        formParams.put("grant_type", "refresh_token");

        Response response = given()
                .baseUri("https://accounts.spotify.com")
                .contentType(ContentType.URLENC)
                .formParams(formParams)
           .when()
           .post("/api/token")
                .then()
           .spec(SpecBuilderFive.getResponseSpec())
                .extract()
                .response();

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response.path("access_token");
    }
}
