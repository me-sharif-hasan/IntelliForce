package com.iishanto.ide.annotator;

import com.iishanto.listeners.ApexDocumentDiagnosticReportCallbackListener;
import com.iishanto.server.hanlder.wrappers.DiagnosticsResult;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApexLanguageExternalAnnotator extends ExternalAnnotator<PsiFile, List<DiagnosticsResult>> {
    @Override
    public @Nullable PsiFile collectInformation(@NotNull PsiFile file) {
        return file;
    }

    @Override
    public @Nullable List<DiagnosticsResult> doAnnotate(PsiFile collectedInfo) {
        String filePath = collectedInfo.getContainingFile().getVirtualFile().getPath();
        if(filePath.endsWith(".apex")){
            String projectRoot = collectedInfo.getProject().getBasePath();
            filePath = projectRoot + '/' + ".sfdx" + '/' + "runner"+'/'+"TriggerRunner.cls";
        }
        ApexDocumentDiagnosticReportCallbackListener listener = new ApexDocumentDiagnosticReportCallbackListener();
        try {
            String content = collectedInfo.getText();
            NotificationHub.getInstance().didChange(
                    filePath,
                    getContent(collectedInfo) + "/*" + Math.random() + "*/",
                    listener
            );
            return listener.getDiagnosisResults();
        } catch (Exception e) {
            return null;
        }
    }

    private String getContent(PsiFile collectedInfo) {
        String filePath = collectedInfo.getContainingFile().getVirtualFile().getPath();

        if (filePath.endsWith(".apex")) {
            try {
                // Define the directory path
                File runnerDir = new File(".sfdx/runner");

                // Create runner folder if it doesn't exist
                if (!runnerDir.exists()) {
                    runnerDir.mkdirs();
                }

                // Create temp file with .cls extension in .sfdx/runner folder
                File tempFile = File.createTempFile("TriggerRunner", ".cls", runnerDir);

                // Prepare the code content
                String codeContent = """
                    public class TriggerRunner{
                    private void main(){
                    %s
                    }
                    }
                    """.formatted(collectedInfo.getText());

                // Write the content to the temporary file
                try (FileWriter writer = new FileWriter(tempFile)) {
                    writer.write(codeContent);
                }

                return codeContent;

            } catch (IOException e) {
                // Handle potential IO exceptions
                e.printStackTrace();
                return collectedInfo.getText(); // Fallback to original content
            }
        } else {
            return collectedInfo.getText();
        }
    }


    private void processErrorString(String s){
        //match the string invalid type: (.*) as typename with no case sensitivity
        Pattern pattern = Pattern.compile("invalid type: (.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            //get the type name
            String typeName = matcher.group(1);
            System.out.println("Type Name: "+typeName);
            ApexMissingTypeAggregator.getInstance().queue(typeName);
        }
    }

    @Override
    public void apply(@NotNull PsiFile file, List<DiagnosticsResult> annotationResult, @NotNull AnnotationHolder annotationHolder) {
        String[] lines = file.getText().split("\n");
        String content = file.getText();
        int lineReducingFactor=file.getContainingFile().getVirtualFile().getPath().endsWith(".apex")?-2:0;
        for (DiagnosticsResult diagnosticsResult : annotationResult) {
            diagnosticsResult.getRange().getStart().setLine(
                    diagnosticsResult.getRange().getStart().getLine()+lineReducingFactor
            );
            diagnosticsResult.getRange().getEnd().setLine(
                    diagnosticsResult.getRange().getEnd().getLine()+lineReducingFactor
            );
            System.out.println(diagnosticsResult.getMessage()+" "+diagnosticsResult.getRange().getStart().getLine());
            processErrorString(diagnosticsResult.getMessage());

            int begin = 0;
            int end = 0;
            for (int i = 0; i < lines.length; i++) {
                if ((diagnosticsResult.getRange().getStart().getLine()) > i) {
                    begin += lines[i].length() + 1;
                } else if (diagnosticsResult.getRange().getStart().getLine() == i) {
                    begin += diagnosticsResult.getRange().getStart().getCharacter();
                }

                if (diagnosticsResult.getRange().getEnd().getLine() > i) {
                    end += lines[i].length() + 1;
                } else if (diagnosticsResult.getRange().getEnd().getLine() == i) {
                    end += diagnosticsResult.getRange().getEnd().getCharacter();
                }
            }
            if (begin <= end && end <= content.length()) {
                TextRange textRange = new TextRange(begin, end);
                ApplicationManager.getApplication().runReadAction(() -> {
                    try {
                        annotationHolder.newAnnotation(HighlightSeverity.ERROR, diagnosticsResult.getMessage())
                                .range(textRange)
                                .create();
                    } catch (Exception ignored) {
                    }
                });
            }
        }
    }

}
