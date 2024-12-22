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

public class ApexEnhancedForStatementImpl extends ASTWrapperPsiElement implements ApexEnhancedForStatement {

  public ApexEnhancedForStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitEnhancedForStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType() {
    return findNotNullChildByClass(ApexClassInterfaceOrPrimitiveType.class);
  }

  @Override
  @Nullable
  public ApexExpression getExpression() {
    return findChildByClass(ApexExpression.class);
  }

  @Override
  @Nullable
  public ApexStatement getStatement() {
    return findChildByClass(ApexStatement.class);
  }

  @Override
  @Nullable
  public ApexVariableModifier getVariableModifier() {
    return findChildByClass(ApexVariableModifier.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
