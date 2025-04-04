package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

// Class for record type info
public class RecordTypeInfo {
    @SerializedName("active")
    private boolean active;

    @SerializedName("available")
    private boolean available;

    @SerializedName("defaultRecordTypeMapping")
    private boolean defaultRecordTypeMapping;

    @SerializedName("developerName")
    private String developerName;

    @SerializedName("master")
    private boolean master;

    @SerializedName("name")
    private String name;

    @SerializedName("recordTypeId")
    private String recordTypeId;

    @SerializedName("urls")
    private RecordTypeUrls urls;

    // Getters and Setters
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public boolean isDefaultRecordTypeMapping() { return defaultRecordTypeMapping; }
    public void setDefaultRecordTypeMapping(boolean defaultRecordTypeMapping) { this.defaultRecordTypeMapping = defaultRecordTypeMapping; }
    public String getDeveloperName() { return developerName; }
    public void setDeveloperName(String developerName) { this.developerName = developerName; }
    public boolean isMaster() { return master; }
    public void setMaster(boolean master) { this.master = master; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRecordTypeId() { return recordTypeId; }
    public void setRecordTypeId(String recordTypeId) { this.recordTypeId = recordTypeId; }
    public RecordTypeUrls getUrls() { return urls; }
    public void setUrls(RecordTypeUrls urls) { this.urls = urls; }
}
