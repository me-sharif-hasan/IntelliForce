package com.iishanto.ide.codeformatter;
import com.iishanto.language.sf.ApexLanguage;
import com.intellij.ide.JavaLanguageCodeStyleSettingsProvider;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApexCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @Override
    public @Nullable String getCodeSample(@NotNull SettingsType settingsType) {
        return "public class SampleClass {\\r\\n    public static void sayHello() {\\r\\n        System.debug(\\'Hello, Apex!\\');\\r\\n    }\\r\\n}";
    }


    @Override
    public @Nullable CustomCodeStyleSettings createCustomSettings(@NotNull CodeStyleSettings settings) {
        return new ApexCodeStyleSettings(settings);
    }

    @Override
    public @NotNull Language getLanguage() {
        return ApexLanguage.INSTANCE;
    }
}
