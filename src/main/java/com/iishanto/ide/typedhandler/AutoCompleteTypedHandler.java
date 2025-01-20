package com.iishanto.ide.typedhandler;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class AutoCompleteTypedHandler extends TypedHandlerDelegate {
    private final Map<Character, Character> map = Map.of(
            '(', ')',
            '{', '}',
            '[', ']',
            '\'', '\'',
            '"', '"'
    );


    @Override
    public @NotNull Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        CharSequence documentChars = editor.getDocument().getCharsSequence();
        char nextChar = offset < documentChars.length() ? documentChars.charAt(offset) : '$';
        System.out.println("got char ii: "+(int)c);
        if (map.containsKey(c)) {
            if (nextChar == map.get(c)) {
                editor.getCaretModel().moveToOffset(offset + 1);
            } else {
                editor.getDocument().insertString(offset, map.get(c).toString());
            }
            return Result.STOP;
        } else if (map.containsValue(c)) {
            if (nextChar == c) {
                editor.getCaretModel().moveToOffset(offset + 1);
                editor.getDocument().deleteString(editor.getCaretModel().getOffset()-1,editor.getCaretModel().getOffset());
                return Result.STOP;
            }
        }
        return Result.CONTINUE;
    }
}
