package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ApexLanguageBlock implements Block {
    private final ASTNode node;
    private final Indent indent;
    private final CodeStyleSettings settings;

    public ApexLanguageBlock(ASTNode node, @Nullable Wrap wrap, Indent indent, CodeStyleSettings settings) {
        this.node = node;
        this.indent = indent;
        this.settings = settings;
    }

    @Override
    public @NotNull TextRange getTextRange() {
        return node.getTextRange();
    }

    @Override
    public @NotNull List<Block> getSubBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (ASTNode child : node.getChildren(null)) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                blocks.add(new ApexLanguageBlock(child, null, getChildIndent(child), settings));
            }
        }
        return blocks;
    }

    @Override
    public @Nullable Wrap getWrap() {
        return null;
    }

    @Override
    public @Nullable Indent getIndent() {
        return indent;
    }

    @Override
    public @Nullable Alignment getAlignment() {
        return null;
    }

    @Override
    public @Nullable Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return null;
    }

    @Override
    public @NotNull ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(Indent.getNormalIndent(), null);
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return node.getFirstChildNode() == null;
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
        return "{".equals(node.getText()) || "}".equals(node.getText());
    }
}
