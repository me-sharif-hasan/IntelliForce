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

public class SfApexAssignmentStatementImplementation extends ASTWrapperPsiElement implements SfApexAssignmentStatement {

  public SfApexAssignmentStatementImplementation(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SfApexVisitor visitor) {
    visitor.visitAssignmentStatement(this);
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
  public SfApexFieldDefinition getFieldDefinition() {
    return findChildByClass(SfApexFieldDefinition.class);
  }

  @Override
  @Nullable
  public SfApexExpression getExpression() {
    return findChildByClass(SfApexExpression.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
