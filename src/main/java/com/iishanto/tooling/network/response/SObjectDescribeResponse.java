package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SObjectDescribeResponse extends ToolingApiBaseResponse {
    @SerializedName("actionOverrides")
    private List<Object> actionOverrides;

    @SerializedName("activateable")
    private boolean activateable;

    @SerializedName("associateEntityType")
    private String associateEntityType;

    @SerializedName("associateParentEntity")
    private String associateParentEntity;

    @SerializedName("childRelationships")
    private List<Object> childRelationships;

    @SerializedName("compactLayoutable")
    private boolean compactLayoutable;

    @SerializedName("createable")
    private boolean createable;

    @SerializedName("custom")
    private boolean custom;

    @SerializedName("customSetting")
    private boolean customSetting;

    @SerializedName("deepCloneable")
    private boolean deepCloneable;

    @SerializedName("defaultImplementation")
    private String defaultImplementation;

    @SerializedName("deletable")
    private boolean deletable;

    @SerializedName("deprecatedAndHidden")
    private boolean deprecatedAndHidden;

    @SerializedName("extendedBy")
    private String extendedBy;

    @SerializedName("extendsInterfaces")
    private String extendsInterfaces;

    @SerializedName("feedEnabled")
    private boolean feedEnabled;

    @SerializedName("fields")
    private List<FieldMetadata> fields;

    @SerializedName("hasSubtypes")
    private boolean hasSubtypes;

    @SerializedName("implementedBy")
    private String implementedBy;

    @SerializedName("implementsInterfaces")
    private String implementsInterfaces;

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

    @SerializedName("listviewable")
    private Boolean listviewable;

    @SerializedName("lookupLayoutable")
    private Boolean lookupLayoutable;

    @SerializedName("mergeable")
    private boolean mergeable;

    @SerializedName("mruEnabled")
    private boolean mruEnabled;

    @SerializedName("name")
    private String name;

    @SerializedName("namedLayoutInfos")
    private List<Object> namedLayoutInfos;

    @SerializedName("networkScopeFieldName")
    private String networkScopeFieldName;

    @SerializedName("queryable")
    private boolean queryable;

    @SerializedName("recordTypeInfos")
    private List<RecordTypeInfo> recordTypeInfos;

    @SerializedName("replicateable")
    private boolean replicateable;

    @SerializedName("retrieveable")
    private boolean retrieveable;

    @SerializedName("searchLayoutable")
    private boolean searchLayoutable;

    @SerializedName("searchable")
    private boolean searchable;

    @SerializedName("sobjectDescribeOption")
    private String sobjectDescribeOption;

    @SerializedName("supportedScopes")
    private List<SupportedScope> supportedScopes;

    @SerializedName("triggerable")
    private boolean triggerable;

    @SerializedName("undeletable")
    private boolean undeletable;

    @SerializedName("updateable")
    private boolean updateable;

    @SerializedName("urls")
    private SObjectUrls urls;

    // Getters and Setters
    public List<Object> getActionOverrides() { return actionOverrides; }
    public void setActionOverrides(List<Object> actionOverrides) { this.actionOverrides = actionOverrides; }
    public boolean isActivateable() { return activateable; }
    public void setActivateable(boolean activateable) { this.activateable = activateable; }
    public String getAssociateEntityType() { return associateEntityType; }
    public void setAssociateEntityType(String associateEntityType) { this.associateEntityType = associateEntityType; }
    public String getAssociateParentEntity() { return associateParentEntity; }
    public void setAssociateParentEntity(String associateParentEntity) { this.associateParentEntity = associateParentEntity; }
    public List<Object> getChildRelationships() { return childRelationships; }
    public void setChildRelationships(List<Object> childRelationships) { this.childRelationships = childRelationships; }
    public boolean isCompactLayoutable() { return compactLayoutable; }
    public void setCompactLayoutable(boolean compactLayoutable) { this.compactLayoutable = compactLayoutable; }
    public boolean isCreateable() { return createable; }
    public void setCreateable(boolean createable) { this.createable = createable; }
    public boolean isCustom() { return custom; }
    public void setCustom(boolean custom) { this.custom = custom; }
    public boolean isCustomSetting() { return customSetting; }
    public void setCustomSetting(boolean customSetting) { this.customSetting = customSetting; }
    public boolean isDeepCloneable() { return deepCloneable; }
    public void setDeepCloneable(boolean deepCloneable) { this.deepCloneable = deepCloneable; }
    public String getDefaultImplementation() { return defaultImplementation; }
    public void setDefaultImplementation(String defaultImplementation) { this.defaultImplementation = defaultImplementation; }
    public boolean isDeletable() { return deletable; }
    public void setDeletable(boolean deletable) { this.deletable = deletable; }
    public boolean isDeprecatedAndHidden() { return deprecatedAndHidden; }
    public void setDeprecatedAndHidden(boolean deprecatedAndHidden) { this.deprecatedAndHidden = deprecatedAndHidden; }
    public String getExtendedBy() { return extendedBy; }
    public void setExtendedBy(String extendedBy) { this.extendedBy = extendedBy; }
    public String getExtendsInterfaces() { return extendsInterfaces; }
    public void setExtendsInterfaces(String extendsInterfaces) { this.extendsInterfaces = extendsInterfaces; }
    public boolean isFeedEnabled() { return feedEnabled; }
    public void setFeedEnabled(boolean feedEnabled) { this.feedEnabled = feedEnabled; }
    public List<FieldMetadata> getFields() { return fields; }
    public void setFields(List<FieldMetadata> fields) { this.fields = fields; }
    public boolean isHasSubtypes() { return hasSubtypes; }
    public void setHasSubtypes(boolean hasSubtypes) { this.hasSubtypes = hasSubtypes; }
    public String getImplementedBy() { return implementedBy; }
    public void setImplementedBy(String implementedBy) { this.implementedBy = implementedBy; }
    public String getImplementsInterfaces() { return implementsInterfaces; }
    public void setImplementsInterfaces(String implementsInterfaces) { this.implementsInterfaces = implementsInterfaces; }
    public boolean isInterface() { return isInterface; }
    public void setInterface(boolean isInterface) { this.isInterface = isInterface; }
    public boolean isSubtype() { return isSubtype; }
    public void setSubtype(boolean isSubtype) { this.isSubtype = isSubtype; }
    public String getKeyPrefix() { return keyPrefix; }
    public void setKeyPrefix(String keyPrefix) { this.keyPrefix = keyPrefix; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getLabelPlural() { return labelPlural; }
    public void setLabelPlural(String labelPlural) { this.labelPlural = labelPlural; }
    public boolean isLayoutable() { return layoutable; }
    public void setLayoutable(boolean layoutable) { this.layoutable = layoutable; }
    public Boolean getListviewable() { return listviewable; }
    public void setListviewable(Boolean listviewable) { this.listviewable = listviewable; }
    public Boolean getLookupLayoutable() { return lookupLayoutable; }
    public void setLookupLayoutable(Boolean lookupLayoutable) { this.lookupLayoutable = lookupLayoutable; }
    public boolean isMergeable() { return mergeable; }
    public void setMergeable(boolean mergeable) { this.mergeable = mergeable; }
    public boolean isMruEnabled() { return mruEnabled; }
    public void setMruEnabled(boolean mruEnabled) { this.mruEnabled = mruEnabled; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Object> getNamedLayoutInfos() { return namedLayoutInfos; }
    public void setNamedLayoutInfos(List<Object> namedLayoutInfos) { this.namedLayoutInfos = namedLayoutInfos; }
    public String getNetworkScopeFieldName() { return networkScopeFieldName; }
    public void setNetworkScopeFieldName(String networkScopeFieldName) { this.networkScopeFieldName = networkScopeFieldName; }
    public boolean isQueryable() { return queryable; }
    public void setQueryable(boolean queryable) { this.queryable = queryable; }
    public List<RecordTypeInfo> getRecordTypeInfos() { return recordTypeInfos; }
    public void setRecordTypeInfos(List<RecordTypeInfo> recordTypeInfos) { this.recordTypeInfos = recordTypeInfos; }
    public boolean isReplicateable() { return replicateable; }
    public void setReplicateable(boolean replicateable) { this.replicateable = replicateable; }
    public boolean isRetrieveable() { return retrieveable; }
    public void setRetrieveable(boolean retrieveable) { this.retrieveable = retrieveable; }
    public boolean isSearchLayoutable() { return searchLayoutable; }
    public void setSearchLayoutable(boolean searchLayoutable) { this.searchLayoutable = searchLayoutable; }
    public boolean isSearchable() { return searchable; }
    public void setSearchable(boolean searchable) { this.searchable = searchable; }
    public String getSobjectDescribeOption() { return sobjectDescribeOption; }
    public void setSobjectDescribeOption(String sobjectDescribeOption) { this.sobjectDescribeOption = sobjectDescribeOption; }
    public List<SupportedScope> getSupportedScopes() { return supportedScopes; }
    public void setSupportedScopes(List<SupportedScope> supportedScopes) { this.supportedScopes = supportedScopes; }
    public boolean isTriggerable() { return triggerable; }
    public void setTriggerable(boolean triggerable) { this.triggerable = triggerable; }
    public boolean isUndeletable() { return undeletable; }
    public void setUndeletable(boolean undeletable) { this.undeletable = undeletable; }
    public boolean isUpdateable() { return updateable; }
    public void setUpdateable(boolean updateable) { this.updateable = updateable; }
    public SObjectUrls getUrls() { return urls; }
    public void setUrls(SObjectUrls urls) { this.urls = urls; }
}

