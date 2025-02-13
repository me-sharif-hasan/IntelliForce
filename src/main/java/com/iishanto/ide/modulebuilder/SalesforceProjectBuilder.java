package com.iishanto.ide.modulebuilder;

import com.iishanto.common.Constants;
import com.iishanto.ide.modulebuilder.step.SalesforceProjectWizardStep;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
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
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
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
        ModuleConfig.projectName = s;
    }

    @Override
    public void setModuleFilePath(@NonNls String s) {
        ModuleConfig.moduleFilePath = s;
    }

    @Override
    public void setContentEntryPath(String s) {
        ModuleConfig.contentEntryPath = s;
    }

    @Override
    public ModuleType<?> getModuleType() {
        return SalesforceProjectModuleType.getInstance();
    }

    @Override
    public @Nullable List<Module> commit(@NotNull Project project, @Nullable ModifiableModuleModel modifiableModuleModel, ModulesProvider modulesProvider) {
        System.out.println("Creating project with infos: ");
        ModuleConfig.printDetails();
        try {
            // Build the command to execute
            ProcessBuilder processBuilder = new ProcessBuilder(ModuleConfig.sfCliPath, "project", "generate", "--name", ".");
            processBuilder.directory(new File(project.getBasePath()));

            // Start the process
            Process process = processBuilder.start();

            // Set up IntelliJ ConsoleView
            ConsoleView consoleView = new ConsoleViewImpl(project, true);
            consoleView.print("Running project generation command...\n", ConsoleViewContentType.NORMAL_OUTPUT);

            // Create a ProcessHandler for the process
            ProcessHandler processHandler = new OSProcessHandler(process, processBuilder.command().toString());

            // Attach the process output to the ConsoleView
            processHandler.addProcessListener(new com.intellij.execution.process.ProcessAdapter() {
                @Override
                public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                    consoleView.print(event.getText(), outputType == ProcessOutputTypes.STDOUT ? ConsoleViewContentType.NORMAL_OUTPUT : ConsoleViewContentType.ERROR_OUTPUT);
                }
            });

            processHandler.startNotify();

            // Add the ConsoleView to a tool window or run window
            VirtualFileManager.getInstance().syncRefresh();
            return List.of();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
