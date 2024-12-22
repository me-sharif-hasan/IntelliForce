// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexStatement extends PsiElement {

  @Nullable
  ApexBlock getBlock();

  @Nullable
  ApexClassicForStatement getClassicForStatement();

  @Nullable
  ApexDoStatement getDoStatement();

  @Nullable
  ApexEnhancedForStatement getEnhancedForStatement();

  @Nullable
  ApexExpression getExpression();

  @Nullable
  ApexIfStatement getIfStatement();

  @Nullable
  ApexTryStatement getTryStatement();

  @Nullable
  ApexWhileStatement getWhileStatement();

}
