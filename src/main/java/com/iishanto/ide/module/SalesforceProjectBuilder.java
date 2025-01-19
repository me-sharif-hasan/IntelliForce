package com.iishanto.ide.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class SalesforceProjectBuilder extends ModuleBuilder {

    @Override
    public Icon getNodeIcon() {
        return IconLoader.getIcon("/icons/salesforce.svg", SalesforceProjectModuleType.class);
    }

    @Override
    public @Nullable @NonNls String getBuilderId() {
        return "";
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[0];
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public void setModuleFilePath(@NonNls String s) {

    }

    @Override
    public void setContentEntryPath(String s) {

    }

    @Override
    public ModuleType<?> getModuleType() {
        return SalesforceProjectModuleType.getInstance();
    }

    @Override
    public @Nullable List<Module> commit(@NotNull Project project, @Nullable ModifiableModuleModel modifiableModuleModel, ModulesProvider modulesProvider) {
        return List.of();
    }
}
