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

public class ApexCreatorImpl extends ASTWrapperPsiElement implements ApexCreator {

  public ApexCreatorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitCreator(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexArrayCreatorRest getArrayCreatorRest() {
    return findChildByClass(ApexArrayCreatorRest.class);
  }

  @Override
  @Nullable
  public ApexClassCreatorRest getClassCreatorRest() {
    return findChildByClass(ApexClassCreatorRest.class);
  }

  @Override
  @NotNull
  public ApexCreatedName getCreatedName() {
    return findNotNullChildByClass(ApexCreatedName.class);
  }

  @Override
  @Nullable
  public ApexNonWildcardTypeArguments getNonWildcardTypeArguments() {
    return findChildByClass(ApexNonWildcardTypeArguments.class);
  }

}
