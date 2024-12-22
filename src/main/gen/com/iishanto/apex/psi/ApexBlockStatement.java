// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexBlockStatement extends PsiElement {

  @Nullable
  ApexDeclaration getDeclaration();

  @Nullable
  ApexLocalVariableDeclarationStatement getLocalVariableDeclarationStatement();

  @Nullable
  ApexStatement getStatement();

}
