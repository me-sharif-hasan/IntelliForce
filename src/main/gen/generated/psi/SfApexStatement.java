// This is a generated file. Not intended for manual editing.
package generated.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SfApexStatement extends PsiElement {

  @Nullable
  SfApexAssignmentStatement getAssignmentStatement();

  @Nullable
  SfApexDecisionStatement getDecisionStatement();

  @Nullable
  SfApexLoopControlStatement getLoopControlStatement();

  @Nullable
  SfApexLoopStatement getLoopStatement();

  @Nullable
  SfApexMethodCallStatement getMethodCallStatement();

}
