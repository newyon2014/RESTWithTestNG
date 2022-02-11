package com.spotify.oauth2.api.BeforeRefactor;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTen {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - added a generic spec builder for tokens getAccountRequestSpec()
     *         - moved the access_token from this file to PlaylistApiFive
     *  *****************************************************************************************/



    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
    public static RequestSpecification getAccountRequestSpec(){
        return new RequestSpecBuilder().
                        setBaseUri("https://accounts.spotify.com").
                        setContentType(ContentType.URLENC).
                log(LogDetail.ALL).
                build();
    }
}
