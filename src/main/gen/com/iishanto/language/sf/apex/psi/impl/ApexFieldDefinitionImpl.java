// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.iishanto.language.sf.apex.psi.ApexTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.iishanto.language.sf.apex.psi.*;

public class ApexFieldDefinitionImpl extends ASTWrapperPsiElement implements ApexFieldDefinition {

  public ApexFieldDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitFieldDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApexMethodBlock> getMethodBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexMethodBlock.class);
  }

  @Override
  @NotNull
  public ApexTypeIdentifier getTypeIdentifier() {
    return findNotNullChildByClass(ApexTypeIdentifier.class);
  }

  @Override
  @NotNull
  public ApexVariableName getVariableName() {
    return findNotNullChildByClass(ApexVariableName.class);
  }

}
