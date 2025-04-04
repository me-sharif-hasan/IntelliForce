package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

// Class for record type URLs
public class RecordTypeUrls {
    @SerializedName("layout")
    private String layout;

    // Getters and Setters
    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
}
