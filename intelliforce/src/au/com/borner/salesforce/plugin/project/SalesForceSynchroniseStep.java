package au.com.borner.salesforce.plugin.project;

import au.com.borner.salesforce.client.rest.ToolingRestClient;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author mark
 */
public class SalesForceSynchroniseStep {

    private final Logger logger = Logger.getInstance(getClass());
    private final SalesForceModuleBuilder moduleBuilder;
    private final SalesForceWizardStateBean stateBean;

    private JButton closeButton;
    private JTextArea statusArea;
    private JPanel synchronizingPanel;

    private volatile boolean synchronizing = false;
    private volatile boolean synchronizeFinished = false;

    public SalesForceSynchroniseStep(SalesForceWizardStateBean stateBean, SalesForceModuleBuilder moduleBuilder) {
        this.stateBean = stateBean;
        this.moduleBuilder = moduleBuilder;

    }

    public void _init() {
        JFrame frame = new JFrame("Synchronizing Metadata");
        frame.setContentPane(this.synchronizingPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        if (synchronizeFinished) {
            // this gets called when we enter AND exits the step (?!?!), so ignore the second call on exit
            return;
        }
        synchronizing = true;
        createFolders();
        SynchronizeSwingWorker swingWorker = new SynchronizeSwingWorker(statusArea, stateBean, new SynchronizeSwingWorker.DoneCallBack() {
            @Override
            public void done() {
                synchronizing = false;
                synchronizeFinished = true;
                closeButton.setEnabled(true);
            }
        });
        swingWorker.execute();
    }

    private void createFolders() {
        String contentEntryPath = moduleBuilder.getContentEntryPath();
        for (SalesForceWizardStateBean.FOLDER_NAMES folderName : SalesForceWizardStateBean.FOLDER_NAMES.values()) {
            String folderPath = contentEntryPath + File.separator + folderName.name().toLowerCase();
            boolean result = new File(folderPath).mkdirs();
            if (!result) {
                logger.warn("Error creating folder " + folderName.name().toLowerCase());
            }
            stateBean.addCreatedFolder(folderName, folderPath);
        }

    }

    public void _commit(boolean finishChosen) throws CommitStepException {
        if (synchronizing) {
            throw new CommitStepException("Please wait for synchronization to complete.");
        }
    }

}
