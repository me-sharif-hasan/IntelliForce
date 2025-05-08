// This is a generated file. Not intended for manual editing.
package com.iishanto.language.sf.apex.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.iishanto.language.sf.apex.psi.ApexTypes.*;
import static com.iishanto.language.sf.apex.parser.ApexParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ApexParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return ApexFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADD_EXPR, ARRAY_ACCESS_EXPR, ASSIGNMENT_EXPR, BITWISE_AND_EXPR,
      BITWISE_OR_EXPR, BITWISE_XOR_EXPR, DIV_EXPR, EXPRESSION,
      LOGICAL_AND_EXPR, LOGICAL_EQUAL_EQUAL_EXPR, LOGICAL_EQUAL_EXPR, LOGICAL_GREATER_EQUAL_EXPR,
      LOGICAL_GREATER_EXPR, LOGICAL_LESS_EQUAL_EXPR, LOGICAL_LESS_EXPR, LOGICAL_NOT_EQUAL_EQUAL_EXPR,
      LOGICAL_NOT_EQUAL_EXPR, LOGICAL_NOT_EXPR, LOGICAL_OR_EXPR, MIN_EXPR,
      MUL_EXPR, NEGATION_EXPR, PRIMARY_EXPR, SHORTHAND_ASSIGNMENT_EXPR,
      TERNARY_EXPR, TYPE_CAST_EXPR, UNARY_POSTFIX_DECREMENT_EXPR, UNARY_POSTFIX_INCREMENT_EXPR,
      UNARY_PREFIX_DECREMENT_EXPR, UNARY_PREFIX_INCREMENT_EXPR),
  };

  /* ********************************************************** */
  // expression
  public static boolean AllowedSwitchType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AllowedSwitchType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ALLOWED_SWITCH_TYPE, "<allowed switch type>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AT TypeIdentifier argument_group?
  public static boolean Annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT);
    r = r && TypeIdentifier(b, l + 1);
    r = r && Annotation_2(b, l + 1);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  // argument_group?
  private static boolean Annotation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_2")) return false;
    argument_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Annotation? ClassAccessModifier Modifiers ClassDefinition
  public static boolean ApexClass(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexClass")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, APEX_CLASS, "<apex class>");
    r = ApexClass_0(b, l + 1);
    r = r && ClassAccessModifier(b, l + 1);
    r = r && Modifiers(b, l + 1);
    r = r && ClassDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation?
  private static boolean ApexClass_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexClass_0")) return false;
    Annotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Annotation? ClassAccessModifier Modifiers ClassDefinition
  public static boolean ApexClassOrInterface(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexClassOrInterface")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, APEX_CLASS_OR_INTERFACE, "<apex class or interface>");
    r = ApexClassOrInterface_0(b, l + 1);
    r = r && ClassAccessModifier(b, l + 1);
    r = r && Modifiers(b, l + 1);
    r = r && ClassDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation?
  private static boolean ApexClassOrInterface_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexClassOrInterface_0")) return false;
    Annotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Annotation? EnumAccessModifier Modifiers EnumDefinition
  public static boolean ApexEnum(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexEnum")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, APEX_ENUM, "<apex enum>");
    r = ApexEnum_0(b, l + 1);
    r = r && EnumAccessModifier(b, l + 1);
    r = r && Modifiers(b, l + 1);
    r = r && EnumDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation?
  private static boolean ApexEnum_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexEnum_0")) return false;
    Annotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // expression| ApexClass | ApexTrigger | ApexInterface | ApexScript | ApexEnum
  static boolean ApexFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexFile")) return false;
    boolean r;
    r = expression(b, l + 1, -1);
    if (!r) r = ApexClass(b, l + 1);
    if (!r) r = consumeToken(b, APEXTRIGGER);
    if (!r) r = ApexInterface(b, l + 1);
    if (!r) r = consumeToken(b, APEXSCRIPT);
    if (!r) r = ApexEnum(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Annotation? InterfaceAccessModifier Modifiers InterfaceDefinition
  public static boolean ApexInterface(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexInterface")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, APEX_INTERFACE, "<apex interface>");
    r = ApexInterface_0(b, l + 1);
    r = r && InterfaceAccessModifier(b, l + 1);
    r = r && Modifiers(b, l + 1);
    r = r && InterfaceDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation?
  private static boolean ApexInterface_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApexInterface_0")) return false;
    Annotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FieldDefinition | expression (QUESTION_MARK? DOT expression) SEMICOLON
  public static boolean AssignmentStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_STATEMENT, "<assignment statement>");
    r = FieldDefinition(b, l + 1);
    if (!r) r = AssignmentStatement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // expression (QUESTION_MARK? DOT expression) SEMICOLON
  private static boolean AssignmentStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && AssignmentStatement_1_1(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK? DOT expression
  private static boolean AssignmentStatement_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AssignmentStatement_1_1_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK?
  private static boolean AssignmentStatement_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement_1_1_0")) return false;
    consumeToken(b, QUESTION_MARK);
    return true;
  }

  /* ********************************************************** */
  // CATCH_KEYWORD LPAREN CatchCondition RPAREN CatchBlockBody
  public static boolean CatchBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchBlock")) return false;
    if (!nextTokenIs(b, CATCH_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CATCH_KEYWORD, LPAREN);
    r = r && CatchCondition(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && CatchBlockBody(b, l + 1);
    exit_section_(b, m, CATCH_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE CatchBody RBRACE
  public static boolean CatchBlockBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchBlockBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && CatchBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, CATCH_BLOCK_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean CatchBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, CATCH_BODY, "<catch body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CatchBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // TypeIdentifier VariableName
  public static boolean CatchCondition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchCondition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CATCH_CONDITION, "<catch condition>");
    r = TypeIdentifier(b, l + 1);
    r = r && VariableName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PUBLIC_KEYWORD | GLOBAL_KEYWORD | PRIVATE_KEYWORD | PROTECTED_KEYWORD
  public static boolean ClassAccessModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassAccessModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_ACCESS_MODIFIER, "<class access modifier>");
    r = consumeToken(b, PUBLIC_KEYWORD);
    if (!r) r = consumeToken(b, GLOBAL_KEYWORD);
    if (!r) r = consumeToken(b, PRIVATE_KEYWORD);
    if (!r) r = consumeToken(b, PROTECTED_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (ClassMember | ApexClassOrInterface| ClassDefinition)*
  public static boolean ClassBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, CLASS_BODY, "<class body>");
    while (true) {
      int c = current_position_(b);
      if (!ClassBody_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ClassBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // ClassMember | ApexClassOrInterface| ClassDefinition
  private static boolean ClassBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassBody_0")) return false;
    boolean r;
    r = ClassMember(b, l + 1);
    if (!r) r = ApexClassOrInterface(b, l + 1);
    if (!r) r = ClassDefinition(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // CLASS_KEYWORD TypeIdentifier Inheritance LBRACE ClassBody RBRACE
  public static boolean ClassDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassDefinition")) return false;
    if (!nextTokenIs(b, CLASS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLASS_KEYWORD);
    r = r && TypeIdentifier(b, l + 1);
    r = r && Inheritance(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && ClassBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, CLASS_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // MemberModifier (FieldDefinition | MethodDefinition | ConstructorDefinition)|ApexClass|ApexEnum|ApexInterface
  public static boolean ClassMember(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassMember")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_MEMBER, "<class member>");
    r = ClassMember_0(b, l + 1);
    if (!r) r = ApexClass(b, l + 1);
    if (!r) r = ApexEnum(b, l + 1);
    if (!r) r = ApexInterface(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // MemberModifier (FieldDefinition | MethodDefinition | ConstructorDefinition)
  private static boolean ClassMember_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassMember_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MemberModifier(b, l + 1);
    r = r && ClassMember_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FieldDefinition | MethodDefinition | ConstructorDefinition
  private static boolean ClassMember_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassMember_0_1")) return false;
    boolean r;
    r = FieldDefinition(b, l + 1);
    if (!r) r = MethodDefinition(b, l + 1);
    if (!r) r = ConstructorDefinition(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | GET_KEYWORD | SET_KEYWORD | THIS_KEYWORD | SUPER_KEYWORD
  static boolean CombinedIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CombinedIdentifier")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, GET_KEYWORD);
    if (!r) r = consumeToken(b, SET_KEYWORD);
    if (!r) r = consumeToken(b, THIS_KEYWORD);
    if (!r) r = consumeToken(b, SUPER_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // TraditionalForLoopCondition | IterationForLoop
  public static boolean ConditionTrail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConditionTrail")) return false;
    if (!nextTokenIs(b, "<condition trail>", COLON, SEMICOLON)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONDITION_TRAIL, "<condition trail>");
    r = TraditionalForLoopCondition(b, l + 1);
    if (!r) r = IterationForLoop(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MethodNameWithParameters MethodBlock
  public static boolean ConstructorDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstructorDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONSTRUCTOR_DEFINITION, "<constructor definition>");
    r = MethodNameWithParameters(b, l + 1);
    r = r && MethodBlock(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IfStatement | SwitchStatement
  public static boolean DecisionStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecisionStatement")) return false;
    if (!nextTokenIs(b, "<decision statement>", IF_KEYWORD, SWITCH_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECISION_STATEMENT, "<decision statement>");
    r = IfStatement(b, l + 1);
    if (!r) r = SwitchStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DELETE_KEYWORD (argument_group)? expression SEMICOLON
  public static boolean DeleteStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DeleteStatement")) return false;
    if (!nextTokenIs(b, DELETE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DELETE_KEYWORD);
    r = r && DeleteStatement_1(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, DELETE_STATEMENT, r);
    return r;
  }

  // (argument_group)?
  private static boolean DeleteStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DeleteStatement_1")) return false;
    DeleteStatement_1_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean DeleteStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DeleteStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // InsertStatement | UpdateStatement | DeleteStatement | MergeStatement | UpsertStatement
  public static boolean DmlStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DmlStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DML_STATEMENT, "<dml statement>");
    r = InsertStatement(b, l + 1);
    if (!r) r = UpdateStatement(b, l + 1);
    if (!r) r = DeleteStatement(b, l + 1);
    if (!r) r = MergeStatement(b, l + 1);
    if (!r) r = UpsertStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ForBlock
  public static boolean DoBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DoBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForBlock(b, l + 1);
    exit_section_(b, m, DO_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // DO_KEYWORD DoBlock WHILE_KEYWORD LPAREN expression RPAREN SEMICOLON
  public static boolean DoWhileLoop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DoWhileLoop")) return false;
    if (!nextTokenIs(b, DO_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DO_KEYWORD);
    r = r && DoBlock(b, l + 1);
    r = r && consumeTokens(b, 0, WHILE_KEYWORD, LPAREN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeTokens(b, 0, RPAREN, SEMICOLON);
    exit_section_(b, m, DO_WHILE_LOOP, r);
    return r;
  }

  /* ********************************************************** */
  // ELSE_KEYWORD IfBlock
  public static boolean ElseBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseBlock")) return false;
    if (!nextTokenIs(b, ELSE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE_KEYWORD);
    r = r && IfBlock(b, l + 1);
    exit_section_(b, m, ELSE_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // FOR_KEYWORD LPAREN (ForLoopConditionDeclared|ForLoopConditionReference) RPAREN (SEMICOLON | ForBlock)
  public static boolean EnhancedForLoop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnhancedForLoop")) return false;
    if (!nextTokenIs(b, FOR_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOR_KEYWORD, LPAREN);
    r = r && EnhancedForLoop_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && EnhancedForLoop_4(b, l + 1);
    exit_section_(b, m, ENHANCED_FOR_LOOP, r);
    return r;
  }

  // ForLoopConditionDeclared|ForLoopConditionReference
  private static boolean EnhancedForLoop_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnhancedForLoop_2")) return false;
    boolean r;
    r = ForLoopConditionDeclared(b, l + 1);
    if (!r) r = ForLoopConditionReference(b, l + 1);
    return r;
  }

  // SEMICOLON | ForBlock
  private static boolean EnhancedForLoop_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnhancedForLoop_4")) return false;
    boolean r;
    r = consumeToken(b, SEMICOLON);
    if (!r) r = ForBlock(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ClassAccessModifier
  public static boolean EnumAccessModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumAccessModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_ACCESS_MODIFIER, "<enum access modifier>");
    r = ClassAccessModifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (expression (COMMA expression)*)?
  public static boolean EnumBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, ENUM_BODY, "<enum body>");
    EnumBody_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // expression (COMMA expression)*
  private static boolean EnumBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumBody_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && EnumBody_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean EnumBody_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumBody_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EnumBody_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "EnumBody_0_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean EnumBody_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumBody_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ENUM_KEYWORD TypeIdentifier LBRACE EnumBody RBRACE
  public static boolean EnumDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumDefinition")) return false;
    if (!nextTokenIs(b, ENUM_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ENUM_KEYWORD);
    r = r && TypeIdentifier(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && EnumBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ENUM_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // TypeIdentifier VariableName (SEMICOLON | (LBRACE ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)) ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)))?  RBRACE)
  public static boolean FieldDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_DEFINITION, "<field definition>");
    r = TypeIdentifier(b, l + 1);
    r = r && VariableName(b, l + 1);
    r = r && FieldDefinition_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SEMICOLON | (LBRACE ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)) ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)))?  RBRACE
  private static boolean FieldDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    if (!r) r = FieldDefinition_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACE ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)) ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)))?  RBRACE
  private static boolean FieldDefinition_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldDefinition_2_1_0(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACE ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)) ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)))?
  private static boolean FieldDefinition_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0")) return false;
    FieldDefinition_2_1_0_0(b, l + 1);
    return true;
  }

  // LBRACE ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)) ((GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON))
  private static boolean FieldDefinition_2_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && FieldDefinition_2_1_0_0_1(b, l + 1);
    r = r && FieldDefinition_2_1_0_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)
  private static boolean FieldDefinition_2_1_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldDefinition_2_1_0_0_1_0(b, l + 1);
    r = r && FieldDefinition_2_1_0_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GET_KEYWORD|SET_KEYWORD
  private static boolean FieldDefinition_2_1_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, GET_KEYWORD);
    if (!r) r = consumeToken(b, SET_KEYWORD);
    return r;
  }

  // MethodBlock | SEMICOLON
  private static boolean FieldDefinition_2_1_0_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0_1_1")) return false;
    boolean r;
    r = MethodBlock(b, l + 1);
    if (!r) r = consumeToken(b, SEMICOLON);
    return r;
  }

  // (GET_KEYWORD|SET_KEYWORD) (MethodBlock | SEMICOLON)
  private static boolean FieldDefinition_2_1_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldDefinition_2_1_0_0_2_0(b, l + 1);
    r = r && FieldDefinition_2_1_0_0_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GET_KEYWORD|SET_KEYWORD
  private static boolean FieldDefinition_2_1_0_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0_2_0")) return false;
    boolean r;
    r = consumeToken(b, GET_KEYWORD);
    if (!r) r = consumeToken(b, SET_KEYWORD);
    return r;
  }

  // MethodBlock | SEMICOLON
  private static boolean FieldDefinition_2_1_0_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefinition_2_1_0_0_2_1")) return false;
    boolean r;
    r = MethodBlock(b, l + 1);
    if (!r) r = consumeToken(b, SEMICOLON);
    return r;
  }

  /* ********************************************************** */
  // FINALLY_KEYWORD LBRACE FinallyBody RBRACE
  public static boolean FinallyBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FinallyBlock")) return false;
    if (!nextTokenIs(b, FINALLY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FINALLY_KEYWORD, LBRACE);
    r = r && FinallyBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, FINALLY_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean FinallyBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FinallyBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, FINALLY_BODY, "<finally body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FinallyBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LBRACE ForBody? RBRACE
  public static boolean ForBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && ForBlock_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, FOR_BLOCK, r);
    return r;
  }

  // ForBody?
  private static boolean ForBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBlock_1")) return false;
    ForBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean ForBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, FOR_BODY, "<for body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ForBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // EnhancedForLoop
  public static boolean ForLoop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForLoop")) return false;
    if (!nextTokenIs(b, FOR_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnhancedForLoop(b, l + 1);
    exit_section_(b, m, FOR_LOOP, r);
    return r;
  }

  /* ********************************************************** */
  // TypeIdentifier VariableName ConditionTrail
  public static boolean ForLoopConditionDeclared(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForLoopConditionDeclared")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_LOOP_CONDITION_DECLARED, "<for loop condition declared>");
    r = TypeIdentifier(b, l + 1);
    r = r && VariableName(b, l + 1);
    r = r && ConditionTrail(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VariableName? ConditionTrail
  public static boolean ForLoopConditionReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForLoopConditionReference")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_LOOP_CONDITION_REFERENCE, "<for loop condition reference>");
    r = ForLoopConditionReference_0(b, l + 1);
    r = r && ConditionTrail(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // VariableName?
  private static boolean ForLoopConditionReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForLoopConditionReference_0")) return false;
    VariableName(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LESS TypeIdentifier (COMMA TypeIdentifier)* GREATER
  public static boolean GenericType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GenericType")) return false;
    if (!nextTokenIs(b, LESS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LESS);
    r = r && TypeIdentifier(b, l + 1);
    r = r && GenericType_2(b, l + 1);
    r = r && consumeToken(b, GREATER);
    exit_section_(b, m, GENERIC_TYPE, r);
    return r;
  }

  // (COMMA TypeIdentifier)*
  private static boolean GenericType_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GenericType_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!GenericType_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "GenericType_2", c)) break;
    }
    return true;
  }

  // COMMA TypeIdentifier
  private static boolean GenericType_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GenericType_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && TypeIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE IfBody RBRACE
  public static boolean IfBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && IfBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, IF_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean IfBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, IF_BODY, "<if body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IfBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // ELSE_KEYWORD IfStatement
  public static boolean IfElseBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfElseBlock")) return false;
    if (!nextTokenIs(b, ELSE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE_KEYWORD);
    r = r && IfStatement(b, l + 1);
    exit_section_(b, m, IF_ELSE_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // IF_KEYWORD LPAREN expression RPAREN (SEMICOLON|IfBlock IfElseBlock? ElseBlock?)
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF_KEYWORD, LPAREN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RPAREN);
    r = r && IfStatement_4(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // SEMICOLON|IfBlock IfElseBlock? ElseBlock?
  private static boolean IfStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    if (!r) r = IfStatement_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IfBlock IfElseBlock? ElseBlock?
  private static boolean IfStatement_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfBlock(b, l + 1);
    r = r && IfStatement_4_1_1(b, l + 1);
    r = r && IfStatement_4_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IfElseBlock?
  private static boolean IfStatement_4_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4_1_1")) return false;
    IfElseBlock(b, l + 1);
    return true;
  }

  // ElseBlock?
  private static boolean IfStatement_4_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4_1_2")) return false;
    ElseBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IMPLEMENTS_KEYWORD InterfaceIdentifier (COMMA InterfaceIdentifier)*
  public static boolean ImplementsClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsClause")) return false;
    if (!nextTokenIs(b, IMPLEMENTS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPLEMENTS_KEYWORD);
    r = r && InterfaceIdentifier(b, l + 1);
    r = r && ImplementsClause_2(b, l + 1);
    exit_section_(b, m, IMPLEMENTS_CLAUSE, r);
    return r;
  }

  // (COMMA InterfaceIdentifier)*
  private static boolean ImplementsClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsClause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImplementsClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImplementsClause_2", c)) break;
    }
    return true;
  }

  // COMMA InterfaceIdentifier
  private static boolean ImplementsClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplementsClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && InterfaceIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ((EXTENDS_KEYWORD) ParentClass)? ImplementsClause?
  public static boolean Inheritance(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Inheritance")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INHERITANCE, "<inheritance>");
    r = Inheritance_0(b, l + 1);
    r = r && Inheritance_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((EXTENDS_KEYWORD) ParentClass)?
  private static boolean Inheritance_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Inheritance_0")) return false;
    Inheritance_0_0(b, l + 1);
    return true;
  }

  // (EXTENDS_KEYWORD) ParentClass
  private static boolean Inheritance_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Inheritance_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXTENDS_KEYWORD);
    r = r && ParentClass(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ImplementsClause?
  private static boolean Inheritance_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Inheritance_1")) return false;
    ImplementsClause(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // INSERT_KEYWORD (argument_group)? expression SEMICOLON
  public static boolean InsertStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertStatement")) return false;
    if (!nextTokenIs(b, INSERT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSERT_KEYWORD);
    r = r && InsertStatement_1(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, INSERT_STATEMENT, r);
    return r;
  }

  // (argument_group)?
  private static boolean InsertStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertStatement_1")) return false;
    InsertStatement_1_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean InsertStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ClassAccessModifier
  public static boolean InterfaceAccessModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceAccessModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_ACCESS_MODIFIER, "<interface access modifier>");
    r = ClassAccessModifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (ClassMember | ApexClassOrInterface| ClassDefinition |InterfaceSignature)*
  public static boolean InterfaceBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_BODY, "<interface body>");
    while (true) {
      int c = current_position_(b);
      if (!InterfaceBody_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InterfaceBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // ClassMember | ApexClassOrInterface| ClassDefinition |InterfaceSignature
  private static boolean InterfaceBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceBody_0")) return false;
    boolean r;
    r = ClassMember(b, l + 1);
    if (!r) r = ApexClassOrInterface(b, l + 1);
    if (!r) r = ClassDefinition(b, l + 1);
    if (!r) r = InterfaceSignature(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // INTERFACE_KEYWORD TypeIdentifier Inheritance LBRACE InterfaceBody RBRACE
  public static boolean InterfaceDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceDefinition")) return false;
    if (!nextTokenIs(b, INTERFACE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTERFACE_KEYWORD);
    r = r && TypeIdentifier(b, l + 1);
    r = r && Inheritance(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && InterfaceBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, INTERFACE_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // TypeIdentifier
  public static boolean InterfaceIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_IDENTIFIER, "<interface identifier>");
    r = TypeIdentifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // InterfaceAccessModifier? VIRTUAL_KEYWORD? STATIC_KEYWORD? (FINAL_KEYWORD | OVERRIDE_KEYWORD)? TypeIdentifier CombinedIdentifier parameter_group SEMICOLON
  public static boolean InterfaceSignature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceSignature")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_SIGNATURE, "<interface signature>");
    r = InterfaceSignature_0(b, l + 1);
    r = r && InterfaceSignature_1(b, l + 1);
    r = r && InterfaceSignature_2(b, l + 1);
    r = r && InterfaceSignature_3(b, l + 1);
    r = r && TypeIdentifier(b, l + 1);
    r = r && CombinedIdentifier(b, l + 1);
    r = r && parameter_group(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // InterfaceAccessModifier?
  private static boolean InterfaceSignature_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceSignature_0")) return false;
    InterfaceAccessModifier(b, l + 1);
    return true;
  }

  // VIRTUAL_KEYWORD?
  private static boolean InterfaceSignature_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceSignature_1")) return false;
    consumeToken(b, VIRTUAL_KEYWORD);
    return true;
  }

  // STATIC_KEYWORD?
  private static boolean InterfaceSignature_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceSignature_2")) return false;
    consumeToken(b, STATIC_KEYWORD);
    return true;
  }

  // (FINAL_KEYWORD | OVERRIDE_KEYWORD)?
  private static boolean InterfaceSignature_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceSignature_3")) return false;
    InterfaceSignature_3_0(b, l + 1);
    return true;
  }

  // FINAL_KEYWORD | OVERRIDE_KEYWORD
  private static boolean InterfaceSignature_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterfaceSignature_3_0")) return false;
    boolean r;
    r = consumeToken(b, FINAL_KEYWORD);
    if (!r) r = consumeToken(b, OVERRIDE_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // COLON expression
  public static boolean IterationForLoop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IterationForLoop")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, ITERATION_FOR_LOOP, r);
    return r;
  }

  /* ********************************************************** */
  // BREAK_KEYWORD SEMICOLON | CONTINUE_KEYWORD SEMICOLON
  public static boolean LoopControlStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopControlStatement")) return false;
    if (!nextTokenIs(b, "<loop control statement>", BREAK_KEYWORD, CONTINUE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOOP_CONTROL_STATEMENT, "<loop control statement>");
    r = parseTokens(b, 0, BREAK_KEYWORD, SEMICOLON);
    if (!r) r = parseTokens(b, 0, CONTINUE_KEYWORD, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ForLoop | WhileLoop | DoWhileLoop
  public static boolean LoopStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOOP_STATEMENT, "<loop statement>");
    r = ForLoop(b, l + 1);
    if (!r) r = WhileLoop(b, l + 1);
    if (!r) r = DoWhileLoop(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PUBLIC_KEYWORD | PRIVATE_KEYWORD | PROTECTED_KEYWORD | GLOBAL_KEYWORD
  public static boolean MemberAccessModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberAccessModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MEMBER_ACCESS_MODIFIER, "<member access modifier>");
    r = consumeToken(b, PUBLIC_KEYWORD);
    if (!r) r = consumeToken(b, PRIVATE_KEYWORD);
    if (!r) r = consumeToken(b, PROTECTED_KEYWORD);
    if (!r) r = consumeToken(b, GLOBAL_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Annotation? MemberAccessModifier? VIRTUAL_KEYWORD? STATIC_KEYWORD? (FINAL_KEYWORD | OVERRIDE_KEYWORD)?
  public static boolean MemberModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MEMBER_MODIFIER, "<member modifier>");
    r = MemberModifier_0(b, l + 1);
    r = r && MemberModifier_1(b, l + 1);
    r = r && MemberModifier_2(b, l + 1);
    r = r && MemberModifier_3(b, l + 1);
    r = r && MemberModifier_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation?
  private static boolean MemberModifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier_0")) return false;
    Annotation(b, l + 1);
    return true;
  }

  // MemberAccessModifier?
  private static boolean MemberModifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier_1")) return false;
    MemberAccessModifier(b, l + 1);
    return true;
  }

  // VIRTUAL_KEYWORD?
  private static boolean MemberModifier_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier_2")) return false;
    consumeToken(b, VIRTUAL_KEYWORD);
    return true;
  }

  // STATIC_KEYWORD?
  private static boolean MemberModifier_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier_3")) return false;
    consumeToken(b, STATIC_KEYWORD);
    return true;
  }

  // (FINAL_KEYWORD | OVERRIDE_KEYWORD)?
  private static boolean MemberModifier_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier_4")) return false;
    MemberModifier_4_0(b, l + 1);
    return true;
  }

  // FINAL_KEYWORD | OVERRIDE_KEYWORD
  private static boolean MemberModifier_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MemberModifier_4_0")) return false;
    boolean r;
    r = consumeToken(b, FINAL_KEYWORD);
    if (!r) r = consumeToken(b, OVERRIDE_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // MERGE_KEYWORD (argument_group)? expression SEMICOLON
  public static boolean MergeStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MergeStatement")) return false;
    if (!nextTokenIs(b, MERGE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MERGE_KEYWORD);
    r = r && MergeStatement_1(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, MERGE_STATEMENT, r);
    return r;
  }

  // (argument_group)?
  private static boolean MergeStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MergeStatement_1")) return false;
    MergeStatement_1_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean MergeStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MergeStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE MethodBody RBRACE
  public static boolean MethodBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && MethodBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, METHOD_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean MethodBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, METHOD_BODY, "<method body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MethodBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // expression (QUESTION_MARK? DOT MethodNameWithArgument)? SEMICOLON
  public static boolean MethodCallStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodCallStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_CALL_STATEMENT, "<method call statement>");
    r = expression(b, l + 1, -1);
    r = r && MethodCallStatement_1(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (QUESTION_MARK? DOT MethodNameWithArgument)?
  private static boolean MethodCallStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodCallStatement_1")) return false;
    MethodCallStatement_1_0(b, l + 1);
    return true;
  }

  // QUESTION_MARK? DOT MethodNameWithArgument
  private static boolean MethodCallStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodCallStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MethodCallStatement_1_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && MethodNameWithArgument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK?
  private static boolean MethodCallStatement_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodCallStatement_1_0_0")) return false;
    consumeToken(b, QUESTION_MARK);
    return true;
  }

  /* ********************************************************** */
  // TypeIdentifier MethodNameWithParameters MethodBlock
  public static boolean MethodDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DEFINITION, "<method definition>");
    r = TypeIdentifier(b, l + 1);
    r = r && MethodNameWithParameters(b, l + 1);
    r = r && MethodBlock(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CombinedIdentifier argument_group
  public static boolean MethodNameWithArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodNameWithArgument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_NAME_WITH_ARGUMENT, "<method name with argument>");
    r = CombinedIdentifier(b, l + 1);
    r = r && argument_group(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CombinedIdentifier parameter_group
  public static boolean MethodNameWithParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodNameWithParameters")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_NAME_WITH_PARAMETERS, "<method name with parameters>");
    r = CombinedIdentifier(b, l + 1);
    r = r && parameter_group(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VIRTUAL_KEYWORD | ABSTRACT_KEYWORD | SharingClause
  public static boolean Modifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Modifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODIFIER, "<modifier>");
    r = consumeToken(b, VIRTUAL_KEYWORD);
    if (!r) r = consumeToken(b, ABSTRACT_KEYWORD);
    if (!r) r = SharingClause(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Modifier*
  public static boolean Modifiers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Modifiers")) return false;
    Marker m = enter_section_(b, l, _NONE_, MODIFIERS, "<modifiers>");
    while (true) {
      int c = current_position_(b);
      if (!Modifier(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Modifiers", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // TypeIdentifier
  public static boolean ParentClass(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParentClass")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARENT_CLASS, "<parent class>");
    r = TypeIdentifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'Integer' | 'String' | 'Boolean' | 'Double' | 'Long' | 'Date' | 'Datetime' | 'Id' | 'Object' | 'Blob' | 'Decimal' | 'Time' | VOID_KEYWORD
  public static boolean PrimitiveType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimitiveType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMITIVE_TYPE, "<primitive type>");
    r = consumeToken(b, "Integer");
    if (!r) r = consumeToken(b, "String");
    if (!r) r = consumeToken(b, "Boolean");
    if (!r) r = consumeToken(b, "Double");
    if (!r) r = consumeToken(b, "Long");
    if (!r) r = consumeToken(b, "Date");
    if (!r) r = consumeToken(b, "Datetime");
    if (!r) r = consumeToken(b, "Id");
    if (!r) r = consumeToken(b, "Object");
    if (!r) r = consumeToken(b, "Blob");
    if (!r) r = consumeToken(b, "Decimal");
    if (!r) r = consumeToken(b, "Time");
    if (!r) r = consumeToken(b, VOID_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CombinedIdentifier (DOT CombinedIdentifier)?
  public static boolean QualifiedIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QualifiedIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUALIFIED_IDENTIFIER, "<qualified identifier>");
    r = CombinedIdentifier(b, l + 1);
    r = r && QualifiedIdentifier_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (DOT CombinedIdentifier)?
  private static boolean QualifiedIdentifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QualifiedIdentifier_1")) return false;
    QualifiedIdentifier_1_0(b, l + 1);
    return true;
  }

  // DOT CombinedIdentifier
  private static boolean QualifiedIdentifier_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QualifiedIdentifier_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && CombinedIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // RETURN_KEYWORD expression? SEMICOLON
  public static boolean ReturnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement")) return false;
    if (!nextTokenIs(b, RETURN_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN_KEYWORD);
    r = r && ReturnStatement_1(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, RETURN_STATEMENT, r);
    return r;
  }

  // expression?
  private static boolean ReturnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // (WITH_KEYWORD | WITHOUT_KEYWORD | INHERITED_KEYWORD) SHARING_KEYWORD
  public static boolean SharingClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SharingClause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHARING_CLAUSE, "<sharing clause>");
    r = SharingClause_0(b, l + 1);
    r = r && consumeToken(b, SHARING_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WITH_KEYWORD | WITHOUT_KEYWORD | INHERITED_KEYWORD
  private static boolean SharingClause_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SharingClause_0")) return false;
    boolean r;
    r = consumeToken(b, WITH_KEYWORD);
    if (!r) r = consumeToken(b, WITHOUT_KEYWORD);
    if (!r) r = consumeToken(b, INHERITED_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // DecisionStatement
  //                 | LoopStatement
  //                 | ReturnStatement
  //                 | ThrowStatement
  //                 | TryCatchStatement
  //                 | DmlStatement
  //                 | AssignmentStatement
  //                 | LoopControlStatement
  //                 | MethodCallStatement
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = DecisionStatement(b, l + 1);
    if (!r) r = LoopStatement(b, l + 1);
    if (!r) r = ReturnStatement(b, l + 1);
    if (!r) r = ThrowStatement(b, l + 1);
    if (!r) r = TryCatchStatement(b, l + 1);
    if (!r) r = DmlStatement(b, l + 1);
    if (!r) r = AssignmentStatement(b, l + 1);
    if (!r) r = LoopControlStatement(b, l + 1);
    if (!r) r = MethodCallStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBRACE SwitchBlockBody RBRACE
  public static boolean SwitchBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && SwitchBlockBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SWITCH_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // (WhenStatement | WhenElseStatement)*
  public static boolean SwitchBlockBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchBlockBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_BLOCK_BODY, "<switch block body>");
    while (true) {
      int c = current_position_(b);
      if (!SwitchBlockBody_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchBlockBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // WhenStatement | WhenElseStatement
  private static boolean SwitchBlockBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchBlockBody_0")) return false;
    boolean r;
    r = WhenStatement(b, l + 1);
    if (!r) r = WhenElseStatement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean SwitchBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_BODY, "<switch body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // SWITCH_KEYWORD ON_KEYWORD AllowedSwitchType SwitchBlock
  public static boolean SwitchStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement")) return false;
    if (!nextTokenIs(b, SWITCH_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SWITCH_KEYWORD, ON_KEYWORD);
    r = r && AllowedSwitchType(b, l + 1);
    r = r && SwitchBlock(b, l + 1);
    exit_section_(b, m, SWITCH_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE SwitchBody RBRACE
  public static boolean SwitchWhenBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchWhenBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && SwitchBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SWITCH_WHEN_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  public static boolean SwitchWhenValueList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchWhenValueList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_WHEN_VALUE_LIST, "<switch when value list>");
    r = expression(b, l + 1, -1);
    r = r && SwitchWhenValueList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA expression)*
  private static boolean SwitchWhenValueList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchWhenValueList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SwitchWhenValueList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchWhenValueList_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean SwitchWhenValueList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchWhenValueList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // THROW_KEYWORD expression SEMICOLON
  public static boolean ThrowStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ThrowStatement")) return false;
    if (!nextTokenIs(b, THROW_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THROW_KEYWORD);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, THROW_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // SEMICOLON expression? SEMICOLON expression?
  public static boolean TraditionalForLoopCondition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TraditionalForLoopCondition")) return false;
    if (!nextTokenIs(b, SEMICOLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    r = r && TraditionalForLoopCondition_1(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    r = r && TraditionalForLoopCondition_3(b, l + 1);
    exit_section_(b, m, TRADITIONAL_FOR_LOOP_CONDITION, r);
    return r;
  }

  // expression?
  private static boolean TraditionalForLoopCondition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TraditionalForLoopCondition_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // expression?
  private static boolean TraditionalForLoopCondition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TraditionalForLoopCondition_3")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE TryBody RBRACE
  public static boolean TryBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && TryBody(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, TRY_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean TryBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, TRY_BODY, "<try body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TryBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // TRY_KEYWORD TryBlock CatchBlock* FinallyBlock?
  public static boolean TryCatchStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryCatchStatement")) return false;
    if (!nextTokenIs(b, TRY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRY_KEYWORD);
    r = r && TryBlock(b, l + 1);
    r = r && TryCatchStatement_2(b, l + 1);
    r = r && TryCatchStatement_3(b, l + 1);
    exit_section_(b, m, TRY_CATCH_STATEMENT, r);
    return r;
  }

  // CatchBlock*
  private static boolean TryCatchStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryCatchStatement_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CatchBlock(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TryCatchStatement_2", c)) break;
    }
    return true;
  }

  // FinallyBlock?
  private static boolean TryCatchStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryCatchStatement_3")) return false;
    FinallyBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (PrimitiveType | QualifiedIdentifier) (GenericType | ARRAY_BRACKET)*
  public static boolean TypeIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_IDENTIFIER, "<type identifier>");
    r = TypeIdentifier_0(b, l + 1);
    r = r && TypeIdentifier_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PrimitiveType | QualifiedIdentifier
  private static boolean TypeIdentifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeIdentifier_0")) return false;
    boolean r;
    r = PrimitiveType(b, l + 1);
    if (!r) r = QualifiedIdentifier(b, l + 1);
    return r;
  }

  // (GenericType | ARRAY_BRACKET)*
  private static boolean TypeIdentifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeIdentifier_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TypeIdentifier_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeIdentifier_1", c)) break;
    }
    return true;
  }

  // GenericType | ARRAY_BRACKET
  private static boolean TypeIdentifier_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeIdentifier_1_0")) return false;
    boolean r;
    r = GenericType(b, l + 1);
    if (!r) r = consumeToken(b, ARRAY_BRACKET);
    return r;
  }

  /* ********************************************************** */
  // UPDATE_KEYWORD (argument_group)? expression SEMICOLON
  public static boolean UpdateStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UpdateStatement")) return false;
    if (!nextTokenIs(b, UPDATE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, UPDATE_KEYWORD);
    r = r && UpdateStatement_1(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, UPDATE_STATEMENT, r);
    return r;
  }

  // (argument_group)?
  private static boolean UpdateStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UpdateStatement_1")) return false;
    UpdateStatement_1_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean UpdateStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UpdateStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // UPSERT_KEYWORD (argument_group)? expression SEMICOLON
  public static boolean UpsertStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UpsertStatement")) return false;
    if (!nextTokenIs(b, UPSERT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, UPSERT_KEYWORD);
    r = r && UpsertStatement_1(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, UPSERT_STATEMENT, r);
    return r;
  }

  // (argument_group)?
  private static boolean UpsertStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UpsertStatement_1")) return false;
    UpsertStatement_1_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean UpsertStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UpsertStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CombinedIdentifier (ASSIGN expression)? (COMMA VariableName)?
  public static boolean VariableName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, VARIABLE_NAME, "<variable name>");
    r = CombinedIdentifier(b, l + 1);
    r = r && VariableName_1(b, l + 1);
    r = r && VariableName_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (ASSIGN expression)?
  private static boolean VariableName_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableName_1")) return false;
    VariableName_1_0(b, l + 1);
    return true;
  }

  // ASSIGN expression
  private static boolean VariableName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA VariableName)?
  private static boolean VariableName_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableName_2")) return false;
    VariableName_2_0(b, l + 1);
    return true;
  }

  // COMMA VariableName
  private static boolean VariableName_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableName_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && VariableName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // WHEN_KEYWORD ELSE_KEYWORD SwitchWhenBlock
  public static boolean WhenElseStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhenElseStatement")) return false;
    if (!nextTokenIs(b, WHEN_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, WHEN_KEYWORD, ELSE_KEYWORD);
    r = r && SwitchWhenBlock(b, l + 1);
    exit_section_(b, m, WHEN_ELSE_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // WHEN_KEYWORD SwitchWhenValueList SwitchWhenBlock
  public static boolean WhenStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhenStatement")) return false;
    if (!nextTokenIs(b, WHEN_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHEN_KEYWORD);
    r = r && SwitchWhenValueList(b, l + 1);
    r = r && SwitchWhenBlock(b, l + 1);
    exit_section_(b, m, WHEN_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE WhileBody? RBRACE
  public static boolean WhileBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && WhileBlock_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, WHILE_BLOCK, r);
    return r;
  }

  // WhileBody?
  private static boolean WhileBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileBlock_1")) return false;
    WhileBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Statement*
  public static boolean WhileBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, WHILE_BODY, "<while body>");
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "WhileBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // expression
  public static boolean WhileCondition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileCondition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, WHILE_CONDITION, "<while condition>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // WHILE_KEYWORD LPAREN WhileCondition RPAREN (SEMICOLON|WhileBlock)
  public static boolean WhileLoop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileLoop")) return false;
    if (!nextTokenIs(b, WHILE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, WHILE_KEYWORD, LPAREN);
    r = r && WhileCondition(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && WhileLoop_4(b, l + 1);
    exit_section_(b, m, WHILE_LOOP, r);
    return r;
  }

  // SEMICOLON|WhileBlock
  private static boolean WhileLoop_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileLoop_4")) return false;
    boolean r;
    r = consumeToken(b, SEMICOLON);
    if (!r) r = WhileBlock(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LPAREN value_expression_list? RPAREN
  static boolean argument_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_group")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && argument_group_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // value_expression_list?
  private static boolean argument_group_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_group_1")) return false;
    value_expression_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // soql_sosl_keyword
  static boolean custom_soql_sosl_expression(PsiBuilder b, int l) {
    return soql_sosl_keyword(b, l + 1);
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  static boolean field_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_expression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && field_expression_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean field_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_expression_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!field_expression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "field_expression_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean field_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (expression EQUAL GREATER?)? expression
  static boolean initialization_unit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = initialization_unit_0(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (expression EQUAL GREATER?)?
  private static boolean initialization_unit_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit_0")) return false;
    initialization_unit_0_0(b, l + 1);
    return true;
  }

  // expression EQUAL GREATER?
  private static boolean initialization_unit_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && consumeToken(b, EQUAL);
    r = r && initialization_unit_0_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GREATER?
  private static boolean initialization_unit_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit_0_0_2")) return false;
    consumeToken(b, GREATER);
    return true;
  }

  /* ********************************************************** */
  // initialization_unit (COMMA initialization_unit)*
  static boolean initialization_unit_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit_list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = initialization_unit(b, l + 1);
    r = r && initialization_unit_list_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA initialization_unit)*
  private static boolean initialization_unit_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!initialization_unit_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "initialization_unit_list_1", c)) break;
    }
    return true;
  }

  // COMMA initialization_unit
  private static boolean initialization_unit_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialization_unit_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && initialization_unit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE initialization_unit_list RBRACE
  static boolean initializer_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initializer_expr")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && initialization_unit_list(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER | STRING | TRUE_LITERAL | FALSE_LITERAL
  static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, TRUE_LITERAL);
    if (!r) r = consumeToken(b, FALSE_LITERAL);
    return r;
  }

  /* ********************************************************** */
  // (literal|soql_expr|SOSL) (QUESTION_MARK? DOT VariableName)? (LBRACKET expression RBRACKET)? (LPAREN expression? RPAREN)?
  static boolean literal_with_attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = literal_with_attribute_0(b, l + 1);
    r = r && literal_with_attribute_1(b, l + 1);
    r = r && literal_with_attribute_2(b, l + 1);
    r = r && literal_with_attribute_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // literal|soql_expr|SOSL
  private static boolean literal_with_attribute_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_0")) return false;
    boolean r;
    r = literal(b, l + 1);
    if (!r) r = soql_expr(b, l + 1);
    if (!r) r = consumeToken(b, SOSL);
    return r;
  }

  // (QUESTION_MARK? DOT VariableName)?
  private static boolean literal_with_attribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_1")) return false;
    literal_with_attribute_1_0(b, l + 1);
    return true;
  }

  // QUESTION_MARK? DOT VariableName
  private static boolean literal_with_attribute_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = literal_with_attribute_1_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && VariableName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK?
  private static boolean literal_with_attribute_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_1_0_0")) return false;
    consumeToken(b, QUESTION_MARK);
    return true;
  }

  // (LBRACKET expression RBRACKET)?
  private static boolean literal_with_attribute_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_2")) return false;
    literal_with_attribute_2_0(b, l + 1);
    return true;
  }

  // LBRACKET expression RBRACKET
  private static boolean literal_with_attribute_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LPAREN expression? RPAREN)?
  private static boolean literal_with_attribute_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_3")) return false;
    literal_with_attribute_3_0(b, l + 1);
    return true;
  }

  // LPAREN expression? RPAREN
  private static boolean literal_with_attribute_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && literal_with_attribute_3_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean literal_with_attribute_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_with_attribute_3_0_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // typedef (argument_group)? initializer_expr? (QUESTION_MARK? DOT variable_reference_expr)?
  static boolean object_creation_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr")) return false;
    if (!nextTokenIs(b, NEW_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = typedef(b, l + 1);
    r = r && object_creation_expr_1(b, l + 1);
    r = r && object_creation_expr_2(b, l + 1);
    r = r && object_creation_expr_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (argument_group)?
  private static boolean object_creation_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr_1")) return false;
    object_creation_expr_1_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean object_creation_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // initializer_expr?
  private static boolean object_creation_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr_2")) return false;
    initializer_expr(b, l + 1);
    return true;
  }

  // (QUESTION_MARK? DOT variable_reference_expr)?
  private static boolean object_creation_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr_3")) return false;
    object_creation_expr_3_0(b, l + 1);
    return true;
  }

  // QUESTION_MARK? DOT variable_reference_expr
  private static boolean object_creation_expr_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_creation_expr_3_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && variable_reference_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK?
  private static boolean object_creation_expr_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_creation_expr_3_0_0")) return false;
    consumeToken(b, QUESTION_MARK);
    return true;
  }

  /* ********************************************************** */
  // LPAREN parameter_list? RPAREN
  static boolean parameter_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_group")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && parameter_group_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // parameter_list?
  private static boolean parameter_group_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_group_1")) return false;
    parameter_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // TypeIdentifier CombinedIdentifier (COMMA TypeIdentifier CombinedIdentifier)*
  public static boolean parameter_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_LIST, "<parameter list>");
    r = TypeIdentifier(b, l + 1);
    r = r && CombinedIdentifier(b, l + 1);
    r = r && parameter_list_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA TypeIdentifier CombinedIdentifier)*
  private static boolean parameter_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameter_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameter_list_2", c)) break;
    }
    return true;
  }

  // COMMA TypeIdentifier CombinedIdentifier
  private static boolean parameter_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && TypeIdentifier(b, l + 1);
    r = r && CombinedIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN expression RPAREN
  static boolean paren_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paren_expr")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SELECT_KEYWORD soql_token_composition
  static boolean soql_base_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_base_expr")) return false;
    if (!nextTokenIs(b, SELECT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SELECT_KEYWORD);
    r = r && soql_token_composition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET soql_base_expr RBRACKET
  static boolean soql_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_expr")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && soql_base_expr(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // custom_soql_sosl_expression|(LPAREN soql_base_expr RPAREN) | field_expression
  static boolean soql_expression_unit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_expression_unit")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = custom_soql_sosl_expression(b, l + 1);
    if (!r) r = soql_expression_unit_1(b, l + 1);
    if (!r) r = field_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LPAREN soql_base_expr RPAREN
  private static boolean soql_expression_unit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_expression_unit_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && soql_base_expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FROM_KEYWORD
  //                                 | WHERE_KEYWORD
  //                                 | ORDER_BY_KEYWORD
  //                                 | LIMIT_KEYWORD
  //                                 | OFFSET_KEYWORD
  //                                 | GROUP_BY_KEYWORD
  //                                 | HAVING_KEYWORD
  //                                 | DISTINCT_KEYWORD
  //                                 | SOQL_FOR_KEYWORD
  //                                 | UPDATE_KEYWORD
  static boolean soql_sosl_keyword(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_sosl_keyword")) return false;
    boolean r;
    r = consumeToken(b, FROM_KEYWORD);
    if (!r) r = consumeToken(b, WHERE_KEYWORD);
    if (!r) r = consumeToken(b, ORDER_BY_KEYWORD);
    if (!r) r = consumeToken(b, LIMIT_KEYWORD);
    if (!r) r = consumeToken(b, OFFSET_KEYWORD);
    if (!r) r = consumeToken(b, GROUP_BY_KEYWORD);
    if (!r) r = consumeToken(b, HAVING_KEYWORD);
    if (!r) r = consumeToken(b, DISTINCT_KEYWORD);
    if (!r) r = consumeToken(b, SOQL_FOR_KEYWORD);
    if (!r) r = consumeToken(b, UPDATE_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // soql_expression_unit COMMA? (soql_expression_unit|COLON)*
  static boolean soql_token_composition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_token_composition")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = soql_expression_unit(b, l + 1);
    r = r && soql_token_composition_1(b, l + 1);
    r = r && soql_token_composition_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean soql_token_composition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_token_composition_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // (soql_expression_unit|COLON)*
  private static boolean soql_token_composition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_token_composition_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!soql_token_composition_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "soql_token_composition_2", c)) break;
    }
    return true;
  }

  // soql_expression_unit|COLON
  private static boolean soql_token_composition_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "soql_token_composition_2_0")) return false;
    boolean r;
    r = soql_expression_unit(b, l + 1);
    if (!r) r = consumeToken(b, COLON);
    return r;
  }

  /* ********************************************************** */
  // variable_reference_expr (COMMA variable_reference_expr)*
  static boolean type_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variable_reference_expr(b, l + 1);
    r = r && type_list_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA variable_reference_expr)*
  private static boolean type_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!type_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "type_list_1", c)) break;
    }
    return true;
  }

  // COMMA variable_reference_expr
  private static boolean type_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variable_reference_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NEW_KEYWORD CombinedIdentifier (LESS type_list GREATER)? ARRAY_BRACKET?
  static boolean typedef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedef")) return false;
    if (!nextTokenIs(b, NEW_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_KEYWORD);
    r = r && CombinedIdentifier(b, l + 1);
    r = r && typedef_2(b, l + 1);
    r = r && typedef_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LESS type_list GREATER)?
  private static boolean typedef_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedef_2")) return false;
    typedef_2_0(b, l + 1);
    return true;
  }

  // LESS type_list GREATER
  private static boolean typedef_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedef_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LESS);
    r = r && type_list(b, l + 1);
    r = r && consumeToken(b, GREATER);
    exit_section_(b, m, null, r);
    return r;
  }

  // ARRAY_BRACKET?
  private static boolean typedef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedef_3")) return false;
    consumeToken(b, ARRAY_BRACKET);
    return true;
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  static boolean value_expression_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_expression_list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && value_expression_list_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean value_expression_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_expression_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!value_expression_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_expression_list_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean value_expression_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_expression_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // object_creation_expr | CombinedIdentifier (LESS type_list GREATER)? (QUESTION_MARK? DOT variable_reference_expr)? (argument_group)? (DOT variable_reference_expr)?
  static boolean variable_reference_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_creation_expr(b, l + 1);
    if (!r) r = variable_reference_expr_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CombinedIdentifier (LESS type_list GREATER)? (QUESTION_MARK? DOT variable_reference_expr)? (argument_group)? (DOT variable_reference_expr)?
  private static boolean variable_reference_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CombinedIdentifier(b, l + 1);
    r = r && variable_reference_expr_1_1(b, l + 1);
    r = r && variable_reference_expr_1_2(b, l + 1);
    r = r && variable_reference_expr_1_3(b, l + 1);
    r = r && variable_reference_expr_1_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LESS type_list GREATER)?
  private static boolean variable_reference_expr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_1")) return false;
    variable_reference_expr_1_1_0(b, l + 1);
    return true;
  }

  // LESS type_list GREATER
  private static boolean variable_reference_expr_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LESS);
    r = r && type_list(b, l + 1);
    r = r && consumeToken(b, GREATER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (QUESTION_MARK? DOT variable_reference_expr)?
  private static boolean variable_reference_expr_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_2")) return false;
    variable_reference_expr_1_2_0(b, l + 1);
    return true;
  }

  // QUESTION_MARK? DOT variable_reference_expr
  private static boolean variable_reference_expr_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variable_reference_expr_1_2_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && variable_reference_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK?
  private static boolean variable_reference_expr_1_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_2_0_0")) return false;
    consumeToken(b, QUESTION_MARK);
    return true;
  }

  // (argument_group)?
  private static boolean variable_reference_expr_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_3")) return false;
    variable_reference_expr_1_3_0(b, l + 1);
    return true;
  }

  // (argument_group)
  private static boolean variable_reference_expr_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_group(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (DOT variable_reference_expr)?
  private static boolean variable_reference_expr_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_4")) return false;
    variable_reference_expr_1_4_0(b, l + 1);
    return true;
  }

  // DOT variable_reference_expr
  private static boolean variable_reference_expr_1_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr_1_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && variable_reference_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: BINARY(ternary_expr)
  // 1: PREFIX(type_cast_expr)
  // 2: BINARY(add_expr)
  // 3: BINARY(min_expr)
  // 4: BINARY(mul_expr)
  // 5: BINARY(div_expr)
  // 6: BINARY(logical_and_expr)
  // 7: BINARY(logical_or_expr)
  // 8: BINARY(logical_greater_expr)
  // 9: BINARY(logical_less_expr)
  // 10: BINARY(logical_greater_equal_expr)
  // 11: BINARY(logical_less_equal_expr)
  // 12: BINARY(logical_equal_expr)
  // 13: BINARY(logical_equal_equal_expr)
  // 14: BINARY(logical_not_equal_expr)
  // 15: BINARY(logical_not_equal_equal_expr)
  // 16: PREFIX(logical_not_expr)
  // 17: BINARY(bitwise_and_expr)
  // 18: BINARY(bitwise_or_expr)
  // 19: BINARY(bitwise_xor_expr)
  // 20: ATOM(primary_expr)
  // 21: BINARY(assignment_expr)
  // 22: BINARY(array_access_expr)
  // 23: PREFIX(unary_prefix_increment_expr)
  // 24: PREFIX(unary_prefix_decrement_expr)
  // 25: POSTFIX(unary_postfix_increment_expr)
  // 26: POSTFIX(unary_postfix_decrement_expr)
  // 27: BINARY(shorthand_assignment_expr)
  // 28: PREFIX(negation_expr)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = type_cast_expr(b, l + 1);
    if (!r) r = negation_expr(b, l + 1);
    if (!r) r = logical_not_expr(b, l + 1);
    if (!r) r = primary_expr(b, l + 1);
    if (!r) r = unary_prefix_increment_expr(b, l + 1);
    if (!r) r = unary_prefix_decrement_expr(b, l + 1);
    p = r;
    r = r && expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && consumeTokenSmart(b, QUESTION_MARK)) {
        r = report_error_(b, expression(b, l, 0));
        r = ternary_expr_1(b, l + 1) && r;
        exit_section_(b, l, m, TERNARY_EXPR, r, true, null);
      }
      else if (g < 2 && consumeTokenSmart(b, PLUS)) {
        r = expression(b, l, 2);
        exit_section_(b, l, m, ADD_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, MINUS)) {
        r = expression(b, l, 3);
        exit_section_(b, l, m, MIN_EXPR, r, true, null);
      }
      else if (g < 4 && consumeTokenSmart(b, MULTIPLY)) {
        r = expression(b, l, 4);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 5 && consumeTokenSmart(b, DIVIDE)) {
        r = expression(b, l, 5);
        exit_section_(b, l, m, DIV_EXPR, r, true, null);
      }
      else if (g < 6 && consumeTokenSmart(b, AND)) {
        r = expression(b, l, 6);
        exit_section_(b, l, m, LOGICAL_AND_EXPR, r, true, null);
      }
      else if (g < 7 && consumeTokenSmart(b, OR)) {
        r = expression(b, l, 7);
        exit_section_(b, l, m, LOGICAL_OR_EXPR, r, true, null);
      }
      else if (g < 8 && consumeTokenSmart(b, GREATER)) {
        r = expression(b, l, 8);
        exit_section_(b, l, m, LOGICAL_GREATER_EXPR, r, true, null);
      }
      else if (g < 9 && consumeTokenSmart(b, LESS)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, LOGICAL_LESS_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, GREATER_EQUAL)) {
        r = expression(b, l, 10);
        exit_section_(b, l, m, LOGICAL_GREATER_EQUAL_EXPR, r, true, null);
      }
      else if (g < 11 && consumeTokenSmart(b, LESS_EQUAL)) {
        r = expression(b, l, 11);
        exit_section_(b, l, m, LOGICAL_LESS_EQUAL_EXPR, r, true, null);
      }
      else if (g < 12 && consumeTokenSmart(b, EQUAL)) {
        r = expression(b, l, 12);
        exit_section_(b, l, m, LOGICAL_EQUAL_EXPR, r, true, null);
      }
      else if (g < 13 && consumeTokenSmart(b, NOT_EQUAL_EQAL)) {
        r = expression(b, l, 13);
        exit_section_(b, l, m, LOGICAL_EQUAL_EQUAL_EXPR, r, true, null);
      }
      else if (g < 14 && consumeTokenSmart(b, NOT_EQUAL)) {
        r = expression(b, l, 14);
        exit_section_(b, l, m, LOGICAL_NOT_EQUAL_EXPR, r, true, null);
      }
      else if (g < 17 && consumeTokenSmart(b, BITWISE_AND)) {
        r = expression(b, l, 17);
        exit_section_(b, l, m, BITWISE_AND_EXPR, r, true, null);
      }
      else if (g < 18 && consumeTokenSmart(b, BITWISE_OR)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, BITWISE_OR_EXPR, r, true, null);
      }
      else if (g < 19 && consumeTokenSmart(b, BITWISE_XOR)) {
        r = expression(b, l, 19);
        exit_section_(b, l, m, BITWISE_XOR_EXPR, r, true, null);
      }
      else if (g < 21 && assignment_expr_0(b, l + 1)) {
        r = expression(b, l, 21);
        exit_section_(b, l, m, ASSIGNMENT_EXPR, r, true, null);
      }
      else if (g < 22 && consumeTokenSmart(b, LBRACKET)) {
        r = report_error_(b, expression(b, l, 22));
        r = array_access_expr_1(b, l + 1) && r;
        exit_section_(b, l, m, ARRAY_ACCESS_EXPR, r, true, null);
      }
      else if (g < 25 && consumeTokenSmart(b, INCREMENT)) {
        r = true;
        exit_section_(b, l, m, UNARY_POSTFIX_INCREMENT_EXPR, r, true, null);
      }
      else if (g < 26 && consumeTokenSmart(b, DECREMENT)) {
        r = true;
        exit_section_(b, l, m, UNARY_POSTFIX_DECREMENT_EXPR, r, true, null);
      }
      else if (g < 27 && shorthand_assignment_expr_0(b, l + 1)) {
        r = expression(b, l, 27);
        exit_section_(b, l, m, SHORTHAND_ASSIGNMENT_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // COLON expression
  private static boolean ternary_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean type_cast_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_expr")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = type_cast_expr_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 1);
    exit_section_(b, l, m, TYPE_CAST_EXPR, r, p, null);
    return r || p;
  }

  // LPAREN TypeIdentifier RPAREN
  private static boolean type_cast_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && TypeIdentifier(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean negation_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "negation_expr")) return false;
    if (!nextTokenIsSmart(b, MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, MINUS);
    p = r;
    r = p && expression(b, l, -1);
    exit_section_(b, l, m, NEGATION_EXPR, r, p, null);
    return r || p;
  }

  public static boolean logical_not_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "logical_not_expr")) return false;
    if (!nextTokenIsSmart(b, NOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, NOT);
    p = r;
    r = p && expression(b, l, 16);
    exit_section_(b, l, m, LOGICAL_NOT_EXPR, r, p, null);
    return r || p;
  }

  // literal_with_attribute
  //              | variable_reference_expr
  //              | THIS_KEYWORD
  //              | SUPER_KEYWORD
  //              | paren_expr
  public static boolean primary_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PRIMARY_EXPR, "<primary expr>");
    r = literal_with_attribute(b, l + 1);
    if (!r) r = variable_reference_expr(b, l + 1);
    if (!r) r = consumeTokenSmart(b, THIS_KEYWORD);
    if (!r) r = consumeTokenSmart(b, SUPER_KEYWORD);
    if (!r) r = paren_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ASSIGN GREATER? COLON?
  private static boolean assignment_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignment_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ASSIGN);
    r = r && assignment_expr_0_1(b, l + 1);
    r = r && assignment_expr_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GREATER?
  private static boolean assignment_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignment_expr_0_1")) return false;
    consumeTokenSmart(b, GREATER);
    return true;
  }

  // COLON?
  private static boolean assignment_expr_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignment_expr_0_2")) return false;
    consumeTokenSmart(b, COLON);
    return true;
  }

  // RBRACKET (QUESTION_MARK? DOT expression)?
  private static boolean array_access_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_access_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RBRACKET);
    r = r && array_access_expr_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (QUESTION_MARK? DOT expression)?
  private static boolean array_access_expr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_access_expr_1_1")) return false;
    array_access_expr_1_1_0(b, l + 1);
    return true;
  }

  // QUESTION_MARK? DOT expression
  private static boolean array_access_expr_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_access_expr_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = array_access_expr_1_1_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK?
  private static boolean array_access_expr_1_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_access_expr_1_1_0_0")) return false;
    consumeToken(b, QUESTION_MARK);
    return true;
  }

  public static boolean unary_prefix_increment_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_prefix_increment_expr")) return false;
    if (!nextTokenIsSmart(b, INCREMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, INCREMENT);
    p = r;
    r = p && expression(b, l, 23);
    exit_section_(b, l, m, UNARY_PREFIX_INCREMENT_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_prefix_decrement_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_prefix_decrement_expr")) return false;
    if (!nextTokenIsSmart(b, DECREMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, DECREMENT);
    p = r;
    r = p && expression(b, l, 24);
    exit_section_(b, l, m, UNARY_PREFIX_DECREMENT_EXPR, r, p, null);
    return r || p;
  }

  // SHORT_HANDLE_PLUS | SHORT_HANDLE_MINUS | SHORT_HANDLE_MULTIPLY | SHORT_HANDLE_DIVIDE | SHORT_HANDLE_BITWISE_AND | SHORT_HANDLE_BITWISE_OR | SHORT_HANDLE_BITWISE_XOR | SHORT_HANDLE_NULL_CHECK
  private static boolean shorthand_assignment_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shorthand_assignment_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, SHORT_HANDLE_PLUS);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_MINUS);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_MULTIPLY);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_DIVIDE);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_BITWISE_AND);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_BITWISE_OR);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_BITWISE_XOR);
    if (!r) r = consumeTokenSmart(b, SHORT_HANDLE_NULL_CHECK);
    return r;
  }

}
