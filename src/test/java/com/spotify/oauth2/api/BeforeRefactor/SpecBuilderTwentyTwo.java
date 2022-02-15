package com.spotify.oauth2.api.BeforeRefactor;

import com.spotify.oauth2.api.Route;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTwentyTwo {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - Updated to accommodate multiple environments instead of hardcoding the base URI
     *         - the value "https://api.spotify.com" will be passed from maven command line as follows:
     *             - mvn test -DBASE_URI="https://api.spotify.com" --> for getRequestSpec()
     *             - mvn test -DACCOUNT_BASE_URI="https://accounts.spotify.com" --> for getAccountRequestSpec()
     *         - Both can be run from maven command in parallel as it's enabled through maven surefire plug in (
     *            Parallel method - 10)
     *               - mvn test -DBASE_URI="https://api.spotify.com" -DACCOUNT_BASE_URI="https://accounts.spotify.com"
     *  *****************************************************************************************/



    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
               // setBaseUri("https://api.spotify.com").
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
                        setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                       // setBaseUri("https://accounts.spotify.com").
                        setContentType(ContentType.URLENC).
                log(LogDetail.ALL).
                build();
    }
}
