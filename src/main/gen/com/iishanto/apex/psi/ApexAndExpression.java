// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexAndExpression extends ApexExpression {

  @NotNull
  List<ApexExpression> getExpressionList();

}
