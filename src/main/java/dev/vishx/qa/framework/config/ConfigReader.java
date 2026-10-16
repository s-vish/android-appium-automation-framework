package dev.vishx.qa.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties = new Properties();

    public ConfigReader(){
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if(inputStream==null){
                throw new RuntimeException("config.properties not found in class path");
            }
            properties.load(inputStream);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to load config.properties",e);
        }
    }

    public String get(String key){
        String value = properties.getProperty(key);
        if(value==null || value.isBlank()){
            throw new RuntimeException("Configuration property not found: "+ key);
        }
        return value;
    }
}
