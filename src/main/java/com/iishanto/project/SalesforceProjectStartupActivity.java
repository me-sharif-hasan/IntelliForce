package com.iishanto.project;

import com.iishanto.common.Configs;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.startup.StartupActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SalesforceProjectStartupActivity implements ProjectActivity {
    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        String projectPath = project.getBasePath(); // Get the project root path
        try{
            if (projectPath != null) {
                Configs.getInstance().setProjectRoot(projectPath);
                System.out.println("Project Root on Startup: " + projectPath);
            } else {
                System.out.println("Failed to retrieve the project root on startup.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
