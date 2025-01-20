package com.iishanto.ide;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iishanto.common.Logger;
import com.iishanto.common.MessageType;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

public class ApexGotoDeclarationHandler implements GotoDeclarationHandler {
    private final WeakHashMap<PsiElement, PsiElement[]> cache = new WeakHashMap<>();

    @Nullable
    @Override
    public synchronized PsiElement[] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {
        if (sourceElement == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        if(cache.containsKey(sourceElement)){
            return cache.get(sourceElement);
        }

        try {
            int startOffset = sourceElement.getTextRange().getStartOffset();
            String text = sourceElement.getText();
            Logger.log("User requested goto declaration for: " + text + " at offset: " + offset);

            int lineNumber = editor.getDocument().getLineNumber(offset);
            int columnNumber = offset - editor.getDocument().getLineStartOffset(lineNumber);
            String filePath = sourceElement.getContainingFile().getVirtualFile().getPath();

            GotoDefinitionListener gotoDefinitionListener = new GotoDefinitionListener();
            NotificationHub.getInstance().definition(filePath, lineNumber, columnNumber, gotoDefinitionListener);
            JsonObject response = gotoDefinitionListener.get();

            if (response != null) {
                JsonArray result = response.get("result").getAsJsonArray();
                PsiElement[] psiElements = new PsiElement[result.size()];
                int idx = 0;

                for (JsonElement jsonElement : result) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    String uri = jsonObject.get("uri").getAsString();
                    int line = jsonObject.get("range").getAsJsonObject().get("start").getAsJsonObject().get("line").getAsInt();
                    int character = jsonObject.get("range").getAsJsonObject().get("start").getAsJsonObject().get("character").getAsInt();

                    VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(uri.replace("file:///", ""));
                    if (virtualFile == null) continue;

                    PsiFile psiFile = PsiManager.getInstance(sourceElement.getProject()).findFile(virtualFile);
                    if (psiFile == null) continue;

                    int targetOffset = calculateOffset(psiFile, line, character);
                    if (targetOffset == -1) continue;

                    PsiElement targetElement = psiFile.findElementAt(targetOffset);
                    if (targetElement != null) {
                        psiElements[idx++] = targetElement;
                    }
                }

                if (idx > 0) {
                    cache.put(sourceElement, psiElements);
                    return psiElements;
                }
            }

            return PsiElement.EMPTY_ARRAY;

        } catch (Throwable e) {
            return PsiElement.EMPTY_ARRAY;
        }
    }

    private int calculateOffset(PsiFile psiFile, int line, int character) {
        var document = psiFile.getViewProvider().getDocument();
        if (document == null || line >= document.getLineCount()) {
            return -1;
        }
        int lineStartOffset = document.getLineStartOffset(line);
        return lineStartOffset + character;
    }
}

class GotoDefinitionListener extends LspResponseListener {

    @Override
    public String getTargetMethod() {
        return "definition";
    }

    @Override
    public boolean isMatching(JsonObject jsonObject) {
        return jsonObject.get("id").getAsString().equals(MessageType.APEX_DEFINITION_MESSAGE.toString());
    }

    @Override
    public boolean isCompletableFuture() {
        return true;
    }
}
