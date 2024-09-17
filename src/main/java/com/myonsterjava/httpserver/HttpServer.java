package com.myonsterjava.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.myonsterjava.httpserver.config.Configuration;

import com.myonsterjava.httpserver.config.ConfigurationManager;

/**
 * 
 * Driver Class for HTTP Server
 * 
 */

public class HttpServer {
    public static void main(String [] args) {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using Port: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();
            
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO read

            // TODO write
            String html = "<html><head><title>Java HTTP server</title></head><body><h1>This page was served using Java HTTP server</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10

            String response = 
                "HTTP/1.1 200 OK" + CRLF +  // Status line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                "Content-Length" + html.getBytes().length + CRLF + // HEADER
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());
            
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
