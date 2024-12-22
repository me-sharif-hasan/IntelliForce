// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexMethodDeclaration extends ApexDeclaration {

  @NotNull
  List<ApexAnnotation> getAnnotationList();

  @Nullable
  ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType();

  @Nullable
  ApexMethodBody getMethodBody();

  @Nullable
  ApexParameters getParameters();

  @NotNull
  List<ApexStaticOrTransientModifier> getStaticOrTransientModifierList();

  @Nullable
  ApexVirtualOrAbstractModifier getVirtualOrAbstractModifier();

}
