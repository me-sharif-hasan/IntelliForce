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

public class SfApexEnhancedForLoopImplementation extends ASTWrapperPsiElement implements SfApexEnhancedForLoop {

  public SfApexEnhancedForLoopImplementation(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SfApexVisitor visitor) {
    visitor.visitEnhancedForLoop(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SfApexVisitor) accept((SfApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SfApexForBlock getForBlock() {
    return findNotNullChildByClass(SfApexForBlock.class);
  }

  @Override
  @Nullable
  public SfApexForLoopConditionDeclared getForLoopConditionDeclared() {
    return findChildByClass(SfApexForLoopConditionDeclared.class);
  }

  @Override
  @Nullable
  public SfApexForLoopConditionReference getForLoopConditionReference() {
    return findChildByClass(SfApexForLoopConditionReference.class);
  }

}
