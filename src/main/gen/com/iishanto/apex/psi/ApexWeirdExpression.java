// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexWeirdExpression extends ApexExpression {

  @NotNull
  ApexExpression getExpression();

  @NotNull
  ApexInnerCreator getInnerCreator();

  @Nullable
  ApexNonWildcardTypeArguments getNonWildcardTypeArguments();

}
