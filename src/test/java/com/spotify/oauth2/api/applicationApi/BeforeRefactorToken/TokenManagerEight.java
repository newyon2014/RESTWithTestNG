package com.spotify.oauth2.api.applicationApi.BeforeRefactorToken;

import com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFive;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManagerEight {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - Added the Check Token expiry to the original file
     *         - Class created under src/test/java/api/applicationApi/BeforeRefactorToken folder
     *  *****************************************************************************************/
    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token ...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                // Adding buffer on the expiry time. Instead of waiting for full 3600 sec , we will wait for less time
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300); //
            } else{
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! Failed to get token");
        }
        return access_token;
    }

    private static Response renewToken(){
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", "18fdbf0c328448989601580ce4e0d7ca");
        formParams.put("client_secret", "f31ab0445ba241dd8a7fdef9be2fa081");
        formParams.put("refresh_token", "AQCzj--buTG0dudUBRtoF2n3DZrOEnaqKPS9myv8gmcxW6rOnp8vIV_p9XLbSAdzAkZXDujoAqYcFXGU96ctW_1v5Tezc6xXz-qjcHp4JyYOG7CjBGoF81BTKVbzxInqmD8");
        formParams.put("grant_type", "refresh_token");

        Response response = given()
                .baseUri("https://accounts.spotify.com")
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .log().all()
           .when()
           .post("/api/token")
                .then()
           .spec(SpecBuilderFive.getResponseSpec())
                .extract()
                .response();

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;
    }
}
