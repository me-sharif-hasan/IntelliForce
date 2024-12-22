// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexInnerCreator extends PsiElement {

  @NotNull
  ApexClassCreatorRest getClassCreatorRest();

  @Nullable
  ApexNonWildcardTypeArgumentsOrDiamond getNonWildcardTypeArgumentsOrDiamond();

  @NotNull
  PsiElement getIdentifier();

}
