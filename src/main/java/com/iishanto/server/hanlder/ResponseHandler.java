package com.iishanto.server.hanlder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResponseHandler {
    InputStream inputStream;
    public ResponseHandler(InputStream inputStream){
        this.inputStream=inputStream;
    }

    public void initiate() {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while (true){
                        String output = reader.readLine();
                        System.out.printf("Apex - Response %s\n", output);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
