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

public class SfApexApexClassImplementation extends ASTWrapperPsiElement implements SfApexApexClass {

  public SfApexApexClassImplementation(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SfApexVisitor visitor) {
    visitor.visitApexClass(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SfApexVisitor) accept((SfApexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SfApexAnnotation getAnnotation() {
    return findChildByClass(SfApexAnnotation.class);
  }

  @Override
  @NotNull
  public SfApexClassAccessModifier getClassAccessModifier() {
    return findNotNullChildByClass(SfApexClassAccessModifier.class);
  }

  @Override
  @NotNull
  public SfApexClassDefinition getClassDefinition() {
    return findNotNullChildByClass(SfApexClassDefinition.class);
  }

  @Override
  @NotNull
  public SfApexModifiers getModifiers() {
    return findNotNullChildByClass(SfApexModifiers.class);
  }

}
