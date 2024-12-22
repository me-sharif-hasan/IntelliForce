// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexClassicForStatement extends PsiElement {

  @Nullable
  ApexExpression getExpression();

  @Nullable
  ApexForInit getForInit();

  @Nullable
  ApexForUpdate getForUpdate();

  @Nullable
  ApexStatement getStatement();

}
