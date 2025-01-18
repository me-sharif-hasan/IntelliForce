package com.iishanto.ide;

import com.iishanto.project.AnnotationProcessor;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApexLanguageAnnotator implements Annotator {
    private final Map<String,AnnotationHolder> fileAnnotators=new HashMap<>();
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (psiElement.getClass().getName().contains("ApexClassFile")) {
            try {
                handleClassAnnotation(psiElement, annotationHolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    synchronized public void handleClassAnnotation(@NotNull PsiElement psiClass, @NotNull AnnotationHolder annotationHolder) throws IOException {
        String filePath = psiClass.getContainingFile().getVirtualFile().getPath();
        fileAnnotators.put(filePath,annotationHolder);
        String content= psiClass.getText();
        NotificationHub.getInstance().didOpen(
                filePath,
                content,
                new AnnotationProcessor(filePath,content)
                        .setAnnotationHolder(annotationHolder)
        );
    }
}
