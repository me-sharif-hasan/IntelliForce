package com.iishanto.tooling.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Class for field metadata
public class FieldMetadata {
    @SerializedName("aggregatable")
    private boolean aggregatable;

    @SerializedName("aiPredictionField")
    private boolean aiPredictionField;

    @SerializedName("autoNumber")
    private boolean autoNumber;

    @SerializedName("byteLength")
    private int byteLength;

    @SerializedName("calculated")
    private boolean calculated;

    @SerializedName("calculatedFormula")
    private String calculatedFormula;

    @SerializedName("cascadeDelete")
    private boolean cascadeDelete;

    @SerializedName("caseSensitive")
    private boolean caseSensitive;

    @SerializedName("compoundFieldName")
    private String compoundFieldName;

    @SerializedName("controllerName")
    private String controllerName;

    @SerializedName("createable")
    private boolean createable;

    @SerializedName("custom")
    private boolean custom;

    @SerializedName("defaultValue")
    private Object defaultValue;

    @SerializedName("defaultValueFormula")
    private String defaultValueFormula;

    @SerializedName("defaultedOnCreate")
    private boolean defaultedOnCreate;

    @SerializedName("dependentPicklist")
    private boolean dependentPicklist;

    @SerializedName("deprecatedAndHidden")
    private boolean deprecatedAndHidden;

    @SerializedName("digits")
    private int digits;

    @SerializedName("displayLocationInDecimal")
    private boolean displayLocationInDecimal;

    @SerializedName("encrypted")
    private boolean encrypted;

    @SerializedName("externalId")
    private boolean externalId;

    @SerializedName("extraTypeInfo")
    private String extraTypeInfo;

    @SerializedName("filterable")
    private boolean filterable;

    @SerializedName("filteredLookupInfo")
    private Object filteredLookupInfo;

    @SerializedName("formulaTreatNullNumberAsZero")
    private boolean formulaTreatNullNumberAsZero;

    @SerializedName("groupable")
    private boolean groupable;

    @SerializedName("highScaleNumber")
    private boolean highScaleNumber;

    @SerializedName("htmlFormatted")
    private boolean htmlFormatted;

    @SerializedName("idLookup")
    private boolean idLookup;

    @SerializedName("inlineHelpText")
    private String inlineHelpText;

    @SerializedName("label")
    private String label;

    @SerializedName("length")
    private int length;

    @SerializedName("mask")
    private String mask;

    @SerializedName("maskType")
    private String maskType;

    @SerializedName("name")
    private String name;

    @SerializedName("nameField")
    private boolean nameField;

    @SerializedName("namePointing")
    private boolean namePointing;

    @SerializedName("nillable")
    private boolean nillable;

    @SerializedName("permissionable")
    private boolean permissionable;

    @SerializedName("picklistValues")
    private List<PicklistValue> picklistValues;

    @SerializedName("polymorphicForeignKey")
    private boolean polymorphicForeignKey;

    @SerializedName("precision")
    private int precision;

    @SerializedName("queryByDistance")
    private boolean queryByDistance;

    @SerializedName("referenceTargetField")
    private String referenceTargetField;

    @SerializedName("referenceTo")
    private List<String> referenceTo;

    @SerializedName("relationshipName")
    private String relationshipName;

    @SerializedName("relationshipOrder")
    private Integer relationshipOrder;

    @SerializedName("restrictedDelete")
    private boolean restrictedDelete;

    @SerializedName("restrictedPicklist")
    private boolean restrictedPicklist;

    @SerializedName("scale")
    private int scale;

    @SerializedName("searchPrefilterable")
    private boolean searchPrefilterable;

    @SerializedName("soapType")
    private String soapType;

    @SerializedName("sortable")
    private boolean sortable;

    @SerializedName("type")
    private String type;

    @SerializedName("unique")
    private boolean unique;

    @SerializedName("updateable")
    private boolean updateable;

    @SerializedName("writeRequiresMasterRead")
    private boolean writeRequiresMasterRead;

    // Getters and Setters
    public boolean isAggregatable() { return aggregatable; }
    public void setAggregatable(boolean aggregatable) { this.aggregatable = aggregatable; }
    public boolean isAiPredictionField() { return aiPredictionField; }
    public void setAiPredictionField(boolean aiPredictionField) { this.aiPredictionField = aiPredictionField; }
    public boolean isAutoNumber() { return autoNumber; }
    public void setAutoNumber(boolean autoNumber) { this.autoNumber = autoNumber; }
    public int getByteLength() { return byteLength; }
    public void setByteLength(int byteLength) { this.byteLength = byteLength; }
    public boolean isCalculated() { return calculated; }
    public void setCalculated(boolean calculated) { this.calculated = calculated; }
    public String getCalculatedFormula() { return calculatedFormula; }
    public void setCalculatedFormula(String calculatedFormula) { this.calculatedFormula = calculatedFormula; }
    public boolean isCascadeDelete() { return cascadeDelete; }
    public void setCascadeDelete(boolean cascadeDelete) { this.cascadeDelete = cascadeDelete; }
    public boolean isCaseSensitive() { return caseSensitive; }
    public void setCaseSensitive(boolean caseSensitive) { this.caseSensitive = caseSensitive; }
    public String getCompoundFieldName() { return compoundFieldName; }
    public void setCompoundFieldName(String compoundFieldName) { this.compoundFieldName = compoundFieldName; }
    public String getControllerName() { return controllerName; }
    public void setControllerName(String controllerName) { this.controllerName = controllerName; }
    public boolean isCreateable() { return createable; }
    public void setCreateable(boolean createable) { this.createable = createable; }
    public boolean isCustom() { return custom; }
    public void setCustom(boolean custom) { this.custom = custom; }
    public Object getDefaultValue() { return defaultValue; }
    public void setDefaultValue(Object defaultValue) { this.defaultValue = defaultValue; }
    public String getDefaultValueFormula() { return defaultValueFormula; }
    public void setDefaultValueFormula(String defaultValueFormula) { this.defaultValueFormula = defaultValueFormula; }
    public boolean isDefaultedOnCreate() { return defaultedOnCreate; }
    public void setDefaultedOnCreate(boolean defaultedOnCreate) { this.defaultedOnCreate = defaultedOnCreate; }
    public boolean isDependentPicklist() { return dependentPicklist; }
    public void setDependentPicklist(boolean dependentPicklist) { this.dependentPicklist = dependentPicklist; }
    public boolean isDeprecatedAndHidden() { return deprecatedAndHidden; }
    public void setDeprecatedAndHidden(boolean deprecatedAndHidden) { this.deprecatedAndHidden = deprecatedAndHidden; }
    public int getDigits() { return digits; }
    public void setDigits(int digits) { this.digits = digits; }
    public boolean isDisplayLocationInDecimal() { return displayLocationInDecimal; }
    public void setDisplayLocationInDecimal(boolean displayLocationInDecimal) { this.displayLocationInDecimal = displayLocationInDecimal; }
    public boolean isEncrypted() { return encrypted; }
    public void setEncrypted(boolean encrypted) { this.encrypted = encrypted; }
    public boolean isExternalId() { return externalId; }
    public void setExternalId(boolean externalId) { this.externalId = externalId; }
    public String getExtraTypeInfo() { return extraTypeInfo; }
    public void setExtraTypeInfo(String extraTypeInfo) { this.extraTypeInfo = extraTypeInfo; }
    public boolean isFilterable() { return filterable; }
    public void setFilterable(boolean filterable) { this.filterable = filterable; }
    public Object getFilteredLookupInfo() { return filteredLookupInfo; }
    public void setFilteredLookupInfo(Object filteredLookupInfo) { this.filteredLookupInfo = filteredLookupInfo; }
    public boolean isFormulaTreatNullNumberAsZero() { return formulaTreatNullNumberAsZero; }
    public void setFormulaTreatNullNumberAsZero(boolean formulaTreatNullNumberAsZero) { this.formulaTreatNullNumberAsZero = formulaTreatNullNumberAsZero; }
    public boolean isGroupable() { return groupable; }
    public void setGroupable(boolean groupable) { this.groupable = groupable; }
    public boolean isHighScaleNumber() { return highScaleNumber; }
    public void setHighScaleNumber(boolean highScaleNumber) { this.highScaleNumber = highScaleNumber; }
    public boolean isHtmlFormatted() { return htmlFormatted; }
    public void setHtmlFormatted(boolean htmlFormatted) { this.htmlFormatted = htmlFormatted; }
    public boolean isIdLookup() { return idLookup; }
    public void setIdLookup(boolean idLookup) { this.idLookup = idLookup; }
    public String getInlineHelpText() { return inlineHelpText; }
    public void setInlineHelpText(String inlineHelpText) { this.inlineHelpText = inlineHelpText; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public String getMask() { return mask; }
    public void setMask(String mask) { this.mask = mask; }
    public String getMaskType() { return maskType; }
    public void setMaskType(String maskType) { this.maskType = maskType; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isNameField() { return nameField; }
    public void setNameField(boolean nameField) { this.nameField = nameField; }
    public boolean isNamePointing() { return namePointing; }
    public void setNamePointing(boolean namePointing) { this.namePointing = namePointing; }
    public boolean isNillable() { return nillable; }
    public void setNillable(boolean nillable) { this.nillable = nillable; }
    public boolean isPermissionable() { return permissionable; }
    public void setPermissionable(boolean permissionable) { this.permissionable = permissionable; }
    public List<PicklistValue> getPicklistValues() { return picklistValues; }
    public void setPicklistValues(List<PicklistValue> picklistValues) { this.picklistValues = picklistValues; }
    public boolean isPolymorphicForeignKey() { return polymorphicForeignKey; }
    public void setPolymorphicForeignKey(boolean polymorphicForeignKey) { this.polymorphicForeignKey = polymorphicForeignKey; }
    public int getPrecision() { return precision; }
    public void setPrecision(int precision) { this.precision = precision; }
    public boolean isQueryByDistance() { return queryByDistance; }
    public void setQueryByDistance(boolean queryByDistance) { this.queryByDistance = queryByDistance; }
    public String getReferenceTargetField() { return referenceTargetField; }
    public void setReferenceTargetField(String referenceTargetField) { this.referenceTargetField = referenceTargetField; }
    public List<String> getReferenceTo() { return referenceTo; }
    public void setReferenceTo(List<String> referenceTo) { this.referenceTo = referenceTo; }
    public String getRelationshipName() { return relationshipName; }
    public void setRelationshipName(String relationshipName) { this.relationshipName = relationshipName; }
    public Integer getRelationshipOrder() { return relationshipOrder; }
    public void setRelationshipOrder(Integer relationshipOrder) { this.relationshipOrder = relationshipOrder; }
    public boolean isRestrictedDelete() { return restrictedDelete; }
    public void setRestrictedDelete(boolean restrictedDelete) { this.restrictedDelete = restrictedDelete; }
    public boolean isRestrictedPicklist() { return restrictedPicklist; }
    public void setRestrictedPicklist(boolean restrictedPicklist) { this.restrictedPicklist = restrictedPicklist; }
    public int getScale() { return scale; }
    public void setScale(int scale) { this.scale = scale; }
    public boolean isSearchPrefilterable() { return searchPrefilterable; }
    public void setSearchPrefilterable(boolean searchPrefilterable) { this.searchPrefilterable = searchPrefilterable; }
    public String getSoapType() { return soapType; }
    public void setSoapType(String soapType) { this.soapType = soapType; }
    public boolean isSortable() { return sortable; }
    public void setSortable(boolean sortable) { this.sortable = sortable; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean isUnique() { return unique; }
    public void setUnique(boolean unique) { this.unique = unique; }
    public boolean isUpdateable() { return updateable; }
    public void setUpdateable(boolean updateable) { this.updateable = updateable; }
    public boolean isWriteRequiresMasterRead() { return writeRequiresMasterRead; }
    public void setWriteRequiresMasterRead(boolean writeRequiresMasterRead) { this.writeRequiresMasterRead = writeRequiresMasterRead; }
}
