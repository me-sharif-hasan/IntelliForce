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
  public List<ApexDeclaration> getDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexDeclaration.class);
  }

  @Override
  @NotNull
  public List<ApexBlock> getBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexBlock.class);
  }

  @Override
  @NotNull
  public List<ApexFieldDeclarator> getFieldDeclaratorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexFieldDeclarator.class);
  }

  @Override
  @NotNull
  public List<ApexStaticBlock> getStaticBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApexStaticBlock.class);
  }

}
