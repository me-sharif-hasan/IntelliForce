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

public class ApexLoopStatementImpl extends ASTWrapperPsiElement implements ApexLoopStatement {

  public ApexLoopStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitLoopStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexDoWhileLoop getDoWhileLoop() {
    return findChildByClass(ApexDoWhileLoop.class);
  }

  @Override
  @Nullable
  public ApexForLoop getForLoop() {
    return findChildByClass(ApexForLoop.class);
  }

  @Override
  @Nullable
  public ApexWhileLoop getWhileLoop() {
    return findChildByClass(ApexWhileLoop.class);
  }

}
