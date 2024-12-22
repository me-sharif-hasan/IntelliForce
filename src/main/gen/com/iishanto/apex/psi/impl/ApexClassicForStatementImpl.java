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

public class ApexClassicForStatementImpl extends ASTWrapperPsiElement implements ApexClassicForStatement {

  public ApexClassicForStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitClassicForStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexExpression getExpression() {
    return findChildByClass(ApexExpression.class);
  }

  @Override
  @Nullable
  public ApexForInit getForInit() {
    return findChildByClass(ApexForInit.class);
  }

  @Override
  @Nullable
  public ApexForUpdate getForUpdate() {
    return findChildByClass(ApexForUpdate.class);
  }

  @Override
  @Nullable
  public ApexStatement getStatement() {
    return findChildByClass(ApexStatement.class);
  }

}
