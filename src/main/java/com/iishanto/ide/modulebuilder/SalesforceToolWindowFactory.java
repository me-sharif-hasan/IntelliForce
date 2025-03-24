package com.iishanto.ide.modulebuilder;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class SalesforceToolWindowFactory implements ToolWindowFactory {
    private static ToolWindow salesforceToolWindow;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        salesforceToolWindow = toolWindow;
        toolWindow.show();
    }

    public static void showConsole(Project project, ConsoleView consoleView) {
        if (salesforceToolWindow != null) {
            ContentFactory contentFactory = ContentFactory.getInstance();
            Content content = contentFactory.createContent(consoleView.getComponent(), "Salesforce Project Generation", false);
            salesforceToolWindow.getContentManager().removeAllContents(true);
            salesforceToolWindow.getContentManager().addContent(content);
            salesforceToolWindow.show();
        }
    }
}