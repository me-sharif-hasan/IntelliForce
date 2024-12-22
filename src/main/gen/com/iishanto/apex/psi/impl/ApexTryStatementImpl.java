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

public class ApexTryStatementImpl extends ASTWrapperPsiElement implements ApexTryStatement {

  public ApexTryStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitTryStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApexBlock getBlock() {
    return findNotNullChildByClass(ApexBlock.class);
  }

  @Override
  @NotNull
  public List<ApexCatchClause> getCatchClauseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexCatchClause.class);
  }

  @Override
  @Nullable
  public ApexFinallyBlock getFinallyBlock() {
    return findChildByClass(ApexFinallyBlock.class);
  }

}
