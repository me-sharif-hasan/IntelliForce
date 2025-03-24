package com.iishanto.ide.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A persistent state component that manages Salesforce-related configuration for a project,
 * including CLI path, running process IDs, and connected aliases.
 */
@State(
        name = "SalesforceProjectConfig",
        storages = @Storage("salesforceProjectConfig.xml")
)
public class SalesforceProjectConfig implements PersistentStateComponent<SalesforceProjectConfig.State> {

    /**
     * Represents the persistent state of the Salesforce project configuration.
     */
    public static class State {
        public String sfCliPath = null;
        public List<Long> runningProcessIds = new ArrayList<>();
        public List<String> connectedAliases = new ArrayList<>();
        public String selectedAlias = null;
    }

    private State myState = new State();

    /**
     * Retrieves the current state of the configuration.
     *
     * @return The current state, or null if not initialized.
     */
    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    /**
     * Loads a new state into the configuration.
     *
     * @param state The state to load, must not be null.
     */
    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    /**
     * Gets the singleton instance of this configuration for the given project.
     *
     * @param project The IntelliJ project.
     * @return The configuration instance.
     */
    public static SalesforceProjectConfig getInstance(@NotNull Project project) {
        return project.getService(SalesforceProjectConfig.class);
    }

    /**
     * Gets the path to the Salesforce CLI.
     *
     * @return The CLI path, or null if not set.
     */
    @Nullable
    public String getSfCliPath() {
        return myState.sfCliPath;
    }

    /**
     * Sets the path to the Salesforce CLI.
     *
     * @param sfCliPath The path to set, may be null.
     */
    public void setSfCliPath(@Nullable String sfCliPath) {
        myState.sfCliPath = sfCliPath;
    }

    /**
     * Gets the list of running process IDs.
     *
     * @return An unmodifiable list of process IDs, never null.
     */
    @NotNull
    public List<Long> getRunningProcessIds() {
        return Collections.unmodifiableList(myState.runningProcessIds);
    }

    /**
     * Adds a single process ID to the list of running processes.
     *
     * @param processId The process ID to add.
     */
    public void addRunningProcessId(long processId) {
        myState.runningProcessIds.add(processId);
    }

    /**
     * Adds multiple process IDs to the list of running processes.
     *
     * @param processIds The collection of process IDs to add.
     */
    public void addRunningProcessIds(@NotNull Collection<Long> processIds) {
        myState.runningProcessIds.addAll(processIds);
    }

    /**
     * Removes a single process ID from the list of running processes.
     *
     * @param processId The process ID to remove.
     */
    public void removeRunningProcessId(long processId) {
        myState.runningProcessIds.remove(processId);
    }

    /**
     * Clears all running process IDs from the configuration.
     */
    public void removeAllRunningProcessIds() {
        myState.runningProcessIds.clear();
    }

    /**
     * Gets the list of connected Salesforce aliases.
     *
     * @return An unmodifiable list of aliases, never null.
     */
    @NotNull
    public List<String> getConnectedAliases() {
        return Collections.unmodifiableList(myState.connectedAliases);
    }

    /**
     * Adds a single connected alias to the list.
     *
     * @param alias The alias to add.
     */
    public void addConnectedAlias(@NotNull String alias) {
        myState.connectedAliases.add(alias);
    }

    public void setSelectedAlias(@NotNull String alias) {
       if(myState.connectedAliases.contains(alias)) {
           myState.selectedAlias = alias;
       }
    }

    public String getSelectedAlias() {
        if (myState.connectedAliases.contains(myState.selectedAlias)) {
            return myState.selectedAlias;
        }
        return null;
    }
}