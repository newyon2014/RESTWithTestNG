package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
    // this class insures that the properties are loaded only once in the framework

    private final Properties properties;
    private static ConfigLoader configLoader;

    // private constructor Ensures that an object of this class is not created outside this class
    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    // A single object of this class is created only once
    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader(); // calls the private constructor and load the properties file ONLy ONCE.
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }

    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if(prop != null) return prop;
        else throw new RuntimeException("property grant_type is not specified in the config.properties file");
    }

    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if(prop != null) return prop;
        else throw new RuntimeException("property refresh_token is not specified in the config.properties file");
    }

    public String getUser(){
        String prop = properties.getProperty("user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }

}