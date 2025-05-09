// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.iishanto.language.sf.apex.psi.ApexTypes.*;
import com.iishanto.language.sf.apex.psi.*;

public class ApexLogicalAndExprImpl extends ApexExpressionImpl implements ApexLogicalAndExpr {

  public ApexLogicalAndExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitLogicalAndExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApexExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexExpression.class);
  }

}
