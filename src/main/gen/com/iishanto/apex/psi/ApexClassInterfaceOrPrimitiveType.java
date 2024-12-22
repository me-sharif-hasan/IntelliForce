// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexClassInterfaceOrPrimitiveType extends PsiElement {

  @Nullable
  ApexClassOrInterfaceReference getClassOrInterfaceReference();

  @Nullable
  ApexListCollection getListCollection();

  @Nullable
  ApexMapCollection getMapCollection();

  @Nullable
  ApexPrimitiveType getPrimitiveType();

  @Nullable
  ApexSetCollection getSetCollection();

}
