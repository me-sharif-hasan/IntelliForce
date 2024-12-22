// license.txt
package com.iishanto.apex.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.iishanto.apex.psi.ApexTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.iishanto.apex.psi.*;

public class ApexCatchClauseImpl extends ASTWrapperPsiElement implements ApexCatchClause {

  public ApexCatchClauseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitCatchClause(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexBlock getBlock() {
    return findChildByClass(ApexBlock.class);
  }

  @Override
  @Nullable
  public ApexClassOrInterfaceReference getClassOrInterfaceReference() {
    return findChildByClass(ApexClassOrInterfaceReference.class);
  }

  @Override
  @Nullable
  public ApexVariableModifier getVariableModifier() {
    return findChildByClass(ApexVariableModifier.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
