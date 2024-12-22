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

public class ApexInterfaceDeclarationImpl extends ApexDeclarationImpl implements ApexInterfaceDeclaration {

  public ApexInterfaceDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitInterfaceDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexExtendsClause getExtendsClause() {
    return findChildByClass(ApexExtendsClause.class);
  }

  @Override
  @Nullable
  public ApexInterfaceBody getInterfaceBody() {
    return findChildByClass(ApexInterfaceBody.class);
  }

}
