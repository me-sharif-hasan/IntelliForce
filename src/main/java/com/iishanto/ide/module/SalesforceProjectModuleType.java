package com.iishanto.ide.module;

import com.iishanto.language.sf.ApexIcons;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.util.Objects;

public class SalesforceProjectModuleType extends ModuleType<SalesforceProjectBuilder> {
    public SalesforceProjectModuleType() {
        super("SFDX_MODULE");
    }

    @Override
    public @NotNull SalesforceProjectBuilder createModuleBuilder() {
        return new SalesforceProjectBuilder();
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getName() {
        return "Salesforce Project";
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getDescription() {
        return "Create a new Salesforce project";
    }

    @Override
    public @NotNull Icon getNodeIcon(boolean b) {
        return IconLoader.getIcon("/icons/salesforce.svg", SalesforceProjectModuleType.class);

    }

    public static SalesforceProjectModuleType getInstance() {
        return (SalesforceProjectModuleType) ModuleTypeManager.getInstance().findByID("SFDX_MODULE");
    }
}
