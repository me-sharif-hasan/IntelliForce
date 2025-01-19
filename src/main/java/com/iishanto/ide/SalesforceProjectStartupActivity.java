package com.iishanto.ide;

import com.iishanto.common.Configs;
import com.iishanto.common.Logger;
import com.iishanto.listeners.FileChangeListener;
import com.iishanto.server.ApexLanguageServerDefinition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SalesforceProjectStartupActivity implements ProjectActivity {
    public static Project project;
    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        SalesforceProjectStartupActivity.project=project;
        String projectPath = project.getBasePath(); // Get the listeners root path
        try{
            if (projectPath != null) {
                FileChangeListener.registerListener();
                Configs.getInstance().setProjectRoot(projectPath);
                ApexLanguageServerDefinition.getInstance();
                Logger.log("Project Root on Startup: " + projectPath);
            } else {
                Logger.log("Failed to retrieve the listeners root on startup.");
            }
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }
}
