package com.myonsterjava.httpserver;

import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.ServerSocket;
// import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myonsterjava.httpserver.config.Configuration;

import com.myonsterjava.httpserver.config.ConfigurationManager;
import com.myonsterjava.httpserver.core.ServerListenerThread;

/**
 * 
 * Driver Class for HTTP Server
 * 
 */

public class HttpServer {

    private final static Logger LOGGER =  LoggerFactory.getLogger(HttpServer.class);
    public static void main(String [] args) {
        // System.out.println("Server starting...");
        LOGGER.info("Server starting");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        // System.out.println("Using Port: " + conf.getPort());
        // System.out.println("Using Port: " + conf.getWebroot());
        LOGGER.info("Using Port: " + conf.getPort());
        LOGGER.info("Using Port: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
