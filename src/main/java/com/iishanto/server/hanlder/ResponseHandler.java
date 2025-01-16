package com.iishanto.server.hanlder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ResponseHandler {
    InputStream inputStream;
    OutputStream outputStream;
    public ResponseHandler(InputStream inputStream, OutputStream outputStream){
        this.inputStream=inputStream;
        this.outputStream=outputStream;
    }
    public void initiate(Map<String,LspResponseListener> listenerRegistry) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true){
                    try{
                        outputStream.flush();
                        outputStream.flush();
                        outputStream.flush();
                        String output=bufferedReader.readLine();
                        if(output==null) continue;
                        System.out.printf("--Apex - Response %s--\n", output.split("\r\n\r\n")[0]);
                        try{
                            String json="";
                            int lastIdx=output.length()-1;
                            while (lastIdx>-1&&output.charAt(lastIdx)!='}') {
                                lastIdx--;
                            }
                            if(lastIdx==-1) continue;
                            json=output.substring(0,lastIdx+1);
                            System.out.println(json);
                            JsonObject jsonObject=JsonParser.parseString(json).getAsJsonObject();
                            String method=jsonObject.get("method").getAsString();
                            if(listenerRegistry.containsKey(method)){
                                listenerRegistry.get(method).listen(jsonObject);
                                System.out.println("Response "+method+" sent");
                            }
                            System.out.println(jsonObject.toString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
