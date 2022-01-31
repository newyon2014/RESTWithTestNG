package com.spotify.oauth2.api.BeforeRefactor;

import com.spotify.oauth2.api.Route;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderSeventeen {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - Allure report and rest assured integration to add more request and response info into
     *           the allure reports
     *  *****************************************************************************************/



    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath(Route.BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()). // Adding the filter criteria for allure reporting
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
