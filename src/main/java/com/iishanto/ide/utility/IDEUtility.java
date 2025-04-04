package com.iishanto.ide.utility;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

public class IDEUtility {
    private static ConsoleView consoleView;
    public static void initConsoleView(Project project){
        if(consoleView==null){
            consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        }
    }
    public static void attachConsoleViewToProcess(ProcessHandler processHandler){
        consoleView.attachToProcess(processHandler);
    }
    public static void forceRefresh(VirtualFile projectDir, Runnable onRefresh) {
        ApplicationManager.getApplication().invokeLater(() -> {
            projectDir.refresh(true, true, onRefresh);
        });
    }

    public static void forceRefreshAndExpand(VirtualFile projectDir,Project project, Runnable onRefresh) {
        ApplicationManager.getApplication().invokeLater(() -> {
            projectDir.refresh(true, true, () -> {
                ProjectView projectView = ProjectView.getInstance(project);
                projectView.refresh();
                projectView.select(project, projectDir, true);
                if (onRefresh != null) {
                    onRefresh.run();
                }
            });
        });
    }

    public static SalesforceOutputManager getSalesforceOutputManager(Project project){
        return new SalesforceOutputManager(project);
    }

    public static class SalesforceOutputManager{
        private final Project project;
        private final ConsoleView consoleView;
        private ToolWindow toolWindow;
        private boolean isAttached=false;

        public SalesforceOutputManager(Project project){
            this.project=project;
            consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        }
        public void attachToProcess(ProcessHandler processHandler,String title){
            if(!isAttached){
                consoleView.attachToProcess(processHandler);
                isAttached=true;
            }
            ApplicationManager.getApplication().invokeLater(()->show(title));
        }
        private void show(String title){
            if(toolWindow==null){
                toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Salesforce Output");
                if (toolWindow != null) {
                    ContentFactory contentFactory = ContentFactory.getInstance();
                    Content content = contentFactory.createContent(consoleView.getComponent(), title, false);
                    content.setCloseable(true);
                    content.setIcon(IconLoader.getIcon("/icons/salesforce.svg",IDEUtility.class));
                    toolWindow.getContentManager().addContent(content);
                    toolWindow.show();
                }
            }
        }

        public void log(String message){
            consoleView.print(message, ConsoleViewContentType.NORMAL_OUTPUT);
        }

        public void log(String message,ConsoleViewContentType type){
            consoleView.print(message, type);
        }

    }
}
