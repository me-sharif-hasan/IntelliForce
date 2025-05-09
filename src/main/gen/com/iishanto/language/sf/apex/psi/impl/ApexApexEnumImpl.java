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

public class ApexApexEnumImpl extends ASTWrapperPsiElement implements ApexApexEnum {

  public ApexApexEnumImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitApexEnum(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexAnnotation getAnnotation() {
    return findChildByClass(ApexAnnotation.class);
  }

  @Override
  @NotNull
  public ApexEnumAccessModifier getEnumAccessModifier() {
    return findNotNullChildByClass(ApexEnumAccessModifier.class);
  }

  @Override
  @NotNull
  public ApexEnumDefinition getEnumDefinition() {
    return findNotNullChildByClass(ApexEnumDefinition.class);
  }

  @Override
  @NotNull
  public ApexModifiers getModifiers() {
    return findNotNullChildByClass(ApexModifiers.class);
  }

}
