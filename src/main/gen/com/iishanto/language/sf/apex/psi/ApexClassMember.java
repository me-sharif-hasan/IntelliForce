// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexClassMember extends PsiElement {

  @Nullable
  ApexApexClass getApexClass();

  @Nullable
  ApexApexEnum getApexEnum();

  @Nullable
  ApexApexInterface getApexInterface();

  @Nullable
  ApexConstructorDefinition getConstructorDefinition();

  @Nullable
  ApexFieldDefinition getFieldDefinition();

  @Nullable
  ApexMemberModifier getMemberModifier();

  @Nullable
  ApexMethodDefinition getMethodDefinition();

}
