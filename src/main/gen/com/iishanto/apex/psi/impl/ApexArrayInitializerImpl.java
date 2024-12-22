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

public class ApexArrayInitializerImpl extends ASTWrapperPsiElement implements ApexArrayInitializer {

  public ApexArrayInitializerImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitArrayInitializer(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApexVariableInitializer> getVariableInitializerList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexVariableInitializer.class);
  }

}