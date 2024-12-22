// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexPrimaryExpression extends ApexExpression {

  @Nullable
  ApexArguments getArguments();

  @Nullable
  ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType();

  @Nullable
  ApexExplicitGenericInvocationSuffix getExplicitGenericInvocationSuffix();

  @Nullable
  ApexExpression getExpression();

  @Nullable
  ApexLiteral getLiteral();

  @Nullable
  ApexNonWildcardTypeArguments getNonWildcardTypeArguments();

  @Nullable
  PsiElement getIdentifier();

}
