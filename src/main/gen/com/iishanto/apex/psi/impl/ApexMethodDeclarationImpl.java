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

public class ApexMethodDeclarationImpl extends ApexDeclarationImpl implements ApexMethodDeclaration {

  public ApexMethodDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitMethodDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApexAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexAnnotation.class);
  }

  @Override
  @Nullable
  public ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType() {
    return findChildByClass(ApexClassInterfaceOrPrimitiveType.class);
  }

  @Override
  @Nullable
  public ApexMethodBody getMethodBody() {
    return findChildByClass(ApexMethodBody.class);
  }

  @Override
  @Nullable
  public ApexParameters getParameters() {
    return findChildByClass(ApexParameters.class);
  }

  @Override
  @NotNull
  public List<ApexStaticOrTransientModifier> getStaticOrTransientModifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexStaticOrTransientModifier.class);
  }

  @Override
  @Nullable
  public ApexVirtualOrAbstractModifier getVirtualOrAbstractModifier() {
    return findChildByClass(ApexVirtualOrAbstractModifier.class);
  }

}
