package com.iishanto.tooling.network.response;

import com.google.gson.Gson;

public class ToolingApiBaseResponse {

    public static <T> T fromJson(String response, Class<T> clazz) {
        System.out.println("Parsing response: " + response);
        return new Gson().fromJson(response, clazz);
    }
}
