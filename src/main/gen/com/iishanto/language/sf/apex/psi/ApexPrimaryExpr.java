// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexPrimaryExpr extends ApexExpression {

  @Nullable
  ApexVariableName getVariableName();

  @NotNull
  List<ApexExpression> getExpressionList();

  @Nullable
  PsiElement getNumber();

  @Nullable
  PsiElement getString();

}
