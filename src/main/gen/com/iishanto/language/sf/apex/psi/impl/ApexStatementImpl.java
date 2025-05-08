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
  public ApexAssignmentStatement getAssignmentStatement() {
    return findChildByClass(ApexAssignmentStatement.class);
  }

  @Override
  @Nullable
  public ApexDecisionStatement getDecisionStatement() {
    return findChildByClass(ApexDecisionStatement.class);
  }

  @Override
  @Nullable
  public ApexDmlStatement getDmlStatement() {
    return findChildByClass(ApexDmlStatement.class);
  }

  @Override
  @Nullable
  public ApexLoopControlStatement getLoopControlStatement() {
    return findChildByClass(ApexLoopControlStatement.class);
  }

  @Override
  @Nullable
  public ApexLoopStatement getLoopStatement() {
    return findChildByClass(ApexLoopStatement.class);
  }

  @Override
  @Nullable
  public ApexMethodCallStatement getMethodCallStatement() {
    return findChildByClass(ApexMethodCallStatement.class);
  }

  @Override
  @Nullable
  public ApexReturnStatement getReturnStatement() {
    return findChildByClass(ApexReturnStatement.class);
  }

  @Override
  @Nullable
  public ApexThrowStatement getThrowStatement() {
    return findChildByClass(ApexThrowStatement.class);
  }

  @Override
  @Nullable
  public ApexTryCatchStatement getTryCatchStatement() {
    return findChildByClass(ApexTryCatchStatement.class);
  }

}
