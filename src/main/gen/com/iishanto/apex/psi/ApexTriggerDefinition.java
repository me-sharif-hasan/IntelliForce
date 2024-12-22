// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexTriggerDefinition extends PsiElement {

  @Nullable
  ApexTriggerBody getTriggerBody();

  @NotNull
  List<ApexTriggerParameter> getTriggerParameterList();

}
