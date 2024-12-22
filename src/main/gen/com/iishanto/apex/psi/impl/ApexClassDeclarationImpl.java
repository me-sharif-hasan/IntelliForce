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

public class ApexClassDeclarationImpl extends ApexDeclarationImpl implements ApexClassDeclaration {

  public ApexClassDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitClassDeclaration(this);
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
  public ApexClassBody getClassBody() {
    return findChildByClass(ApexClassBody.class);
  }

  @Override
  @Nullable
  public ApexExtendsClause getExtendsClause() {
    return findChildByClass(ApexExtendsClause.class);
  }

  @Override
  @Nullable
  public ApexImplementsClause getImplementsClause() {
    return findChildByClass(ApexImplementsClause.class);
  }

  @Override
  @Nullable
  public ApexSharingModifier getSharingModifier() {
    return findChildByClass(ApexSharingModifier.class);
  }

  @Override
  @Nullable
  public ApexVirtualOrAbstractModifier getVirtualOrAbstractModifier() {
    return findChildByClass(ApexVirtualOrAbstractModifier.class);
  }

}
