// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexCreatedName extends PsiElement {

  @Nullable
  ApexPrimitiveType getPrimitiveType();

  @NotNull
  List<ApexTypeArgumentsOrDiamond> getTypeArgumentsOrDiamondList();

}
