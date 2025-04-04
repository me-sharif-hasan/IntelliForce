package com.iishanto.ide.ui.tabs;

import com.google.common.base.Charsets;
import com.iishanto.ide.config.SalesforceProjectConfig;
import com.iishanto.ide.modulebuilder.ModuleConfig;
import com.iishanto.tooling.sync.ObjectSynchronizer;
import com.iishanto.language.sf.ApexIcons;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.icons.AllIcons;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * A ToolWindowFactory implementation that provides Salesforce authorization functionality within IntelliJ,
 * featuring a loading indicator and process termination capabilities.
 */
public class SalesforceToolUtility implements ToolWindowFactory {

    private static final String NOTIFICATION_GROUP_ID = "SalesforceToolUtility";
    private static final String AUTHORIZE_TEXT = "Add new salesforce org";
    private static final String CANCEL_TEXT = "Cancel Authorization";


    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    List <JButton> aliasButtons = new ArrayList<>();
    JButton currentAliasButton = null;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JButton authorizeButton = new JButton(AUTHORIZE_TEXT);
        authorizeButton.setIcon(AllIcons.Actions.AddList);
        JProgressBar loadingIndicator = new JProgressBar();
        loadingIndicator.setIndeterminate(true);
        loadingIndicator.setVisible(false); // Initially hidden
        loadingIndicator.setPreferredSize(new Dimension(20, 20));

        AtomicBoolean isAuthorizing = new AtomicBoolean(false);
        OSProcessHandler[] currentProcessHandler = {null}; // Array to allow modification in lambda

        authorizeButton.addActionListener(e -> {
            if (isAuthorizing.get()) {
                // Cancel the running process
                terminateProcess(project, currentProcessHandler[0], authorizeButton, loadingIndicator, isAuthorizing);
            } else {
                // Start authorization
                showAliasDialogAndAuthorize(project, authorizeButton, loadingIndicator, isAuthorizing, currentProcessHandler);
            }
        });

        panel.add(authorizeButton);
        panel.add(loadingIndicator);

        renderConnectedAliases(project);

        Content content = ContentFactory.getInstance().createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private void renderConnectedAliases(Project project) {
        List<String> connectedAliases = SalesforceProjectConfig.getInstance(project).getConnectedAliases();
        System.out.println("Connected aliases iis: " + connectedAliases);
        aliasButtons.forEach(panel::remove);
        aliasButtons.clear();
        if (!connectedAliases.isEmpty()) {
            System.out.println("Connected aliases: " + connectedAliases);
            connectedAliases.forEach(alias -> {
                JButton aliasButton = new JButton(alias);
                aliasButton.setBackground(JBColor.CYAN);
                String selectedAlias = SalesforceProjectConfig.getInstance(project).getSelectedAlias();
                if(alias.equals(selectedAlias)){
                    aliasButton.setIcon(AllIcons.Actions.Checked);
                    currentAliasButton=aliasButton;
                }else{
                    aliasButton.setIcon(ApexIcons.APEX_ICON);
                }
                aliasButton.addActionListener(e -> {
                            SalesforceProjectConfig.getInstance(project).setSelectedAlias(alias);
                            ObjectSynchronizer.getInstance().sync(project);
                            aliasButton.setIcon(AllIcons.Actions.Checked);
                            if(currentAliasButton!=null){
                                currentAliasButton.setIcon(ApexIcons.APEX_ICON);
                            }
                            currentAliasButton=aliasButton;
                        });
                panel.add(aliasButton);
                aliasButtons.add(aliasButton);
            });
        }
    }

    /**
     * Displays the alias input dialog and initiates Salesforce authorization if an alias is provided.
     *
     * @param project            The current IntelliJ project.
     * @param button             The authorize button to update.
     * @param loadingIndicator   The loading indicator to show/hide.
     * @param isAuthorizing      Flag indicating if authorization is in progress.
     * @param currentProcessHandler Array holding the current process handler.
     */
    private void showAliasDialogAndAuthorize(Project project, JButton button, JProgressBar loadingIndicator,
                                             AtomicBoolean isAuthorizing, OSProcessHandler[] currentProcessHandler) {
        AliasInputDialog dialog = new AliasInputDialog(project);
        if (dialog.showAndGet()) {
            String alias = dialog.getAlias();
            if (!alias.trim().isEmpty()) {
                isAuthorizing.set(true);
                button.setText(CANCEL_TEXT);
                loadingIndicator.setVisible(true);
                currentProcessHandler[0] = authorizeSalesforce(project, alias, button, loadingIndicator, isAuthorizing);
            }
        }
    }

    /**
     * Authorizes a Salesforce organization using the provided alias.
     *
     * @param project          The current IntelliJ project.
     * @param alias            The alias for the Salesforce organization.
     * @param button           The authorize button to update.
     * @param loadingIndicator The loading indicator to show/hide.
     * @param isAuthorizing    Flag indicating if authorization is in progress.
     * @return The OSProcessHandler managing the process.
     */
    private OSProcessHandler authorizeSalesforce(Project project, String alias, JButton button, JProgressBar loadingIndicator,
                                                 AtomicBoolean isAuthorizing) {
        try {
            cleanupPreviousProcesses(project);
            OSProcessHandler processHandler = startSalesforceLoginProcess(project, alias);
            long parentPid = processHandler.getProcess().pid();
            Set<Long> childPids = trackChildProcesses(project, parentPid);
            attachProcessListener(project, alias, processHandler, parentPid, childPids, button, loadingIndicator, isAuthorizing);
            return processHandler;
        } catch (Exception ex) {
            handleAuthorizationError(project, ex);
            resetUI(button, loadingIndicator, isAuthorizing);
            return null;
        }
    }

    /**
     * Terminates the current process and resets the UI.
     *
     * @param project          The current IntelliJ project.
     * @param processHandler   The process handler to terminate.
     * @param button           The authorize button to update.
     * @param loadingIndicator The loading indicator to hide.
     * @param isAuthorizing    Flag to reset.
     */
    private void terminateProcess(Project project, OSProcessHandler processHandler, JButton button, JProgressBar loadingIndicator,
                                  AtomicBoolean isAuthorizing) {
        if (processHandler != null && !processHandler.isProcessTerminated()) {
            processHandler.destroyProcess();
            System.out.println("Process terminated by user.");
            Notification notification = new Notification(NOTIFICATION_GROUP_ID, "Salesforce login canceled",
                    "Authorization process was canceled by the user.", NotificationType.WARNING);
            notification.setIcon(ApexIcons.APEX_ICON);
            ApplicationManager.getApplication().invokeLater(() -> Notifications.Bus.notify(notification, project));
        }
        cleanupPreviousProcesses(project); // Kill all registered PIDs
        resetUI(button, loadingIndicator, isAuthorizing);
    }

    /**
     * Resets the UI components to their initial state.
     *
     * @param button           The authorize button to reset.
     * @param loadingIndicator The loading indicator to hide.
     * @param isAuthorizing    Flag to reset.
     */
    private void resetUI(JButton button, JProgressBar loadingIndicator, AtomicBoolean isAuthorizing) {
        isAuthorizing.set(false);
        button.setText(AUTHORIZE_TEXT);
        loadingIndicator.setVisible(false);
    }

    /**
     * Cleans up any previously running processes stored in the project configuration.
     *
     * @param project The current IntelliJ project.
     */
    private void cleanupPreviousProcesses(Project project) {
        SalesforceProjectConfig config = SalesforceProjectConfig.getInstance(project);
        List<Long> previousPids = config.getRunningProcessIds();
        System.out.println("Previous running process IDs: " + previousPids);
        if (!previousPids.isEmpty()) {
            for (long pid : previousPids) {
                System.out.println("Killing previous process: " + pid);
                ProcessHandle.of(pid).ifPresent(ProcessHandle::destroyForcibly);
            }
            config.removeAllRunningProcessIds();
        }
    }

    /**
     * Starts the Salesforce login process using the CLI.
     *
     * @param project The current IntelliJ project.
     * @param alias   The alias for the Salesforce organization.
     * @return The OSProcessHandler managing the process.
     */
    private OSProcessHandler startSalesforceLoginProcess(Project project, String alias) throws ExecutionException {
        GeneralCommandLine commandLine = new GeneralCommandLine(ModuleConfig.getSfCliPath(project), "org", "login", "web", "--alias", alias)
                .withWorkDirectory(project.getBasePath())
                .withCharset(Charsets.UTF_8);
        System.out.println("Command: " + commandLine.getCommandLineString());

        OSProcessHandler processHandler = new OSProcessHandler(commandLine);
        System.out.println("Parent PID: " + processHandler.getProcess().pid());
        return processHandler;
    }

    /**
     * Tracks child processes spawned by the parent process.
     *
     * @param project   The current IntelliJ project.
     * @param parentPid The PID of the parent process.
     * @return A set of child process IDs.
     */
    private Set<Long> trackChildProcesses(Project project, long parentPid) {
        Set<Long> childPids = new HashSet<>();
        ProcessHandle.of(parentPid).ifPresent(parent -> {
            childPids.addAll(parent.children()
                    .map(ProcessHandle::pid)
                    .collect(Collectors.toSet()));
        });
        System.out.println("Initial Child PIDs: " + childPids);

        SalesforceProjectConfig config = SalesforceProjectConfig.getInstance(project);
        config.addRunningProcessId(parentPid);
        childPids.forEach(config::addRunningProcessId);
        return childPids;
    }

    /**
     * Attaches a process listener to handle process events and updates.
     *
     * @param project          The current IntelliJ project.
     * @param alias            The Salesforce organization alias.
     * @param processHandler   The process handler for the Salesforce CLI command.
     * @param parentPid        The PID of the parent process.
     * @param childPids        The set of child process IDs.
     * @param button           The authorize button to update.
     * @param loadingIndicator The loading indicator to show/hide.
     * @param isAuthorizing    Flag indicating if authorization is in progress.
     */
    private void attachProcessListener(Project project, String alias, OSProcessHandler processHandler, long parentPid,
                                       Set<Long> childPids, JButton button, JProgressBar loadingIndicator, AtomicBoolean isAuthorizing) {
        ConsoleView consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        consoleView.print("Running Salesforce authorization command...\n", ConsoleViewContentType.NORMAL_OUTPUT);

        processHandler.addProcessListener(new ProcessListener() {
            @Override
            public void startNotified(@NotNull ProcessEvent event) {
                consoleView.print("Command started.\n", ConsoleViewContentType.SYSTEM_OUTPUT);
                updateChildProcesses(project, parentPid, childPids);
            }

            @Override
            public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                String text = event.getText();
                System.out.println("Output: " + text);
                consoleView.print(text, ConsoleViewContentType.NORMAL_OUTPUT);
            }

            @Override
            public void processTerminated(@NotNull ProcessEvent event) {
                int exitCode = event.getExitCode();
                System.out.println("Process terminated with exit code: " + exitCode);
                consoleView.print("Command terminated.\n", ConsoleViewContentType.SYSTEM_OUTPUT);

                registerAlias(project, alias, exitCode);
                notifyAuthorizationResult(project, alias, exitCode);
                cleanupProcessIds(project, parentPid, childPids);
                resetUI(button, loadingIndicator, isAuthorizing);
            }
        });

        processHandler.startNotify();
        consoleView.attachToProcess(processHandler);
    }

    private void registerAlias(Project project, String alias, int exitCode) {
        if (exitCode == 0) {
            SalesforceProjectConfig.getInstance(project).addConnectedAlias(alias);
        }
    }
    /**
     * Updates the set of child PIDs by detecting new descendants.
     *
     * @param project   The current IntelliJ project.
     * @param parentPid The PID of the parent process.
     * @param childPids The current set of child PIDs to update.
     */
    private void updateChildProcesses(Project project, long parentPid, Set<Long> childPids) {
        ProcessHandle.of(parentPid).ifPresent(parent -> {
            Set<Long> updatedChildPids = parent.descendants()
                    .map(ProcessHandle::pid)
                    .collect(Collectors.toSet());
            updatedChildPids.removeAll(childPids);
            if (!updatedChildPids.isEmpty()) {
                System.out.println("New Child PIDs detected: " + updatedChildPids);
                SalesforceProjectConfig.getInstance(project).addRunningProcessIds(updatedChildPids);
                childPids.addAll(updatedChildPids);
            }
        });
    }

    /**
     * Notifies the user of the authorization result based on the exit code.
     *
     * @param project  The current IntelliJ project.
     * @param alias    The Salesforce organization alias.
     * @param exitCode The exit code of the process.
     */
    private void notifyAuthorizationResult(Project project, String alias, int exitCode) {
        Notification notification = (exitCode == 0)
                ? new Notification(NOTIFICATION_GROUP_ID, "Salesforce login", "Successfully authorized Salesforce org with alias '" + alias + "'", NotificationType.INFORMATION)
                : new Notification(NOTIFICATION_GROUP_ID, "Salesforce login failure", "Failed to authorize Salesforce org. Exit code: " + exitCode, NotificationType.ERROR);
        renderConnectedAliases(project);
        notification.setIcon(ApexIcons.APEX_ICON);
        notification.setImportant(true);
        ApplicationManager.getApplication().invokeLater(() -> Notifications.Bus.notify(notification, project));
    }

    /**
     * Cleans up all tracked process IDs from the configuration.
     *
     * @param project   The current IntelliJ project.
     * @param parentPid The PID of the parent process.
     * @param childPids The set of child process IDs.
     */
    private void cleanupProcessIds(Project project, long parentPid, Set<Long> childPids) {
        SalesforceProjectConfig config = SalesforceProjectConfig.getInstance(project);
        config.removeRunningProcessId(parentPid);
        childPids.forEach(config::removeRunningProcessId);
    }

    /**
     * Handles errors during authorization by logging and notifying the user.
     *
     * @param project The current IntelliJ project.
     * @param ex      The exception that occurred.
     */
    private void handleAuthorizationError(Project project, Exception ex) {
        String errorMessage = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.toString();
        System.err.println("Error during authorization: " + errorMessage);

        Notification notification = new Notification(NOTIFICATION_GROUP_ID, "Salesforce login error", "Error: " + errorMessage, NotificationType.ERROR);
        notification.setIcon(ApexIcons.APEX_ICON);
        ApplicationManager.getApplication().invokeLater(() -> Notifications.Bus.notify(notification, project));
    }

    /**
     * Dialog for entering the Salesforce alias, styled to match IntelliJ's UI.
     */
    private static class AliasInputDialog extends DialogWrapper {
        private JBTextField aliasField;

        AliasInputDialog(Project project) {
            super(project);
            setTitle("Authorize Salesforce");
            init();
        }

        @Override
        protected @Nullable JComponent createCenterPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = JBUI.insets(4, 8);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(new JBLabel("Salesforce login alias:"), gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            aliasField = new JBTextField(20);
            aliasField.setText("jetForce");
            panel.add(aliasField, gbc);

            return panel;
        }

        @Override
        protected void init() {
            super.init();
            setOKButtonText("Authorize");
            setCancelButtonText("Cancel");
        }

        String getAlias() {
            return aliasField.getText().trim();
        }
    }
}