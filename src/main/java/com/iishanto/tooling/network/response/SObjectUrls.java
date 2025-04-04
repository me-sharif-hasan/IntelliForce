package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

// Class for the nested urls object
public class SObjectUrls {
    @SerializedName("rowTemplate")
    private String rowTemplate;

    @SerializedName("describe")
    private String describe;

    @SerializedName("sobject")
    private String sobject;

    // Getters and Setters
    public String getRowTemplate() {
        return rowTemplate;
    }

    public void setRowTemplate(String rowTemplate) {
        this.rowTemplate = rowTemplate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSobject() {
        return sobject;
    }

    public void setSobject(String sobject) {
        this.sobject = sobject;
    }
}
