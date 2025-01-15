package com.iishanto.server.language.ide;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ApexLanguageAnnotator implements Annotator {
    public ApexLanguageAnnotator(){
        System.out.println("Loaded - iishanto");
    }
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        System.out.println("iishanto - annot "+psiElement.getText());
    }
}
