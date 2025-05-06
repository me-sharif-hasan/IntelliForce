// This is a generated file. Not intended for manual editing.
package generated.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static generated.GeneratedTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import generated.psi.*;

public class SfApexConditionTrailImplementation extends ASTWrapperPsiElement implements SfApexConditionTrail {

  public SfApexConditionTrailImplementation(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SfApexVisitor visitor) {
    visitor.visitConditionTrail(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SfApexVisitor) accept((SfApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SfApexIterationForLoop getIterationForLoop() {
    return findChildByClass(SfApexIterationForLoop.class);
  }

  @Override
  @Nullable
  public SfApexTraditionalForLoopCondition getTraditionalForLoopCondition() {
    return findChildByClass(SfApexTraditionalForLoopCondition.class);
  }

}
