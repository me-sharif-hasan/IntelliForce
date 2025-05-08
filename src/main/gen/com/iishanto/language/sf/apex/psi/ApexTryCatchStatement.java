// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexTryCatchStatement extends PsiElement {

  @NotNull
  List<ApexCatchBlock> getCatchBlockList();

  @Nullable
  ApexFinallyBlock getFinallyBlock();

  @NotNull
  ApexTryBlock getTryBlock();

}
