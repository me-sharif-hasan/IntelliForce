// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexTryStatement extends PsiElement {

  @NotNull
  ApexBlock getBlock();

  @NotNull
  List<ApexCatchClause> getCatchClauseList();

  @Nullable
  ApexFinallyBlock getFinallyBlock();

}
