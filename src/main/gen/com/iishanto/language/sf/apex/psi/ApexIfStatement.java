// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexIfStatement extends PsiElement {

  @Nullable
  ApexElseBlock getElseBlock();

  @Nullable
  ApexIfBlock getIfBlock();

  @Nullable
  ApexIfElseBlock getIfElseBlock();

  @NotNull
  ApexExpression getExpression();

}
