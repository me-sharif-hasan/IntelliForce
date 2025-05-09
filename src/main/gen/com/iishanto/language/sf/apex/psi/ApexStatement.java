// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexStatement extends PsiElement {

  @Nullable
  ApexAssignmentStatement getAssignmentStatement();

  @Nullable
  ApexDecisionStatement getDecisionStatement();

  @Nullable
  ApexDmlStatement getDmlStatement();

  @Nullable
  ApexLoopControlStatement getLoopControlStatement();

  @Nullable
  ApexLoopStatement getLoopStatement();

  @Nullable
  ApexMethodCallStatement getMethodCallStatement();

  @Nullable
  ApexReturnStatement getReturnStatement();

  @Nullable
  ApexThrowStatement getThrowStatement();

  @Nullable
  ApexTryCatchStatement getTryCatchStatement();

}
