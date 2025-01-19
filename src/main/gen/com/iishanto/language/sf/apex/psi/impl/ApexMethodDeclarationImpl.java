/*
 *  Copyright 2014 Mark Borner
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.iishanto.language.sf.apex.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.iishanto.language.sf.apex.psi.ApexTypes.*;
import com.iishanto.language.sf.apex.psi.*;

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
