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

public class ApexClassBodyImpl extends ASTWrapperPsiElement implements ApexClassBody {

  public ApexClassBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitClassBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApexApexClassOrInterface> getApexClassOrInterfaceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexApexClassOrInterface.class);
  }

  @Override
  @NotNull
  public List<ApexClassDefinition> getClassDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexClassDefinition.class);
  }

  @Override
  @NotNull
  public List<ApexClassMember> getClassMemberList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexClassMember.class);
  }

}
