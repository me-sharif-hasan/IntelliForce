package com.iishanto.ide;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class AutoCompleteTypedHandler extends TypedHandlerDelegate {
    Map<Character,Character> map= Map.of(
            '(',')',
            '{','}',
            '[',']',
            '\'','\'',
            '"','"'
    );
    @Override
    public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        char nextChar= editor.getDocument().getCharsSequence().length()>editor.getCaretModel().getOffset()?editor.getDocument().getCharsSequence().charAt(editor.getCaretModel().getOffset()):'$';
        if(c=='('||c=='['||c=='{'){
            if (nextChar == c) {
                editor.getCaretModel().moveToOffset(editor.getCaretModel().getOffset() + 1);
                return Result.STOP;
            }
        }else if(c==')'||c==']'||c=='}'){
            if(nextChar==c){
                editor.getCaretModel().moveToOffset(editor.getCaretModel().getOffset()+1);
                return Result.STOP;
            }
            return Result.CONTINUE;
        }
        if (c == '('&&nextChar!=')') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), ")");
        } else if (c == '['&&nextChar!=']') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "]");
        } else if (c == '{'&&nextChar!='}') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "}");
        } else if (c == '\'') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "'");
        } else if (c == '"') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "\"");
        }
        return Result.CONTINUE;
    }
}
