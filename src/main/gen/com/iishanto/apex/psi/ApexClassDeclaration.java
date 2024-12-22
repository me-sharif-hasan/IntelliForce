// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexClassDeclaration extends ApexDeclaration {

  @NotNull
  List<ApexAnnotation> getAnnotationList();

  @Nullable
  ApexClassBody getClassBody();

  @Nullable
  ApexExtendsClause getExtendsClause();

  @Nullable
  ApexImplementsClause getImplementsClause();

  @Nullable
  ApexSharingModifier getSharingModifier();

  @Nullable
  ApexVirtualOrAbstractModifier getVirtualOrAbstractModifier();

}
