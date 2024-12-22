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

public class ApexCreatedNameImpl extends ASTWrapperPsiElement implements ApexCreatedName {

  public ApexCreatedNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitCreatedName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexPrimitiveType getPrimitiveType() {
    return findChildByClass(ApexPrimitiveType.class);
  }

  @Override
  @NotNull
  public List<ApexTypeArgumentsOrDiamond> getTypeArgumentsOrDiamondList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexTypeArgumentsOrDiamond.class);
  }

}