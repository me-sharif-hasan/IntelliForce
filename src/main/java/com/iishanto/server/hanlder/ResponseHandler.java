package com.iishanto.server.hanlder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Map;

public class ResponseHandler {
    InputStream inputStream;
    OutputStream outputStream;
    public ResponseHandler(InputStream inputStream, OutputStream outputStream){
        this.inputStream=inputStream;
        this.outputStream=outputStream;
    }
    public void initiate(Map<String,LspResponseListener> listenerRegistry){
        new Thread(() -> {
            try{
                int c;
                byte []headerMatcher=new byte[15];
                while ((c=inputStream.read())!=-1){
                    for(int i=1;i<headerMatcher.length;i++){
                        headerMatcher[i-1]=headerMatcher[i];
                    }
                    headerMatcher[14]= (byte) c;
                    String header=new String(headerMatcher);
                    if(header.toLowerCase().trim().equals("content-length:")){
                        //read next number
                        int numchar;
                        StringBuilder lenSegment= new StringBuilder();
                        while ((numchar=inputStream.read())!=-1){
                            if(numchar=='\r'){
                                break;
                            }else{
                                lenSegment.append((char) numchar);
                            }
                        }
                        int contentLength=Integer.parseInt(lenSegment.toString().trim());
                        byte []json=new byte[contentLength];
                        inputStream.skipNBytes(3);
                        numchar = inputStream.read(json,0,contentLength);
                        if(numchar!=-1){
                            String jsonStr=new String(json);
//                            System.out.println("parsed -- iishanto -- "+jsonStr);
                            JsonObject jsonObject=JsonParser.parseString(jsonStr).getAsJsonObject();
                            String method=null;
                            try{
                                method=jsonObject.get("method").getAsString();
                            }catch (Exception ignored){}
                            if(method!=null&&listenerRegistry.containsKey(method)){
                                listenerRegistry.get(method).listen(jsonObject);
                            }else{
                                try{
                                    for (String key:listenerRegistry.keySet()){
                                        LspResponseListener lspResponseListener=listenerRegistry.get(key);
                                        boolean isMatching=false;
                                        try{
                                            isMatching=lspResponseListener.isMatching(jsonObject);
                                        }catch (Throwable ignored){
                                        }
                                        if(isMatching){
                                            System.out.println("semantic delivery");
                                            lspResponseListener.listen(jsonObject);
                                            break;
                                        }
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    System.err.println("error: "+e.getLocalizedMessage());
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                System.err.println(e.getLocalizedMessage());
            }
        }).start();
    }
}
