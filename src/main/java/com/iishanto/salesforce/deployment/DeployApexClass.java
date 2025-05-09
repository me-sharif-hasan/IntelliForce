package com.iishanto.salesforce.deployment;

import com.iishanto.ide.utility.SalesforceUtility;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DeployApexClass {
    private DeployApexClass() {}

    private static DeployApexClass deployApexClass;
    public static DeployApexClass getInstance() {
        if (deployApexClass == null) {
            deployApexClass = new DeployApexClass();
        }
        return deployApexClass;
    }
    public void deploy(@NonNls @NotNull String path, Project project) {
        // Implement the deployment logic here
        System.out.println("Deploying Apex Class... "+path);
        SalesforceUtility.deployFile(path, SalesforceUtility.DeploymentType.ApexClass, project);
    }
}
