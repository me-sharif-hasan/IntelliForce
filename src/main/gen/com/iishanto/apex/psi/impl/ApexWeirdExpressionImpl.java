// license.txt
package com.iishanto.apex.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.iishanto.apex.psi.ApexTypes.*;
import com.iishanto.apex.psi.*;

public class ApexWeirdExpressionImpl extends ApexExpressionImpl implements ApexWeirdExpression {

  public ApexWeirdExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitWeirdExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApexExpression getExpression() {
    return findNotNullChildByClass(ApexExpression.class);
  }

  @Override
  @NotNull
  public ApexInnerCreator getInnerCreator() {
    return findNotNullChildByClass(ApexInnerCreator.class);
  }

  @Override
  @Nullable
  public ApexNonWildcardTypeArguments getNonWildcardTypeArguments() {
    return findChildByClass(ApexNonWildcardTypeArguments.class);
  }

}
