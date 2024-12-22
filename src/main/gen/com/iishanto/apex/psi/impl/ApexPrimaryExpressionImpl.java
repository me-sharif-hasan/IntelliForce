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

public class ApexPrimaryExpressionImpl extends ApexExpressionImpl implements ApexPrimaryExpression {

  public ApexPrimaryExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitPrimaryExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexArguments getArguments() {
    return findChildByClass(ApexArguments.class);
  }

  @Override
  @Nullable
  public ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType() {
    return findChildByClass(ApexClassInterfaceOrPrimitiveType.class);
  }

  @Override
  @Nullable
  public ApexExplicitGenericInvocationSuffix getExplicitGenericInvocationSuffix() {
    return findChildByClass(ApexExplicitGenericInvocationSuffix.class);
  }

  @Override
  @Nullable
  public ApexExpression getExpression() {
    return findChildByClass(ApexExpression.class);
  }

  @Override
  @Nullable
  public ApexLiteral getLiteral() {
    return findChildByClass(ApexLiteral.class);
  }

  @Override
  @Nullable
  public ApexNonWildcardTypeArguments getNonWildcardTypeArguments() {
    return findChildByClass(ApexNonWildcardTypeArguments.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
