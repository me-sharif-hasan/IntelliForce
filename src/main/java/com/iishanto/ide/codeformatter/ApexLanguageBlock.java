package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.formatting.alignment.AlignmentStrategy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.JavaCodeStyleSettings;
import com.intellij.psi.formatter.java.JavaBlock;
import com.intellij.psi.formatter.java.SimpleJavaBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ApexLanguageBlock implements Block {
    private final ASTNode node;
    private final Indent indent;
    private final CodeStyleSettings settings;
    private final JavaBlock javaBlock;
    private final Block block;

    public ApexLanguageBlock(ASTNode node, @Nullable Wrap wrap, Indent indent, CodeStyleSettings settings) {
        this.node = node;
        this.indent = indent;
        this.settings = settings;
        CodeStyleSettings globalSettings = new CodeStyleSettings();
        CommonCodeStyleSettings commonSettings = globalSettings.getCommonSettings("Java");
        JavaCodeStyleSettings javaSettings = globalSettings.getCustomSettings(JavaCodeStyleSettings.class);

        AlignmentStrategy alignmentStrategy = AlignmentStrategy.getNullStrategy();

        this.javaBlock=new SimpleJavaBlock(node,wrap,alignmentStrategy,indent,settings,javaSettings,FormattingMode.ADJUST_INDENT_ON_ENTER);
        this.block=SimpleJavaBlock.newJavaBlock(node,settings,javaSettings,FormattingMode.ADJUST_INDENT_ON_ENTER);
    }

    @Override
    public @NotNull TextRange getTextRange() {
        return block.getTextRange();
    }

    @Override
    public @NotNull List<Block> getSubBlocks() {
//        List<Block> blocks = new ArrayList<>();
//        for (ASTNode child : node.getChildren(null)) {
//            if (child.getElementType() != TokenType.WHITE_SPACE) {
//                blocks.add(new ApexLanguageBlock(child, null, getChildIndent(child), settings));
//            }
//        }
//        return blocks;
        return block.getSubBlocks();
    }

    @Override
    public @Nullable Wrap getWrap() {
        return block.getWrap();
    }

    @Override
    public @Nullable Indent getIndent() {
        return block.getIndent();
    }

    @Override
    public @Nullable Alignment getAlignment() {
        return block.getAlignment();
    }

    @Override
    public @Nullable Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return block.getSpacing(child1,child2);
    }

    @Override
    public @NotNull ChildAttributes getChildAttributes(int newChildIndex) {
        return block.getChildAttributes(newChildIndex);
    }

    @Override
    public boolean isIncomplete() {
        return block.isIncomplete();
    }

    @Override
    public boolean isLeaf() {
        return block.isLeaf();
    }

    private Indent getChildIndent(ASTNode child) {
        // Apply indentation rules based on the child node type
        if (isBlockElement(child)) {
            return Indent.getNormalIndent(); // Indent for block elements
        }
        return Indent.getNoneIndent(); // No indent for others
    }

    private boolean isBlockElement(ASTNode node) {
        // Define block elements (e.g., braces, statements, etc.)
//        return "{".equals(node.getText()) || "}".equals(node.getText());
        return false;
    }
}
