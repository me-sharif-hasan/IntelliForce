package com.iishanto;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ApexLanguage extends Language {
    public static final ApexLanguage INSTANCE = new ApexLanguage();

    protected ApexLanguage() {
        super("Apex");
    }
}
