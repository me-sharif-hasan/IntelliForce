package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import generated.GeneratedTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ApexBlockFormatter extends AbstractBlock {
    private static final Set<IElementType> INDENTED_BLOCKS = Set.of(
//            GeneratedTypes.CLASS_BODY,
            GeneratedTypes.METHOD_BODY,
            GeneratedTypes.IF_STATEMENT,
            GeneratedTypes.FOR_LOOP,
            GeneratedTypes.WHILE_LOOP,
            GeneratedTypes.DO_WHILE_LOOP,
            GeneratedTypes.SWITCH_STATEMENT
    );

    private static final Set<IElementType> LINE_BREAK_BLOCKS = Set.of(
            GeneratedTypes.METHOD_DEFINITION
//            GeneratedTypes.CLASS_BODY
//            GeneratedTypes.INTERFACE_DECLARATION
    );

    protected ApexBlockFormatter(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
        super(node, wrap, alignment);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            IElementType elementType = child.getElementType();
            if (elementType != TokenType.WHITE_SPACE) {
                Wrap wrap = null;
                boolean needsLineBreak = LINE_BREAK_BLOCKS.contains(elementType) || INDENTED_BLOCKS.contains(elementType);
                if (needsLineBreak) {
                    wrap = Wrap.createWrap(WrapType.NORMAL, true);
                }
                blocks.add(new ApexBlockFormatter(child, wrap, null));
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public @Nullable Spacing getSpacing(@Nullable Block block, @NotNull Block block1) {
        if (!(block1 instanceof ApexBlockFormatter apexBlock)) {
            return null;
        }

        IElementType type = apexBlock.getNode().getElementType();
        if (LINE_BREAK_BLOCKS.contains(type)) {
            return Spacing.createSpacing(0, 0, 2, true, 1); // Two line breaks before methods/classes
        }
        if (INDENTED_BLOCKS.contains(type)) {
            return Spacing.createSpacing(0, 0, 1, true, 1); // One line break for blocks
        }
        if (type == GeneratedTypes.COMMENT) {
            return Spacing.createSpacing(0, 0, 1, false, 0); // No extra lines after comments
        }
        if (type == GeneratedTypes.RBRACE) {
            return Spacing.createSpacing(0, 0, 1, true, 0); // Line break after braces
        }
        return Spacing.createSpacing(1, 1, 0, false, 0); // Default: one space, no line breaks
    }

    @Override
    public @Nullable Indent getIndent() {
        IElementType type = myNode.getElementType();
        if (INDENTED_BLOCKS.contains(type)) {
            return Indent.getNormalIndent(); // Standard indent for blocks
        }
        if (type == GeneratedTypes.STATEMENT || type == GeneratedTypes.WHEN_STATEMENT) {
            return Indent.getNormalIndent(); // Indent statements inside blocks
        }
        if (type == GeneratedTypes.RBRACE || type == GeneratedTypes.LBRACE) {
            return Indent.getNoneIndent(); // No indent for braces
        }
        return Indent.getNoneIndent(); // Default: no indent
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}