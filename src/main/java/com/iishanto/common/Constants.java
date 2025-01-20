package com.iishanto.common;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public final class Constants {
    private Constants(){};
    public static final String LANGUAGE_ID="Apex";
    public static final Icon APEX_ICON= IconLoader.getIcon("/icons/salesforce.svg",Constants.class);
    public static final String CREATE_NEW_SALESFORCE_PROJECT_TEXT="Create a new Salesforce project";
    public static final String MODULE_ID ="SFDX_MODULE";
    public static final String MODULE_NAME ="SF Project";
}

