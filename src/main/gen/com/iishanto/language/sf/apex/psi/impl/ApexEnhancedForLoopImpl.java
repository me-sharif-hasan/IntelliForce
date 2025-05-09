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

public class ApexEnhancedForLoopImpl extends ASTWrapperPsiElement implements ApexEnhancedForLoop {

  public ApexEnhancedForLoopImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitEnhancedForLoop(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexForBlock getForBlock() {
    return findChildByClass(ApexForBlock.class);
  }

  @Override
  @Nullable
  public ApexForLoopConditionDeclared getForLoopConditionDeclared() {
    return findChildByClass(ApexForLoopConditionDeclared.class);
  }

  @Override
  @Nullable
  public ApexForLoopConditionReference getForLoopConditionReference() {
    return findChildByClass(ApexForLoopConditionReference.class);
  }

}
