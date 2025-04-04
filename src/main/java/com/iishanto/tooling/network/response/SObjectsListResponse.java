package com.iishanto.tooling.network.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class SObjectsListResponse extends ToolingApiBaseResponse {
    @SerializedName("encoding")
    private String encoding;

    @SerializedName("maxBatchSize")
    private int maxBatchSize;

    @SerializedName("sobjects")
    private List<SObjectMetadata> sobjects;


    // Getters and Setters
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getMaxBatchSize() {
        return maxBatchSize;
    }

    public void setMaxBatchSize(int maxBatchSize) {
        this.maxBatchSize = maxBatchSize;
    }

    public List<SObjectMetadata> getSobjects() {
        return sobjects;
    }

    public void setSobjects(List<SObjectMetadata> sobjects) {
        this.sobjects = sobjects;
    }
}

