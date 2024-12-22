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

public class ApexConstructorDeclarationImpl extends ApexDeclarationImpl implements ApexConstructorDeclaration {

  public ApexConstructorDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitConstructorDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexConstructorBody getConstructorBody() {
    return findChildByClass(ApexConstructorBody.class);
  }

  @Override
  @NotNull
  public ApexParameters getParameters() {
    return findNotNullChildByClass(ApexParameters.class);
  }

}
