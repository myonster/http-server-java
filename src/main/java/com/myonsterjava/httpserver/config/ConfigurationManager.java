package com.myonsterjava.httpserver.config;

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

    public void loadConfigurationFile(String filePath) {

    }

    /**
     * Returns the current loaded configuration
     */

    public void getCurrentConfiguration() {

    }
}
