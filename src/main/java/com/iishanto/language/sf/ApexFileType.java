package com.iishanto.language.sf;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ApexFileType extends LanguageFileType {
    protected ApexFileType() {
        super( ApexLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Apex Class File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Salesforce Apex Class File";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return ".cls";
    }

    @Override
    public Icon getIcon() {
        return null;
    }
}
