package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

// Class for each SObject in the sobjects array
public class SObjectMetadata {
    @SerializedName("activateable")
    private boolean activateable;

    @SerializedName("associateEntityType")
    private String associateEntityType;

    @SerializedName("associateParentEntity")
    private String associateParentEntity;

    @SerializedName("createable")
    private boolean createable;

    @SerializedName("custom")
    private boolean custom;

    @SerializedName("customSetting")
    private boolean customSetting;

    @SerializedName("deepCloneable")
    private boolean deepCloneable;

    @SerializedName("deletable")
    private boolean deletable;

    @SerializedName("deprecatedAndHidden")
    private boolean deprecatedAndHidden;

    @SerializedName("feedEnabled")
    private boolean feedEnabled;

    @SerializedName("hasSubtypes")
    private boolean hasSubtypes;

    @SerializedName("isInterface")
    private boolean isInterface;

    @SerializedName("isSubtype")
    private boolean isSubtype;

    @SerializedName("keyPrefix")
    private String keyPrefix;

    @SerializedName("label")
    private String label;

    @SerializedName("labelPlural")
    private String labelPlural;

    @SerializedName("layoutable")
    private boolean layoutable;

    @SerializedName("mergeable")
    private boolean mergeable;

    @SerializedName("mruEnabled")
    private boolean mruEnabled;

    @SerializedName("name")
    private String name;

    @SerializedName("queryable")
    private boolean queryable;

    @SerializedName("replicateable")
    private boolean replicateable;

    @SerializedName("retrieveable")
    private boolean retrieveable;

    @SerializedName("searchable")
    private boolean searchable;

    @SerializedName("triggerable")
    private boolean triggerable;

    @SerializedName("undeletable")
    private boolean undeletable;

    @SerializedName("updateable")
    private boolean updateable;

    @SerializedName("urls")
    private SObjectUrls urls;

    // Getters and Setters
    public boolean isActivateable() {
        return activateable;
    }

    public void setActivateable(boolean activateable) {
        this.activateable = activateable;
    }

    public String getAssociateEntityType() {
        return associateEntityType;
    }

    public void setAssociateEntityType(String associateEntityType) {
        this.associateEntityType = associateEntityType;
    }

    public String getAssociateParentEntity() {
        return associateParentEntity;
    }

    public void setAssociateParentEntity(String associateParentEntity) {
        this.associateParentEntity = associateParentEntity;
    }

    public boolean isCreateable() {
        return createable;
    }

    public void setCreateable(boolean createable) {
        this.createable = createable;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public boolean isCustomSetting() {
        return customSetting;
    }

    public void setCustomSetting(boolean customSetting) {
        this.customSetting = customSetting;
    }

    public boolean isDeepCloneable() {
        return deepCloneable;
    }

    public void setDeepCloneable(boolean deepCloneable) {
        this.deepCloneable = deepCloneable;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public boolean isDeprecatedAndHidden() {
        return deprecatedAndHidden;
    }

    public void setDeprecatedAndHidden(boolean deprecatedAndHidden) {
        this.deprecatedAndHidden = deprecatedAndHidden;
    }

    public boolean isFeedEnabled() {
        return feedEnabled;
    }

    public void setFeedEnabled(boolean feedEnabled) {
        this.feedEnabled = feedEnabled;
    }

    public boolean isHasSubtypes() {
        return hasSubtypes;
    }

    public void setHasSubtypes(boolean hasSubtypes) {
        this.hasSubtypes = hasSubtypes;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }

    public boolean isSubtype() {
        return isSubtype;
    }

    public void setSubtype(boolean isSubtype) {
        this.isSubtype = isSubtype;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelPlural() {
        return labelPlural;
    }

    public void setLabelPlural(String labelPlural) {
        this.labelPlural = labelPlural;
    }

    public boolean isLayoutable() {
        return layoutable;
    }

    public void setLayoutable(boolean layoutable) {
        this.layoutable = layoutable;
    }

    public boolean isMergeable() {
        return mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

    public boolean isMruEnabled() {
        return mruEnabled;
    }

    public void setMruEnabled(boolean mruEnabled) {
        this.mruEnabled = mruEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isQueryable() {
        return queryable;
    }

    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }

    public boolean isReplicateable() {
        return replicateable;
    }

    public void setReplicateable(boolean replicateable) {
        this.replicateable = replicateable;
    }

    public boolean isRetrieveable() {
        return retrieveable;
    }

    public void setRetrieveable(boolean retrieveable) {
        this.retrieveable = retrieveable;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isTriggerable() {
        return triggerable;
    }

    public void setTriggerable(boolean triggerable) {
        this.triggerable = triggerable;
    }

    public boolean isUndeletable() {
        return undeletable;
    }

    public void setUndeletable(boolean undeletable) {
        this.undeletable = undeletable;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public SObjectUrls getUrls() {
        return urls;
    }

    public void setUrls(SObjectUrls urls) {
        this.urls = urls;
    }
}
