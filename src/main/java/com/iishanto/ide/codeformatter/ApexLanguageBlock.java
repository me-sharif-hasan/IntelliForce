package com.iishanto.ide.codeformatter;

import com.iishanto.language.sf.apex.psi.ApexTypes;
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
        // Determine the indent for a new line based on the context
        if (isBlockNode(node)) {
            // Inside a block (e.g., method body, class body), new lines should have normal indent
            return new ChildAttributes(Indent.getNormalIndent(), null);
        } else if (isStatementNode(node)) {
            // If the current node is a statement, new lines should align with the statement
            return new ChildAttributes(Indent.getNoneIndent(), null);
        }
        // Default: no additional indent
        return new ChildAttributes(Indent.getNoneIndent(), null);
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
        if (isBlockNode(child.getTreeParent()) && isStatementNode(child)) {
            // Statements inside a block should have normal indent
            return Indent.getNormalIndent();
        } else if (isBlockElement(child)) {
            // Braces or block delimiters should have no indent
            return Indent.getNoneIndent();
        }
        // Default: no indent for other nodes
        return Indent.getNoneIndent();
    }

    private boolean isBlockElement(ASTNode node) {
        // Braces as block delimiters
        return node.getElementType() == ApexTypes.LBRACE || node.getElementType() == ApexTypes.RBRACE;
    }

    private boolean isBlockNode(ASTNode node) {
        // Check if the node represents a block (e.g., method body, class body, if block)
        return node.getElementType() == ApexTypes.METHOD_BLOCK ||
                node.getElementType() == ApexTypes.CLASS_BODY ||
                node.getElementType() == ApexTypes.IF_BLOCK ||
                node.getElementType() == ApexTypes.FOR_BLOCK ||
                node.getElementType() == ApexTypes.WHILE_BLOCK ||
                node.getElementType() == ApexTypes.TRY_BLOCK ||
                node.getElementType() == ApexTypes.CATCH_BLOCK ||
                node.getElementType() == ApexTypes.FINALLY_BLOCK ||
                node.getElementType() == ApexTypes.SWITCH_BLOCK;
    }

    private boolean isStatementNode(ASTNode node) {
        // Check if the node is a statement
        return node.getElementType() == ApexTypes.STATEMENT ||
                node.getElementType() == ApexTypes.ASSIGNMENT_STATEMENT ||
                node.getElementType() == ApexTypes.METHOD_CALL_STATEMENT ||
                node.getElementType() == ApexTypes.RETURN_STATEMENT ||
                node.getElementType() == ApexTypes.THROW_STATEMENT ||
                node.getElementType() == ApexTypes.DML_STATEMENT ||
                node.getElementType() == ApexTypes.LOOP_CONTROL_STATEMENT ||
                node.getElementType() == ApexTypes.DECISION_STATEMENT ||
                node.getElementType() == ApexTypes.LOOP_STATEMENT ||
                node.getElementType() == ApexTypes.TRY_CATCH_STATEMENT;
    }
}