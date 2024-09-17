package com.myonsterjava.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.myonsterjava.httpserver.util.Json;

public class ConfigurationManager {
    /**
     * We are using singleton here
     * 
     * Singleton: in OOP singleton pattern is a software design pattern that restricts the instantiation of a class to a singular instance.
     * 
     * Why singleton here?
     * We don't need more than 1 config manager, we only need one that will be shared across the project. 
     */
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;
    //
    
    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager==null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    /**
     *  Used to load a config file by the path provided
     */

    public void loadConfigurationFile(String filePath){
        // FileReader fileReader = new FileReader(filePath);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while( ( i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        // JsonNode conf = Json.parse(sb.toString());
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file", e);
        }

        //myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file, internal", e);
        }
    }

    /**
     * Returns the current loaded configuration
     */

    public Configuration getCurrentConfiguration() {
        if ( myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration Set");
        }

        return myCurrentConfiguration;
    }
}
