package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ApexBlockFormatter extends AbstractBlock {
    int blockId=0;
    protected ApexBlockFormatter(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,int blockId) {
        super(node, wrap, alignment);
        this.blockId=blockId;
    }

    public int getBlockId() {
        return blockId;
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
    List <ASTNode> tokenStream = new java.util.ArrayList<>();
    Map<Integer,Boolean> blockTypeMap=new HashMap<>();
    @Override
    protected List<Block> buildChildren() {
        ASTNode astNode = myNode.getFirstChildNode();
        ASTNode []allChildren = myNode.getChildren(null);
        if(allChildren.length>0){
            System.out.println("All children: "+allChildren[0].getElementType()+" "+allChildren[0].getElementType().getClass().getName());
            tokenStream.clear();
            blockTypeMap.clear();
        }
        List<Block>  blocks= new java.util.ArrayList<>();
        while (astNode != null) {
            if(!astNode.getElementType().equals(JavaTokenType.WHITE_SPACE)) {
                System.out.println("Build children called "+astNode.getElementType()+" "+astNode.getElementType().getClass().getName());
                tokenStream.add(astNode);
                blocks.add(new ApexBlockFormatter(astNode, Wrap.createWrap(WrapType.NONE, false), null,tokenStream.size()));
            }
            astNode = astNode.getTreeNext();
        }
        return blocks;
    }

    public boolean shouldBreakLine(Block block) {
        if (block instanceof ApexBlockFormatter blockFormatterForApex&&blockFormatterForApex.getBlockId()>=2) {
            if(tokenStream.get(blockFormatterForApex.getBlockId()-2)!=null&&tokenStream.get(blockFormatterForApex.getBlockId()-2).getElementType().equals(JavaTokenType.LBRACE)){
                return true;
            }
            if(tokenStream.get(blockFormatterForApex.getBlockId()-2)!=null&&tokenStream.get(blockFormatterForApex.getBlockId()-2).getElementType().equals(JavaTokenType.SEMICOLON)){
                return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable Spacing getSpacing(@Nullable Block block, @NotNull Block block1) {
        if(block==null){
            System.out.println("Block id is 0");
            for (ASTNode astNode:tokenStream) {
                System.out.print(astNode.getElementType().toString()+" ");
            }
            System.out.println();
        }
        if(this.shouldBreakLine(block1)){
            return Spacing.createSpacing(0, 0, 1, true, 0);
        }
        return null;
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
