package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

// Class for supported scopes
public class SupportedScope {
    @SerializedName("label")
    private String label;

    @SerializedName("name")
    private String name;

    // Getters and Setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
