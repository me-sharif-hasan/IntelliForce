//package com.iishanto.apexplugin;
//
//import com.iishanto.language.sf.ApexFileType;
//import com.iishanto.language.sf.ApexLanguage;
//import com.intellij.icons.AllIcons;
//import com.intellij.openapi.actionSystem.*;
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.ui.popup.JBPopup;
//import com.intellij.openapi.ui.popup.JBPopupFactory;
//import com.intellij.openapi.vfs.VirtualFile;
//import com.intellij.psi.PsiDirectory;
//import com.intellij.psi.PsiFile;
//import com.intellij.psi.PsiFileFactory;
//import com.intellij.ui.components.JBLabel;
//import com.intellij.ui.components.JBTextField;
//import org.jetbrains.annotations.NotNull;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//
//public class ApexClassCreationDialog extends AnAction {
//    @Override
//    public void actionPerformed(@NotNull AnActionEvent e) {
//        Project project = e.getData(CommonDataKeys.PROJECT);
//        PsiDirectory directory = e.getData(CommonDataKeys.PSI_ELEMENT) instanceof PsiDirectory
//                ? (PsiDirectory) e.getData(CommonDataKeys.PSI_ELEMENT)
//                : null;
//        if (project == null || directory == null || e.getPlace() == null) return;
//
//        // Create the popup content
//        ApexClassPopupPanel popupPanel = new ApexClassPopupPanel(project, directory);
//
//        // Create and show the popup
//        JBPopup popup = JBPopupFactory.getInstance()
//                .createComponentPopupBuilder(popupPanel, popupPanel.getNameField())
//                .setTitle("New Apex Class")
//                .setMovable(true)
//                .setRequestFocus(true)
//                .setCancelOnClickOutside(true)
//                .createPopup();
//
//        // Show the popup at the mouse location
//        popup.showInBestPositionFor(e.getDataContext());
//    }
//
//    @Override
//    public void update(@NotNull AnActionEvent e) {
//        boolean enabled = e.getData(CommonDataKeys.PROJECT) != null &&
//                e.getData(CommonDataKeys.PSI_ELEMENT) instanceof PsiDirectory;
//        e.getPresentation().setEnabledAndVisible(enabled);
//    }
//}
//
