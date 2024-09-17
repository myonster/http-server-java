package com.myonsterjava.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionWorkerThread extends Thread {
    private final static Logger LOGGER =  LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;
    

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

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
            
            /*
             * All the close will be handled in finally 
             */
            // inputStream.close();
            // outputStream.close();
            // socket.close();

            LOGGER.info(" * Connection Processing Finished.");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
