package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

// Class for picklist values
public class PicklistValue {
    @SerializedName("active")
    private boolean active;

    @SerializedName("defaultValue")
    private boolean defaultValue;

    @SerializedName("label")
    private String label;

    @SerializedName("validFor")
    private String validFor;

    @SerializedName("value")
    private String value;

    // Getters and Setters
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public boolean isDefaultValue() { return defaultValue; }
    public void setDefaultValue(boolean defaultValue) { this.defaultValue = defaultValue; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getValidFor() { return validFor; }
    public void setValidFor(String validFor) { this.validFor = validFor; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
