package com.iishanto.ide.modulebuilder;

import com.google.common.base.Charsets;
import com.iishanto.common.Constants;
import com.iishanto.ide.modulebuilder.step.SalesforceProjectWizardStep;
import com.iishanto.ide.utility.IDEUtility;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SalesforceProjectBuilder extends ModuleBuilder {

    @Override
    public Icon getNodeIcon() {
        return Constants.APEX_ICON;
    }

    @Override
    public @Nullable @NonNls String getBuilderId() {
        return "SalesforceProjectBuilder";
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
            ModuleConfig.setSfCliPath(project,ModuleConfig.sfCliPath);
            GeneralCommandLine commandLine = new GeneralCommandLine(ModuleConfig.getSfCliPath(project), "project", "generate", "--name", ".")
                    .withWorkDirectory(project.getBasePath())
                    .withCharset(Charsets.UTF_8);

            OSProcessHandler processHandler = new OSProcessHandler(commandLine);
            IDEUtility.SalesforceOutputManager outputManager = IDEUtility.getSalesforceOutputManager(project);
//            ConsoleView consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
            outputManager.log("Running Salesforce project generation command...\n", ConsoleViewContentType.NORMAL_OUTPUT);

            processHandler.addProcessListener(new ProcessListener() {
                @Override
                public void startNotified(@NotNull ProcessEvent event) {
                    outputManager.log("Command started.\n", ConsoleViewContentType.SYSTEM_OUTPUT);
                }

                @Override
                public void processTerminated(@NotNull ProcessEvent event) {

                    LocalFileSystem localFileSystem = LocalFileSystem.getInstance();
                    VirtualFile projectDir = localFileSystem.refreshAndFindFileByPath(Objects.requireNonNull(project.getBasePath()));
                    if (projectDir == null) {
                        throw new RuntimeException("Generated project directory not found: " + project.getBasePath());
                    }

                    ApplicationManager.getApplication().invokeLater(() -> {
                        IDEUtility.forceRefreshAndExpand(projectDir,project,() -> {
                            System.out.println("Project directory refreshed: " + projectDir.getPath());

                            ApplicationManager.getApplication().runWriteAction((Computable<Module>) () -> {
                                Module newModule;
                                if (modifiableModuleModel != null) {
                                    newModule = modifiableModuleModel.newModule(ModuleConfig.moduleFilePath, getModuleType().getId());
                                    ModifiableRootModel rootModel = ModuleRootManager.getInstance(newModule).getModifiableModel();
                                    rootModel.addContentEntry(projectDir);
                                    VirtualFile sourceDir = projectDir.findFileByRelativePath("force-app/main/default");
                                    if (sourceDir != null) {
                                        rootModel.addContentEntry(sourceDir).addSourceFolder(sourceDir, false);
                                    }
                                    rootModel.commit();
                                } else {
                                    newModule = ModuleManager.getInstance(project).newModule(ModuleConfig.moduleFilePath, getModuleType().getId());
                                    ModifiableRootModel rootModel = ModuleRootManager.getInstance(newModule).getModifiableModel();
                                    rootModel.addContentEntry(projectDir);
                                    VirtualFile sourceDir = projectDir.findFileByRelativePath("force-app/main/default");
                                    if (sourceDir != null) {
                                        rootModel.addContentEntry(sourceDir).addSourceFolder(sourceDir, false);
                                    }
                                    rootModel.commit();
                                }
                                return newModule;
                            });
                        });
                    });

                    outputManager.log("Command finished with exit code " + event.getExitCode() + "\n",
                            event.getExitCode() == 0 ? ConsoleViewContentType.NORMAL_OUTPUT : ConsoleViewContentType.ERROR_OUTPUT);
                }

                @Override
                public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                    ConsoleViewContentType contentType = outputType == ProcessOutputTypes.STDOUT
                            ? ConsoleViewContentType.NORMAL_OUTPUT
                            : ConsoleViewContentType.ERROR_OUTPUT;
                    outputManager.log(event.getText(), contentType);
                    outputManager.attachToProcess(processHandler, "Generating Salesforce Project");
                }
            });

            // Start the process and wait for completion
            processHandler.startNotify();



            // Return null for now; module creation happens asynchronously
            return null;
        } catch (Exception e) {
            Messages.showErrorDialog(project, "Failed to generate Salesforce project: " + e.getMessage(), "Error");
            return null; // Indicate failure
        }
    }
}