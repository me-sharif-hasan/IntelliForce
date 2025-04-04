package com.iishanto.tooling.network;

import com.iishanto.tooling.network.response.SObjectsListResponse;
import com.iishanto.tooling.network.response.ToolingApiBaseResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ToolingApiHttpClient {
    private String baseUrl;
    private String accessToken;
    HttpClient client = HttpClient.newHttpClient();
    private static ToolingApiHttpClient instance;
    public static ToolingApiHttpClient getInstance(){
        if(instance==null){
            instance=new ToolingApiHttpClient();
        }
        return instance;
    }
    public void setBaseUrl(String baseUrl){
        this.baseUrl=baseUrl;
    }
    public void setAccessToken(String accessToken){
        this.accessToken=accessToken;
    }
    private ToolingApiHttpClient(){}

    public void get(String path, CompletableFuture<String> completableFuture) {
        System.out.println("Making get request to: "+baseUrl+path);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .GET()
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    System.out.println("Response: "+response);
                    completableFuture.complete(response);
                });
    }
}
