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

public class ApexArrayCreatorRestImpl extends ASTWrapperPsiElement implements ApexArrayCreatorRest {

  public ApexArrayCreatorRestImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitArrayCreatorRest(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexArrayInitializer getArrayInitializer() {
    return findChildByClass(ApexArrayInitializer.class);
  }

  @Override
  @NotNull
  public List<ApexExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexExpression.class);
  }

}