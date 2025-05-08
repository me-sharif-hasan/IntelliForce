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

public class ApexClassMemberImpl extends ASTWrapperPsiElement implements ApexClassMember {

  public ApexClassMemberImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitClassMember(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexApexClass getApexClass() {
    return findChildByClass(ApexApexClass.class);
  }

  @Override
  @Nullable
  public ApexApexEnum getApexEnum() {
    return findChildByClass(ApexApexEnum.class);
  }

  @Override
  @Nullable
  public ApexApexInterface getApexInterface() {
    return findChildByClass(ApexApexInterface.class);
  }

  @Override
  @Nullable
  public ApexConstructorDefinition getConstructorDefinition() {
    return findChildByClass(ApexConstructorDefinition.class);
  }

  @Override
  @Nullable
  public ApexFieldDefinition getFieldDefinition() {
    return findChildByClass(ApexFieldDefinition.class);
  }

  @Override
  @Nullable
  public ApexMemberModifier getMemberModifier() {
    return findChildByClass(ApexMemberModifier.class);
  }

  @Override
  @Nullable
  public ApexMethodDefinition getMethodDefinition() {
    return findChildByClass(ApexMethodDefinition.class);
  }

}
