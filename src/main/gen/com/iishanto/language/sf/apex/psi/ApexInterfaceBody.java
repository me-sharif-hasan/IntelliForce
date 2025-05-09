// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexInterfaceBody extends PsiElement {

  @NotNull
  List<ApexApexClassOrInterface> getApexClassOrInterfaceList();

  @NotNull
  List<ApexClassDefinition> getClassDefinitionList();

  @NotNull
  List<ApexClassMember> getClassMemberList();

  @NotNull
  List<ApexInterfaceSignature> getInterfaceSignatureList();

}
