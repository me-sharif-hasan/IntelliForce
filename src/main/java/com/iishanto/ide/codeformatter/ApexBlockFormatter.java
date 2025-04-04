package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ApexBlockFormatter extends AbstractBlock {
    protected ApexBlockFormatter(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
        super(node, wrap, alignment);
    }

    private boolean isNestedScope(ASTNode astNode) {
        String type = astNode.getElementType().toString();
        return (
                type.contains("JSHELL_ROOT_CLASS") ||
                        type.contains("METHOD_DECLARATION") ||
                        type.contains("FIELD_DECLARATION") ||
                        type.contains("BLOCK_STATEMENT")
        );
    }

    @Override
    protected List<Block> buildChildren() {
        ASTNode astNode = myNode.getFirstChildNode();
        while (astNode != null) {
            System.out.println("Building child: <<<" + astNode.getText() + " >>> " + astNode.getElementType());
            if (isNestedScope(astNode)) {
                return List.of(new ApexBlockFormatter(astNode, Wrap.createWrap(WrapType.ALWAYS, false), null));
            }
            else if (!astNode.getText().trim().isEmpty()) {
                return List.of(new ApexBlockFormatter(astNode, Wrap.createWrap(WrapType.NONE, false), null));
            }
            astNode = astNode.getTreeNext();
        }
        return List.of();
    }

    @Override
    public @Nullable Spacing getSpacing(@Nullable Block block, @NotNull Block block1) {
        if(block!=null && block instanceof ApexBlockFormatter && block1 instanceof ApexBlockFormatter blockFormatterForApex){
            System.out.println("Spacing: <<<" + blockFormatterForApex.getNode().getText() + " >>> " + blockFormatterForApex.getNode().getElementType());
        }
        return null;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
