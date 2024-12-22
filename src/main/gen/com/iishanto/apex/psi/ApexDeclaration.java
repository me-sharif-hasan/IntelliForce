// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexDeclaration extends ApexDeclarationElement {

  @Nullable
  ApexVisibility getVisibility();

  @NotNull
  PsiElement getIdentifier();

  //WARNING: getPresentation(...) is skipped
  //matching getPresentation(ApexDeclaration, ...)
  //methods are not found in ApexPsiImplUtil

}
