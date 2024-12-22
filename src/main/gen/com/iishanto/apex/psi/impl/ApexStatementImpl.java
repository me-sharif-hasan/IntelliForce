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

public class ApexStatementImpl extends ASTWrapperPsiElement implements ApexStatement {

  public ApexStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitStatement(this);
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
  public ApexClassicForStatement getClassicForStatement() {
    return findChildByClass(ApexClassicForStatement.class);
  }

  @Override
  @Nullable
  public ApexDoStatement getDoStatement() {
    return findChildByClass(ApexDoStatement.class);
  }

  @Override
  @Nullable
  public ApexEnhancedForStatement getEnhancedForStatement() {
    return findChildByClass(ApexEnhancedForStatement.class);
  }

  @Override
  @Nullable
  public ApexExpression getExpression() {
    return findChildByClass(ApexExpression.class);
  }

  @Override
  @Nullable
  public ApexIfStatement getIfStatement() {
    return findChildByClass(ApexIfStatement.class);
  }

  @Override
  @Nullable
  public ApexTryStatement getTryStatement() {
    return findChildByClass(ApexTryStatement.class);
  }

  @Override
  @Nullable
  public ApexWhileStatement getWhileStatement() {
    return findChildByClass(ApexWhileStatement.class);
  }

}
