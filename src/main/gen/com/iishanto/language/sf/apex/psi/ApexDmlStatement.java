// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexDmlStatement extends PsiElement {

  @Nullable
  ApexDeleteStatement getDeleteStatement();

  @Nullable
  ApexInsertStatement getInsertStatement();

  @Nullable
  ApexMergeStatement getMergeStatement();

  @Nullable
  ApexUpdateStatement getUpdateStatement();

  @Nullable
  ApexUpsertStatement getUpsertStatement();

}
