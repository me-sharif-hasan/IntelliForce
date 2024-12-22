// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexClassBody extends PsiElement {

  @NotNull
  List<ApexDeclaration> getDeclarationList();

  @NotNull
  List<ApexBlock> getBlockList();

  @NotNull
  List<ApexFieldDeclarator> getFieldDeclaratorList();

  @NotNull
  List<ApexStaticBlock> getStaticBlockList();

}
