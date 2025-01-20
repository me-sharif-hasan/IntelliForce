package com.iishanto.ide.modulebuilder;

import com.iishanto.common.Constants;
import com.iishanto.ide.modulebuilder.step.SalesforceProjectWizardStep;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
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
        return Constants.APEX_ICON;
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
    public @Nullable ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        return new SalesforceProjectWizardStep();
    }

    @Override
    public void setName(String s) {
        ModuleConfig.projectName=s;
    }

    @Override
    public void setModuleFilePath(@NonNls String s) {
        ModuleConfig.moduleFilePath=s;
    }

    @Override
    public void setContentEntryPath(String s) {
        ModuleConfig.contentEntryPath=s;
    }

    @Override
    public ModuleType<?> getModuleType() {
        return SalesforceProjectModuleType.getInstance();
    }

    @Override
    public @Nullable List<Module> commit(@NotNull Project project, @Nullable ModifiableModuleModel modifiableModuleModel, ModulesProvider modulesProvider) {
        System.out.println("Creating project with infos: ");
        ModuleConfig.printDetails();
        return List.of();
    }
}
