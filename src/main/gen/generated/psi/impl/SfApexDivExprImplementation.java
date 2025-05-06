// This is a generated file. Not intended for manual editing.
package generated.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static generated.GeneratedTypes.*;
import generated.psi.*;

public class SfApexDivExprImplementation extends SfApexExpressionImplementation implements SfApexDivExpr {

  public SfApexDivExprImplementation(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull SfApexVisitor visitor) {
    visitor.visitDivExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SfApexVisitor) accept((SfApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SfApexExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SfApexExpression.class);
  }

}
