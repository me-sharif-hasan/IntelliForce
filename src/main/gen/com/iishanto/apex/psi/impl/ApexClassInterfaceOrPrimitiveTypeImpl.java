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

public class ApexClassInterfaceOrPrimitiveTypeImpl extends ASTWrapperPsiElement implements ApexClassInterfaceOrPrimitiveType {

  public ApexClassInterfaceOrPrimitiveTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApexVisitor visitor) {
    visitor.visitClassInterfaceOrPrimitiveType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApexVisitor) accept((ApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApexClassOrInterfaceReference getClassOrInterfaceReference() {
    return findChildByClass(ApexClassOrInterfaceReference.class);
  }

  @Override
  @Nullable
  public ApexListCollection getListCollection() {
    return findChildByClass(ApexListCollection.class);
  }

  @Override
  @Nullable
  public ApexMapCollection getMapCollection() {
    return findChildByClass(ApexMapCollection.class);
  }

  @Override
  @Nullable
  public ApexPrimitiveType getPrimitiveType() {
    return findChildByClass(ApexPrimitiveType.class);
  }

  @Override
  @Nullable
  public ApexSetCollection getSetCollection() {
    return findChildByClass(ApexSetCollection.class);
  }

}
