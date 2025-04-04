package com.iishanto.language.sf.apex.filetypes;

import com.iishanto.language.sf.ApexIcons;
import com.iishanto.language.sf.ApexLanguage;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ApexScriptFileType extends LanguageFileType {
    public static final ApexScriptFileType INSTANCE = new ApexScriptFileType();
    public ApexScriptFileType() {
        super(ApexLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Apex Script";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Apex runnable script file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return ".apex";
    }

    @Override
    public Icon getIcon() {
        return PlatformIcons.ANONYMOUS_CLASS_ICON;
    }
}
