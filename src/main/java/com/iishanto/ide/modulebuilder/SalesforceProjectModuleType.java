package com.iishanto.ide.modulebuilder;

import com.iishanto.common.Constants;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SalesforceProjectModuleType extends ModuleType<SalesforceProjectBuilder> {
    public SalesforceProjectModuleType() {
        super(Constants.MODULE_ID);
    }

    @Override
    public @NotNull SalesforceProjectBuilder createModuleBuilder() {
        return new SalesforceProjectBuilder();
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getName() {
        return Constants.MODULE_NAME;
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getDescription() {
        return Constants.CREATE_NEW_SALESFORCE_PROJECT_TEXT;
    }

    @Override
    public @NotNull Icon getNodeIcon(boolean b) {
        return Constants.APEX_ICON;

    }

    public static SalesforceProjectModuleType getInstance() {
        return (SalesforceProjectModuleType) ModuleTypeManager.getInstance().findByID(Constants.MODULE_ID);
    }
}
