package com.iishanto.server.hanlder;

import java.io.*;

public class LogHandler {
    InputStream inputStream;
    public LogHandler(InputStream inputStream){
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
                        System.out.printf("Apex - Log %s\n", output);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
