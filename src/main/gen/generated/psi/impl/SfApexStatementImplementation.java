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

public class SfApexStatementImplementation extends ASTWrapperPsiElement implements SfApexStatement {

  public SfApexStatementImplementation(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SfApexVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SfApexVisitor) accept((SfApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SfApexAssignmentStatement getAssignmentStatement() {
    return findChildByClass(SfApexAssignmentStatement.class);
  }

  @Override
  @Nullable
  public SfApexDecisionStatement getDecisionStatement() {
    return findChildByClass(SfApexDecisionStatement.class);
  }

  @Override
  @Nullable
  public SfApexLoopControlStatement getLoopControlStatement() {
    return findChildByClass(SfApexLoopControlStatement.class);
  }

  @Override
  @Nullable
  public SfApexLoopStatement getLoopStatement() {
    return findChildByClass(SfApexLoopStatement.class);
  }

  @Override
  @Nullable
  public SfApexMethodCallStatement getMethodCallStatement() {
    return findChildByClass(SfApexMethodCallStatement.class);
  }

}
