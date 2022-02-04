package com.spotify.oauth2.api.BeforeRefactor;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderFive {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - removed the add header section due to expired token scenario
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
}
