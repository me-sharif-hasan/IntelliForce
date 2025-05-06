package com.iishanto.ide.codeformatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class ApexCodeStyleSettings extends CustomCodeStyleSettings {
    protected ApexCodeStyleSettings(CodeStyleSettings settings) {
        super("ApexCodeStyleSettings", settings);
    }
}