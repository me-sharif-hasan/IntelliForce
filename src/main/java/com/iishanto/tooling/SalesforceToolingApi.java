package com.iishanto.tooling;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iishanto.ide.utility.CliExecutor;
import com.iishanto.ide.utility.ScriptRunnerUtility;
import com.iishanto.tooling.json.JsonExtractor;
import com.iishanto.tooling.network.ToolingApiHttpClient;
import com.iishanto.tooling.network.response.SObjectsListResponse;
import com.iishanto.tooling.network.response.ToolingApiBaseResponse;
import com.iishanto.tooling.sync.SObjectSynchronizationHelper;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.project.Project;

import java.util.concurrent.CompletableFuture;

public class SalesforceToolingApi {
    private static SalesforceToolingApi instance;
    private String accessToken;
    private String instanceUrl;
    private ToolingApiHttpClient toolingApiHttpClient;
    private Project project;
    public static SalesforceToolingApi getInstance(Project project) throws Exception {
        if (instance == null) {
            instance = new SalesforceToolingApi(project);
        }
        return instance;
    }

    private SalesforceToolingApi(Project project) throws Exception {
        this.project = project;
        refreshAccessToken(project);
    }
    public void refreshAccessToken(Project project) throws Exception {
        System.out.println("Refreshing access token");
        CompletableFuture<JsonObject> completableFuture=new CompletableFuture<>();
        CliExecutor.getInstance().executeWithOutput(project, "Refreshing access token", new CliExecutor.CliOutputListener() {
            @Override
            public void onOutputAvailable(String output) {
                String json = JsonExtractor.extractJson(output);
                System.out.println("Output refreshed: " + json);
                JsonElement element = JsonParser.parseString(json);
                JsonObject object = element.getAsJsonObject();
                completableFuture.complete(object);
            }
        },"org", "display", "--json");
        JsonObject jsonObject = completableFuture.get();
        accessToken = jsonObject.get("result").getAsJsonObject().get("accessToken").getAsString();
        instanceUrl = jsonObject.get("result").getAsJsonObject().get("instanceUrl").getAsString();
        toolingApiHttpClient = ToolingApiHttpClient.getInstance();
        toolingApiHttpClient.setBaseUrl(instanceUrl);
        toolingApiHttpClient.setAccessToken(accessToken);
    }

    public void getStandardObjectList() {
        System.out.println("Getting standard object list "+accessToken);
        CompletableFuture<String> responseCompletableFuture = new CompletableFuture<>();
        toolingApiHttpClient.get("/services/data/v59.0/sobjects", responseCompletableFuture);
        SObjectsListResponse response = SObjectsListResponse.fromJson(responseCompletableFuture.join(),SObjectsListResponse.class);
        System.out.println("Response Entities: " +response.getSobjects().get(0).getName());
        SObjectSynchronizationHelper.startSync(project,response.getSobjects());
    }

    public void executeAnonymous(String script) {
        System.out.println("Executing anonymous script: " + script);
    }

    public void compile(String script) {
        System.out.println("Compiling script: " + script);
    }

    public void execute(String script) {
        System.out.println("Executing script: " + script);
    }
}
