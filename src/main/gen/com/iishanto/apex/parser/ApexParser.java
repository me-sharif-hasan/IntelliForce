// license.txt
package com.iishanto.apex.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.iishanto.apex.psi.ApexTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
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
    return apexFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(CLASS_DECLARATION, CONSTRUCTOR_DECLARATION, ENUM_DECLARATION, INTERFACE_DECLARATION,
      METHOD_DECLARATION),
    create_token_set_(ADDITION_ASSIGN_EXPRESSION, ADDITION_EXPRESSION, AND_ASSIGN_EXPRESSION, AND_EXPRESSION,
      ARRAY_POSITION_EXPRESSION, ASSIGN_EXPRESSION, BITWISE_AND_EXPRESSION, BITWISE_EXCLUSIVE_OR_1_EXPRESSION,
      BITWISE_EXCLUSIVE_OR_2_EXPRESSION, BITWISE_OR_EXPRESSION, BITWISE_SHIFT_LEFT_EXPRESSION, BITWISE_SHIFT_RIGHT_EXPRESSION,
      BITWISE_SHIFT_RIGHT_UNSIGNED_EXPRESSION, CAST_EXPRESSION, DECREMENT_AFTER_EXPRESSION, DECREMENT_BEFORE_EXPRESSION,
      DIVIDE_ASSIGN_EXPRESSION, DIVISION_EXPRESSION, EQUALITY_EXPRESSION, EXACT_EQUALITY_EXPRESSION,
      EXACT_INEQUALITY_EXPRESSION, EXPRESSION, EXPRESSION_LIST_EXPRESSION, GENERIC_EXPRESSION,
      GREATER_EQUAL_EXPRESSION, GREATER_THAN_EXPRESSION, IDENTIFIER_EXPRESSION, INCREMENT_AFTER_EXPRESSION,
      INCREMENT_BEFORE_EXPRESSION, INEQUALITY_EXPRESSION, INSTANCE_OF_EXPRESSION, INSTANTIATION_EXPRESSION,
      LESS_EQUAL_EXPRESSION, LESS_THAN_EXPRESSION, LOGICAL_COMPLIMENT_EXPRESSION, MULTIPLICATION_ASSIGN_EXPRESSION,
      MULTIPLICATION_EXPRESSION, OR_ASSIGN_EXPRESSION, OR_EXPRESSION, PRIMARY_EXPRESSION,
      SHIFT_LEFT_ASSIGN_EXPRESSION, SHIFT_RIGHT_ASSIGN_EXPRESSION, SHIFT_RIGHT_UNSIGNED_ASSIGN_EXPRESSION, SUBTRACTION_ASSIGN_EXPRESSION,
      SUBTRACTION_EXPRESSION, SUPER_EXPRESSION, TERNARY_EXPRESSION, THIS_EXPRESSION,
      UNARY_NEGATION_EXPRESSION, WEIRD_EXPRESSION),
  };

  /* ********************************************************** */
  // '@' identifier ( '(' annotationParameters ')' )?
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, AT_SIGN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION, null);
    r = consumeTokens(b, 2, AT_SIGN, IDENTIFIER);
    p = r; // pin = 2
    r = r && annotation_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ( '(' annotationParameters ')' )?
  private static boolean annotation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_2")) return false;
    annotation_2_0(b, l + 1);
    return true;
  }

  // '(' annotationParameters ')'
  private static boolean annotation_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && annotationParameters(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier '=' annotationParameterValue
  public static boolean annotationParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_PARAMETER, null);
    r = consumeTokens(b, 1, IDENTIFIER, OPERATOR_ASSIGNMENT);
    p = r; // pin = 1
    r = r && annotationParameterValue(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // STRING_LITERAL | booleanLiteral
  public static boolean annotationParameterValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationParameterValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_PARAMETER_VALUE, "<annotation parameter value>");
    r = consumeToken(b, STRING_LITERAL);
    if (!r) r = booleanLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // annotationParameter (',' annotationParameter)*
  static boolean annotationParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationParameters")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotationParameter(b, l + 1);
    r = r && annotationParameters_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' annotationParameter)*
  private static boolean annotationParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationParameters_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotationParameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationParameters_1", c)) break;
    }
    return true;
  }

  // ',' annotationParameter
  private static boolean annotationParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && annotationParameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // interfaceDeclaration
  //     |   classDeclaration
  //     |   triggerDefinition
  //     |   enumDeclaration
  static boolean apexFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apexFile")) return false;
    boolean r;
    r = interfaceDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    if (!r) r = triggerDefinition(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // '(' expressionList? ')'
  public static boolean arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && arguments_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ARGUMENTS, r);
    return r;
  }

  // expressionList?
  private static boolean arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1")) return false;
    expressionList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '['
  //         (   ']' ('[' ']')* arrayInitializer
  //         |   expression ']' ('[' expression ']')* ('[' ']')*
  //         )
  public static boolean arrayCreatorRest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && arrayCreatorRest_1(b, l + 1);
    exit_section_(b, m, ARRAY_CREATOR_REST, r);
    return r;
  }

  // ']' ('[' ']')* arrayInitializer
  //         |   expression ']' ('[' expression ']')* ('[' ']')*
  private static boolean arrayCreatorRest_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arrayCreatorRest_1_0(b, l + 1);
    if (!r) r = arrayCreatorRest_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ']' ('[' ']')* arrayInitializer
  private static boolean arrayCreatorRest_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RBRACK);
    r = r && arrayCreatorRest_1_0_1(b, l + 1);
    r = r && arrayInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('[' ']')*
  private static boolean arrayCreatorRest_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayCreatorRest_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreatorRest_1_0_1", c)) break;
    }
    return true;
  }

  // '[' ']'
  private static boolean arrayCreatorRest_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression ']' ('[' expression ']')* ('[' ']')*
  private static boolean arrayCreatorRest_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACK);
    r = r && arrayCreatorRest_1_1_2(b, l + 1);
    r = r && arrayCreatorRest_1_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('[' expression ']')*
  private static boolean arrayCreatorRest_1_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayCreatorRest_1_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreatorRest_1_1_2", c)) break;
    }
    return true;
  }

  // '[' expression ']'
  private static boolean arrayCreatorRest_1_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('[' ']')*
  private static boolean arrayCreatorRest_1_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_1_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayCreatorRest_1_1_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreatorRest_1_1_3", c)) break;
    }
    return true;
  }

  // '[' ']'
  private static boolean arrayCreatorRest_1_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreatorRest_1_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '{' (variableInitializer (',' variableInitializer)* (',')? )? '}'
  public static boolean arrayInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_INITIALIZER, null);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, arrayInitializer_1(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (variableInitializer (',' variableInitializer)* (',')? )?
  private static boolean arrayInitializer_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1")) return false;
    arrayInitializer_1_0(b, l + 1);
    return true;
  }

  // variableInitializer (',' variableInitializer)* (',')?
  private static boolean arrayInitializer_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableInitializer(b, l + 1);
    r = r && arrayInitializer_1_0_1(b, l + 1);
    r = r && arrayInitializer_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' variableInitializer)*
  private static boolean arrayInitializer_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayInitializer_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayInitializer_1_0_1", c)) break;
    }
    return true;
  }

  // ',' variableInitializer
  private static boolean arrayInitializer_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',')?
  private static boolean arrayInitializer_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // '{' blockStatement* '}'
  public static boolean block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && block_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, BLOCK, r);
    return r;
  }

  // blockStatement*
  private static boolean block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!blockStatement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // localVariableDeclarationStatement
  //     |   statement
  //     |   classDeclaration
  //     |   interfaceDeclaration
  public static boolean blockStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "blockStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_STATEMENT, "<block statement>");
    r = localVariableDeclarationStatement(b, l + 1);
    if (!r) r = statement(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    if (!r) r = interfaceDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // true
  //     |   false
  public static boolean booleanLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanLiteral")) return false;
    if (!nextTokenIs(b, "<boolean literal>", FALSE, TRUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN_LITERAL, "<boolean literal>");
    r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // catch '(' variableModifier? classOrInterfaceReference identifier ')' block
  public static boolean catchClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchClause")) return false;
    if (!nextTokenIs(b, CATCH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CATCH_CLAUSE, null);
    r = consumeTokens(b, 1, CATCH, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, catchClause_2(b, l + 1));
    r = p && report_error_(b, classOrInterfaceReference(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, IDENTIFIER, RPAREN)) && r;
    r = p && block(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // variableModifier?
  private static boolean catchClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchClause_2")) return false;
    variableModifier(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' '}'
  //     |   '{' (members | staticBlock | block)* '}'
  public static boolean classBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, LBRACE, RBRACE);
    if (!r) r = classBody_1(b, l + 1);
    exit_section_(b, m, CLASS_BODY, r);
    return r;
  }

  // '{' (members | staticBlock | block)* '}'
  private static boolean classBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && classBody_1_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (members | staticBlock | block)*
  private static boolean classBody_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!classBody_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classBody_1_1", c)) break;
    }
    return true;
  }

  // members | staticBlock | block
  private static boolean classBody_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody_1_1_0")) return false;
    boolean r;
    r = members(b, l + 1);
    if (!r) r = staticBlock(b, l + 1);
    if (!r) r = block(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // arguments classBody?
  public static boolean classCreatorRest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classCreatorRest")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arguments(b, l + 1);
    r = r && classCreatorRest_1(b, l + 1);
    exit_section_(b, m, CLASS_CREATOR_REST, r);
    return r;
  }

  // classBody?
  private static boolean classCreatorRest_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classCreatorRest_1")) return false;
    classBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // annotation* visibility? virtualOrAbstractModifier? sharingModifier? CLASS_KEYWORD identifier extendsClause? implementsClause? classBody
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DECLARATION, "<class declaration>");
    r = classDeclaration_0(b, l + 1);
    r = r && classDeclaration_1(b, l + 1);
    r = r && classDeclaration_2(b, l + 1);
    r = r && classDeclaration_3(b, l + 1);
    r = r && consumeTokens(b, 2, CLASS_KEYWORD, IDENTIFIER);
    p = r; // pin = 6
    r = r && report_error_(b, classDeclaration_6(b, l + 1));
    r = p && report_error_(b, classDeclaration_7(b, l + 1)) && r;
    r = p && classBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // annotation*
  private static boolean classDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classDeclaration_0", c)) break;
    }
    return true;
  }

  // visibility?
  private static boolean classDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_1")) return false;
    visibility(b, l + 1);
    return true;
  }

  // virtualOrAbstractModifier?
  private static boolean classDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_2")) return false;
    virtualOrAbstractModifier(b, l + 1);
    return true;
  }

  // sharingModifier?
  private static boolean classDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_3")) return false;
    sharingModifier(b, l + 1);
    return true;
  }

  // extendsClause?
  private static boolean classDeclaration_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_6")) return false;
    extendsClause(b, l + 1);
    return true;
  }

  // implementsClause?
  private static boolean classDeclaration_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_7")) return false;
    implementsClause(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // classOrInterfaceReference ('[' ']')?
  //     |   primitiveType ('[' ']')?
  //     |   collectionType
  public static boolean classInterfaceOrPrimitiveType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_INTERFACE_OR_PRIMITIVE_TYPE, "<class interface or primitive type>");
    r = classInterfaceOrPrimitiveType_0(b, l + 1);
    if (!r) r = classInterfaceOrPrimitiveType_1(b, l + 1);
    if (!r) r = collectionType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // classOrInterfaceReference ('[' ']')?
  private static boolean classInterfaceOrPrimitiveType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classOrInterfaceReference(b, l + 1);
    r = r && classInterfaceOrPrimitiveType_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('[' ']')?
  private static boolean classInterfaceOrPrimitiveType_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType_0_1")) return false;
    classInterfaceOrPrimitiveType_0_1_0(b, l + 1);
    return true;
  }

  // '[' ']'
  private static boolean classInterfaceOrPrimitiveType_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  // primitiveType ('[' ']')?
  private static boolean classInterfaceOrPrimitiveType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = primitiveType(b, l + 1);
    r = r && classInterfaceOrPrimitiveType_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('[' ']')?
  private static boolean classInterfaceOrPrimitiveType_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType_1_1")) return false;
    classInterfaceOrPrimitiveType_1_1_0(b, l + 1);
    return true;
  }

  // '[' ']'
  private static boolean classInterfaceOrPrimitiveType_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classInterfaceOrPrimitiveType_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean classOrInterfaceReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classOrInterfaceReference")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, CLASS_OR_INTERFACE_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // for '(' forInit? ';' expression? ';' forUpdate? ')' statement
  public static boolean classicForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classicForStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASSIC_FOR_STATEMENT, null);
    r = consumeTokens(b, 0, FOR, LPAREN);
    r = r && classicForStatement_2(b, l + 1);
    r = r && consumeToken(b, SEMI);
    p = r; // pin = 4
    r = r && report_error_(b, classicForStatement_4(b, l + 1));
    r = p && report_error_(b, consumeToken(b, SEMI)) && r;
    r = p && report_error_(b, classicForStatement_6(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && statement(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // forInit?
  private static boolean classicForStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classicForStatement_2")) return false;
    forInit(b, l + 1);
    return true;
  }

  // expression?
  private static boolean classicForStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classicForStatement_4")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // forUpdate?
  private static boolean classicForStatement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classicForStatement_6")) return false;
    forUpdate(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // listCollection | setCollection | mapCollection
  static boolean collectionType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "collectionType")) return false;
    boolean r;
    r = listCollection(b, l + 1);
    if (!r) r = setCollection(b, l + 1);
    if (!r) r = mapCollection(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // block
  public static boolean constructorBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constructorBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = block(b, l + 1);
    exit_section_(b, m, CONSTRUCTOR_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // visibility? identifier parameters constructorBody
  public static boolean constructorDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constructorDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONSTRUCTOR_DECLARATION, "<constructor declaration>");
    r = constructorDeclaration_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && parameters(b, l + 1);
    p = r; // pin = 3
    r = r && constructorBody(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // visibility?
  private static boolean constructorDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constructorDeclaration_0")) return false;
    visibility(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // identifier typeArgumentsOrDiamond? ('.' identifier typeArgumentsOrDiamond?)*
  //     |   primitiveType
  public static boolean createdName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CREATED_NAME, "<created name>");
    r = createdName_0(b, l + 1);
    if (!r) r = primitiveType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // identifier typeArgumentsOrDiamond? ('.' identifier typeArgumentsOrDiamond?)*
  private static boolean createdName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && createdName_0_1(b, l + 1);
    r = r && createdName_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // typeArgumentsOrDiamond?
  private static boolean createdName_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName_0_1")) return false;
    typeArgumentsOrDiamond(b, l + 1);
    return true;
  }

  // ('.' identifier typeArgumentsOrDiamond?)*
  private static boolean createdName_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!createdName_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "createdName_0_2", c)) break;
    }
    return true;
  }

  // '.' identifier typeArgumentsOrDiamond?
  private static boolean createdName_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    r = r && createdName_0_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // typeArgumentsOrDiamond?
  private static boolean createdName_0_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName_0_2_0_2")) return false;
    typeArgumentsOrDiamond(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // nonWildcardTypeArguments createdName classCreatorRest
  //     |   createdName (arrayCreatorRest | classCreatorRest)
  public static boolean creator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CREATOR, "<creator>");
    r = creator_0(b, l + 1);
    if (!r) r = creator_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // nonWildcardTypeArguments createdName classCreatorRest
  private static boolean creator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = nonWildcardTypeArguments(b, l + 1);
    r = r && createdName(b, l + 1);
    r = r && classCreatorRest(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // createdName (arrayCreatorRest | classCreatorRest)
  private static boolean creator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = createdName(b, l + 1);
    r = r && creator_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // arrayCreatorRest | classCreatorRest
  private static boolean creator_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator_1_1")) return false;
    boolean r;
    r = arrayCreatorRest(b, l + 1);
    if (!r) r = classCreatorRest(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // do statement while '(' expression ')' ';'
  public static boolean doStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doStatement")) return false;
    if (!nextTokenIs(b, DO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DO_STATEMENT, null);
    r = consumeToken(b, DO);
    p = r; // pin = 1
    r = r && report_error_(b, statement(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, WHILE, LPAREN)) && r;
    r = p && report_error_(b, expression(b, l + 1, -1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, RPAREN, SEMI)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // for '(' variableModifier? classInterfaceOrPrimitiveType identifier ':' expression ')' statement
  public static boolean enhancedForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enhancedForStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENHANCED_FOR_STATEMENT, null);
    r = consumeTokens(b, 0, FOR, LPAREN);
    r = r && enhancedForStatement_2(b, l + 1);
    r = r && classInterfaceOrPrimitiveType(b, l + 1);
    r = r && consumeTokens(b, 2, IDENTIFIER, OPERATOR_COLON);
    p = r; // pin = 6
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && statement(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // variableModifier?
  private static boolean enhancedForStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enhancedForStatement_2")) return false;
    variableModifier(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // annotation* visibility? enum identifier '{' enumIdentifiers '}'
  public static boolean enumDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_DECLARATION, "<enum declaration>");
    r = enumDeclaration_0(b, l + 1);
    r = r && enumDeclaration_1(b, l + 1);
    r = r && consumeTokens(b, 1, ENUM, IDENTIFIER, LBRACE);
    p = r; // pin = 3
    r = r && report_error_(b, enumIdentifiers(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // annotation*
  private static boolean enumDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumDeclaration_0", c)) break;
    }
    return true;
  }

  // visibility?
  private static boolean enumDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_1")) return false;
    visibility(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // identifier (',' identifier)*
  static boolean enumIdentifiers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumIdentifiers")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && enumIdentifiers_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' identifier)*
  private static boolean enumIdentifiers_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumIdentifiers_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enumIdentifiers_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumIdentifiers_1", c)) break;
    }
    return true;
  }

  // ',' identifier
  private static boolean enumIdentifiers_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumIdentifiers_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // nonWildcardTypeArguments explicitGenericInvocationSuffix
  public static boolean explicitGenericInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitGenericInvocation")) return false;
    if (!nextTokenIs(b, OPERATOR_LESS_THAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = nonWildcardTypeArguments(b, l + 1);
    r = r && explicitGenericInvocationSuffix(b, l + 1);
    exit_section_(b, m, EXPLICIT_GENERIC_INVOCATION, r);
    return r;
  }

  /* ********************************************************** */
  // super superSuffix
  //     |   identifier arguments
  public static boolean explicitGenericInvocationSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitGenericInvocationSuffix")) return false;
    if (!nextTokenIs(b, "<explicit generic invocation suffix>", IDENTIFIER, SUPER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPLICIT_GENERIC_INVOCATION_SUFFIX, "<explicit generic invocation suffix>");
    r = explicitGenericInvocationSuffix_0(b, l + 1);
    if (!r) r = explicitGenericInvocationSuffix_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // super superSuffix
  private static boolean explicitGenericInvocationSuffix_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitGenericInvocationSuffix_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUPER);
    r = r && superSuffix(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // identifier arguments
  private static boolean explicitGenericInvocationSuffix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitGenericInvocationSuffix_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expression (',' expression)*
  public static boolean expressionList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_LIST, "<expression list>");
    r = expression(b, l + 1, -1);
    r = r && expressionList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' expression)*
  private static boolean expressionList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expressionList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expressionList_1", c)) break;
    }
    return true;
  }

  // ',' expression
  private static boolean expressionList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // extends classOrInterfaceReference
  public static boolean extendsClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extendsClause")) return false;
    if (!nextTokenIs(b, EXTENDS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXTENDS);
    r = r && classOrInterfaceReference(b, l + 1);
    exit_section_(b, m, EXTENDS_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // fieldVisibility? classInterfaceOrPrimitiveType variableDeclarators ';'
  public static boolean fieldDeclarator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclarator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_DECLARATOR, "<field declarator>");
    r = fieldDeclarator_0(b, l + 1);
    r = r && classInterfaceOrPrimitiveType(b, l + 1);
    r = r && variableDeclarators(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // fieldVisibility?
  private static boolean fieldDeclarator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclarator_0")) return false;
    fieldVisibility(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // public | private | global | protected
  public static boolean fieldVisibility(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldVisibility")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_VISIBILITY, "<field visibility>");
    r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, GLOBAL);
    if (!r) r = consumeToken(b, PROTECTED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // finally block
  public static boolean finallyBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "finallyBlock")) return false;
    if (!nextTokenIs(b, FINALLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FINALLY_BLOCK, null);
    r = consumeToken(b, FINALLY);
    p = r; // pin = 1
    r = r && block(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // localVariableDeclarator
  //     |    expressionList
  public static boolean forInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forInit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_INIT, "<for init>");
    r = localVariableDeclarator(b, l + 1);
    if (!r) r = expressionList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expressionList
  public static boolean forUpdate(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forUpdate")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_UPDATE, "<for update>");
    r = expressionList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // if '(' expression ')' statement (else statement)?
  public static boolean ifStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IF_STATEMENT, null);
    r = consumeTokens(b, 1, IF, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && report_error_(b, statement(b, l + 1)) && r;
    r = p && ifStatement_5(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (else statement)?
  private static boolean ifStatement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement_5")) return false;
    ifStatement_5_0(b, l + 1);
    return true;
  }

  // else statement
  private static boolean ifStatement_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // implements classOrInterfaceReference (',' classOrInterfaceReference)*
  public static boolean implementsClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implementsClause")) return false;
    if (!nextTokenIs(b, IMPLEMENTS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IMPLEMENTS_CLAUSE, null);
    r = consumeToken(b, IMPLEMENTS);
    r = r && classOrInterfaceReference(b, l + 1);
    p = r; // pin = 2
    r = r && implementsClause_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' classOrInterfaceReference)*
  private static boolean implementsClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implementsClause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!implementsClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "implementsClause_2", c)) break;
    }
    return true;
  }

  // ',' classOrInterfaceReference
  private static boolean implementsClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implementsClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && classOrInterfaceReference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier nonWildcardTypeArgumentsOrDiamond? classCreatorRest
  public static boolean innerCreator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "innerCreator")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && innerCreator_1(b, l + 1);
    r = r && classCreatorRest(b, l + 1);
    exit_section_(b, m, INNER_CREATOR, r);
    return r;
  }

  // nonWildcardTypeArgumentsOrDiamond?
  private static boolean innerCreator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "innerCreator_1")) return false;
    nonWildcardTypeArgumentsOrDiamond(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' '}'
  public static boolean interfaceBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interfaceBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACE, RBRACE);
    exit_section_(b, m, INTERFACE_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // visibility? virtual? interface identifier extendsClause? interfaceBody
  public static boolean interfaceDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interfaceDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INTERFACE_DECLARATION, "<interface declaration>");
    r = interfaceDeclaration_0(b, l + 1);
    r = r && interfaceDeclaration_1(b, l + 1);
    r = r && consumeTokens(b, 2, INTERFACE, IDENTIFIER);
    p = r; // pin = 4
    r = r && report_error_(b, interfaceDeclaration_4(b, l + 1));
    r = p && interfaceBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // visibility?
  private static boolean interfaceDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interfaceDeclaration_0")) return false;
    visibility(b, l + 1);
    return true;
  }

  // virtual?
  private static boolean interfaceDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interfaceDeclaration_1")) return false;
    consumeToken(b, VIRTUAL);
    return true;
  }

  // extendsClause?
  private static boolean interfaceDeclaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interfaceDeclaration_4")) return false;
    extendsClause(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // List '<' typeArgument '>'
  public static boolean listCollection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listCollection")) return false;
    if (!nextTokenIs(b, LIST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LIST_COLLECTION, null);
    r = consumeTokens(b, 1, LIST, OPERATOR_LESS_THAN);
    p = r; // pin = 1
    r = r && report_error_(b, typeArgument(b, l + 1));
    r = p && consumeToken(b, OPERATOR_GREATER_THAN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INTEGER_LITERAL
  //     |   DECIMAL_LITERAL
  //     |   LONG_LITERAL
  //     |   STRING_LITERAL
  //     |   booleanLiteral
  //     |   null
  public static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = consumeToken(b, INTEGER_LITERAL);
    if (!r) r = consumeToken(b, DECIMAL_LITERAL);
    if (!r) r = consumeToken(b, LONG_LITERAL);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    if (!r) r = booleanLiteral(b, l + 1);
    if (!r) r = consumeToken(b, NULL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // localVariableDeclarator ';'
  public static boolean localVariableDeclarationStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclarationStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOCAL_VARIABLE_DECLARATION_STATEMENT, "<local variable declaration statement>");
    r = localVariableDeclarator(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variableModifier? classInterfaceOrPrimitiveType variableDeclarators
  public static boolean localVariableDeclarator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclarator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOCAL_VARIABLE_DECLARATOR, "<local variable declarator>");
    r = localVariableDeclarator_0(b, l + 1);
    r = r && classInterfaceOrPrimitiveType(b, l + 1);
    r = r && variableDeclarators(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // variableModifier?
  private static boolean localVariableDeclarator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclarator_0")) return false;
    variableModifier(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Map '<' typeArgument ',' typeArgument '>'
  public static boolean mapCollection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapCollection")) return false;
    if (!nextTokenIs(b, MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_COLLECTION, null);
    r = consumeTokens(b, 1, MAP, OPERATOR_LESS_THAN);
    p = r; // pin = 1
    r = r && report_error_(b, typeArgument(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, typeArgument(b, l + 1)) && r;
    r = p && consumeToken(b, OPERATOR_GREATER_THAN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // constructorDeclaration
  //     |   fieldDeclarator
  //     |   methodDeclaration
  //     |   interfaceDeclaration
  //     |   classDeclaration
  static boolean members(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "members")) return false;
    boolean r;
    r = constructorDeclaration(b, l + 1);
    if (!r) r = fieldDeclarator(b, l + 1);
    if (!r) r = methodDeclaration(b, l + 1);
    if (!r) r = interfaceDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // block
  public static boolean methodBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = block(b, l + 1);
    exit_section_(b, m, METHOD_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // annotation* visibility? staticOrTransientModifier* virtualOrAbstractModifier? ( classInterfaceOrPrimitiveType | void ) identifier parameters methodBody
  public static boolean methodDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DECLARATION, "<method declaration>");
    r = methodDeclaration_0(b, l + 1);
    r = r && methodDeclaration_1(b, l + 1);
    r = r && methodDeclaration_2(b, l + 1);
    r = r && methodDeclaration_3(b, l + 1);
    r = r && methodDeclaration_4(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    p = r; // pin = 6
    r = r && report_error_(b, parameters(b, l + 1));
    r = p && methodBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // annotation*
  private static boolean methodDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDeclaration_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "methodDeclaration_0", c)) break;
    }
    return true;
  }

  // visibility?
  private static boolean methodDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDeclaration_1")) return false;
    visibility(b, l + 1);
    return true;
  }

  // staticOrTransientModifier*
  private static boolean methodDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDeclaration_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!staticOrTransientModifier(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "methodDeclaration_2", c)) break;
    }
    return true;
  }

  // virtualOrAbstractModifier?
  private static boolean methodDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDeclaration_3")) return false;
    virtualOrAbstractModifier(b, l + 1);
    return true;
  }

  // classInterfaceOrPrimitiveType | void
  private static boolean methodDeclaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDeclaration_4")) return false;
    boolean r;
    r = classInterfaceOrPrimitiveType(b, l + 1);
    if (!r) r = consumeToken(b, VOID);
    return r;
  }

  /* ********************************************************** */
  // '<' typeList '>'
  public static boolean nonWildcardTypeArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nonWildcardTypeArguments")) return false;
    if (!nextTokenIs(b, OPERATOR_LESS_THAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OPERATOR_LESS_THAN, TYPELIST, OPERATOR_GREATER_THAN);
    exit_section_(b, m, NON_WILDCARD_TYPE_ARGUMENTS, r);
    return r;
  }

  /* ********************************************************** */
  // '<' '>'
  //     |   nonWildcardTypeArguments
  public static boolean nonWildcardTypeArgumentsOrDiamond(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nonWildcardTypeArgumentsOrDiamond")) return false;
    if (!nextTokenIs(b, OPERATOR_LESS_THAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, OPERATOR_LESS_THAN, OPERATOR_GREATER_THAN);
    if (!r) r = nonWildcardTypeArguments(b, l + 1);
    exit_section_(b, m, NON_WILDCARD_TYPE_ARGUMENTS_OR_DIAMOND, r);
    return r;
  }

  /* ********************************************************** */
  // variableModifier? classInterfaceOrPrimitiveType identifier
  public static boolean parameterDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_DEFINITION, "<parameter definition>");
    r = parameterDefinition_0(b, l + 1);
    r = r && classInterfaceOrPrimitiveType(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // variableModifier?
  private static boolean parameterDefinition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDefinition_0")) return false;
    variableModifier(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // parameterDefinition (',' parameterDefinition)*
  static boolean parameterList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterList")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterDefinition(b, l + 1);
    r = r && parameterList_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' parameterDefinition)*
  private static boolean parameterList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterList_1", c)) break;
    }
    return true;
  }

  // ',' parameterDefinition
  private static boolean parameterList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameterDefinition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '(' parameterList? ')'
  public static boolean parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && parameters_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, PARAMETERS, r);
    return r;
  }

  // parameterList?
  private static boolean parameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1")) return false;
    parameterList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Blob
  //     |   Boolean
  //     |   Date
  //     |   Datetime
  //     |   Decimal
  //     |   Double
  //     |   ID
  //     |   Integer
  //     |   Long
  //     |   String
  //     |   Time
  public static boolean primitiveType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primitiveType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMITIVE_TYPE, "<primitive type>");
    r = consumeToken(b, BLOB);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = consumeToken(b, DATETIME);
    if (!r) r = consumeToken(b, DECIMAL);
    if (!r) r = consumeToken(b, DOUBLE);
    if (!r) r = consumeToken(b, ID);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, LONG);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, TIME);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Set '<' typeArgument '>'
  public static boolean setCollection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "setCollection")) return false;
    if (!nextTokenIs(b, SET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SET_COLLECTION, null);
    r = consumeTokens(b, 1, SET, OPERATOR_LESS_THAN);
    p = r; // pin = 1
    r = r && report_error_(b, typeArgument(b, l + 1));
    r = p && consumeToken(b, OPERATOR_GREATER_THAN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // with sharing | without sharing
  public static boolean sharingModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sharingModifier")) return false;
    if (!nextTokenIs(b, "<sharing modifier>", WITH, WITHOUT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHARING_MODIFIER, "<sharing modifier>");
    r = parseTokens(b, 0, WITH, SHARING);
    if (!r) r = parseTokens(b, 0, WITHOUT, SHARING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // block
  //     |   ifStatement
  //     |   classicForStatement
  //     |   enhancedForStatement
  //     |   whileStatement
  //     |   doStatement
  //     |   tryStatement
  //     |   return expression? ';'
  //     |   throw expression ';'
  //     |   break ';'
  //     |   continue ';'
  //     |   ';'
  //     |   expression ';'
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = block(b, l + 1);
    if (!r) r = ifStatement(b, l + 1);
    if (!r) r = classicForStatement(b, l + 1);
    if (!r) r = enhancedForStatement(b, l + 1);
    if (!r) r = whileStatement(b, l + 1);
    if (!r) r = doStatement(b, l + 1);
    if (!r) r = tryStatement(b, l + 1);
    if (!r) r = statement_7(b, l + 1);
    if (!r) r = statement_8(b, l + 1);
    if (!r) r = parseTokens(b, 0, BREAK, SEMI);
    if (!r) r = parseTokens(b, 0, CONTINUE, SEMI);
    if (!r) r = consumeToken(b, SEMI);
    if (!r) r = statement_12(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // return expression? ';'
  private static boolean statement_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN);
    r = r && statement_7_1(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean statement_7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_7_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // throw expression ';'
  private static boolean statement_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THROW);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression ';'
  private static boolean statement_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_12")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // static block
  public static boolean staticBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "staticBlock")) return false;
    if (!nextTokenIs(b, STATIC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STATIC_BLOCK, null);
    r = consumeToken(b, STATIC);
    p = r; // pin = 1
    r = r && block(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // static | transient
  public static boolean staticOrTransientModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "staticOrTransientModifier")) return false;
    if (!nextTokenIs(b, "<static or transient modifier>", STATIC, TRANSIENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATIC_OR_TRANSIENT_MODIFIER, "<static or transient modifier>");
    r = consumeToken(b, STATIC);
    if (!r) r = consumeToken(b, TRANSIENT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // arguments
  //     |   '.' identifier arguments?
  public static boolean superSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superSuffix")) return false;
    if (!nextTokenIs(b, "<super suffix>", DOT, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SUPER_SUFFIX, "<super suffix>");
    r = arguments(b, l + 1);
    if (!r) r = superSuffix_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '.' identifier arguments?
  private static boolean superSuffix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superSuffix_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    r = r && superSuffix_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // arguments?
  private static boolean superSuffix_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superSuffix_1_2")) return false;
    arguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' '}'
  public static boolean triggerBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "triggerBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACE, RBRACE);
    exit_section_(b, m, TRIGGER_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // trigger identifier on identifier '(' triggerParameters ')' triggerBody
  public static boolean triggerDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "triggerDefinition")) return false;
    if (!nextTokenIs(b, TRIGGER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TRIGGER_DEFINITION, null);
    r = consumeTokens(b, 2, TRIGGER, IDENTIFIER, ON, IDENTIFIER, LPAREN);
    p = r; // pin = 2
    r = r && report_error_(b, triggerParameters(b, l + 1));
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && triggerBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // before insert |
  //         before update |
  //         before delete |
  //         after insert |
  //         after update |
  //         after delete |
  //         after undelete
  public static boolean triggerParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "triggerParameter")) return false;
    if (!nextTokenIs(b, "<trigger parameter>", AFTER, BEFORE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TRIGGER_PARAMETER, "<trigger parameter>");
    r = parseTokens(b, 0, BEFORE, INSERT);
    if (!r) r = parseTokens(b, 0, BEFORE, UPDATE);
    if (!r) r = parseTokens(b, 0, BEFORE, DELETE);
    if (!r) r = parseTokens(b, 0, AFTER, INSERT);
    if (!r) r = parseTokens(b, 0, AFTER, UPDATE);
    if (!r) r = parseTokens(b, 0, AFTER, DELETE);
    if (!r) r = parseTokens(b, 0, AFTER, UNDELETE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // triggerParameter (',' triggerParameter)*
  static boolean triggerParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "triggerParameters")) return false;
    if (!nextTokenIs(b, "", AFTER, BEFORE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = triggerParameter(b, l + 1);
    r = r && triggerParameters_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' triggerParameter)*
  private static boolean triggerParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "triggerParameters_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!triggerParameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "triggerParameters_1", c)) break;
    }
    return true;
  }

  // ',' triggerParameter
  private static boolean triggerParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "triggerParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && triggerParameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // try block (catchClause+ finallyBlock? | finallyBlock)
  public static boolean tryStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement")) return false;
    if (!nextTokenIs(b, TRY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRY);
    r = r && block(b, l + 1);
    r = r && tryStatement_2(b, l + 1);
    exit_section_(b, m, TRY_STATEMENT, r);
    return r;
  }

  // catchClause+ finallyBlock? | finallyBlock
  private static boolean tryStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tryStatement_2_0(b, l + 1);
    if (!r) r = finallyBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // catchClause+ finallyBlock?
  private static boolean tryStatement_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tryStatement_2_0_0(b, l + 1);
    r = r && tryStatement_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // catchClause+
  private static boolean tryStatement_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = catchClause(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!catchClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tryStatement_2_0_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // finallyBlock?
  private static boolean tryStatement_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2_0_1")) return false;
    finallyBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // classInterfaceOrPrimitiveType
  public static boolean typeArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeArgument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_ARGUMENT, "<type argument>");
    r = classInterfaceOrPrimitiveType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '<' '>'
  //     |   typeArguments
  public static boolean typeArgumentsOrDiamond(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeArgumentsOrDiamond")) return false;
    if (!nextTokenIs(b, "<type arguments or diamond>", OPERATOR_LESS_THAN, TYPEARGUMENTS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_ARGUMENTS_OR_DIAMOND, "<type arguments or diamond>");
    r = parseTokens(b, 0, OPERATOR_LESS_THAN, OPERATOR_GREATER_THAN);
    if (!r) r = consumeToken(b, TYPEARGUMENTS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variableDefinition (',' variableDefinition)*
  static boolean variableDeclarators(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarators")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableDefinition(b, l + 1);
    r = r && variableDeclarators_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' variableDefinition)*
  private static boolean variableDeclarators_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarators_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!variableDeclarators_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variableDeclarators_1", c)) break;
    }
    return true;
  }

  // ',' variableDefinition
  private static boolean variableDeclarators_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarators_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableDefinition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier ('=' variableInitializer)?
  public static boolean variableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_DEFINITION, null);
    r = consumeToken(b, IDENTIFIER);
    p = r; // pin = 1
    r = r && variableDefinition_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ('=' variableInitializer)?
  private static boolean variableDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition_1")) return false;
    variableDefinition_1_0(b, l + 1);
    return true;
  }

  // '=' variableInitializer
  private static boolean variableDefinition_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_ASSIGNMENT);
    r = r && variableInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // arrayInitializer
  //     |   expression
  public static boolean variableInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitializer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_INITIALIZER, "<variable initializer>");
    r = arrayInitializer(b, l + 1);
    if (!r) r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // final
  public static boolean variableModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableModifier")) return false;
    if (!nextTokenIs(b, FINAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FINAL);
    exit_section_(b, m, VARIABLE_MODIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // virtual | abstract
  public static boolean virtualOrAbstractModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "virtualOrAbstractModifier")) return false;
    if (!nextTokenIs(b, "<virtual or abstract modifier>", ABSTRACT, VIRTUAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VIRTUAL_OR_ABSTRACT_MODIFIER, "<virtual or abstract modifier>");
    r = consumeToken(b, VIRTUAL);
    if (!r) r = consumeToken(b, ABSTRACT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // public | private | global | protected
  public static boolean visibility(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "visibility")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VISIBILITY, "<visibility>");
    r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, GLOBAL);
    if (!r) r = consumeToken(b, PROTECTED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // while '(' expression ')' statement
  public static boolean whileStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whileStatement")) return false;
    if (!nextTokenIs(b, WHILE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, WHILE_STATEMENT, null);
    r = consumeTokens(b, 1, WHILE, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && statement(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: ATOM(primary_expression)
  // 1: POSTFIX(identifier_expression)
  // 2: POSTFIX(this_expression)
  // 3: POSTFIX(weird_expression)
  // 4: POSTFIX(super_expression)
  // 5: POSTFIX(generic_expression)
  // 6: BINARY(array_position_expression)
  // 7: POSTFIX(expressionList_expression)
  // 8: ATOM(instantiation_expression)
  // 9: PREFIX(cast_expression)
  // 10: POSTFIX(increment_after_expression)
  // 11: POSTFIX(decrement_after_expression)
  // 12: PREFIX(unary_negation_expression)
  // 13: PREFIX(decrement_before_expression)
  // 14: PREFIX(increment_before_expression)
  // 15: PREFIX(logical_compliment_expression)
  // 16: BINARY(multiplication_expression)
  // 17: BINARY(division_expression)
  // 18: BINARY(addition_expression)
  // 19: BINARY(subtraction_expression)
  // 20: BINARY(bitwise_shift_left_expression)
  // 21: BINARY(bitwise_shift_right_expression)
  // 22: BINARY(bitwise_shift_right_unsigned_expression)
  // 23: BINARY(greater_equal_expression)
  // 24: BINARY(less_equal_expression)
  // 25: BINARY(greater_than_expression)
  // 26: BINARY(less_than_expression)
  // 27: POSTFIX(instanceOf_expression)
  // 28: BINARY(exact_equality_expression)
  // 29: BINARY(exact_inequality_expression)
  // 30: BINARY(equality_expression)
  // 31: BINARY(inequality_expression)
  // 32: BINARY(bitwise_and_expression)
  // 33: BINARY(bitwise_exclusive_or_1expression)
  // 34: BINARY(bitwise_exclusive_or_2expression)
  // 35: BINARY(bitwise_or_expression)
  // 36: BINARY(or_expression)
  // 37: BINARY(and_expression)
  // 38: BINARY(ternary_expression)
  // 39: BINARY(assign_expression)
  // 40: BINARY(addition_assign_expression)
  // 41: BINARY(multiplication_assign_expression)
  // 42: BINARY(subtraction_assign_expression)
  // 43: BINARY(divide_assign_expression)
  // 44: BINARY(or_assign_expression)
  // 45: BINARY(and_assign_expression)
  // 46: BINARY(shift_right_assign_expression)
  // 47: BINARY(shift_right_unsigned_assign_expression)
  // 48: BINARY(shift_left_assign_expression)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = primary_expression(b, l + 1);
    if (!r) r = instantiation_expression(b, l + 1);
    if (!r) r = cast_expression(b, l + 1);
    if (!r) r = increment_before_expression(b, l + 1);
    if (!r) r = decrement_before_expression(b, l + 1);
    if (!r) r = unary_negation_expression(b, l + 1);
    if (!r) r = logical_compliment_expression(b, l + 1);
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
      if (g < 1 && parseTokensSmart(b, 0, DOT, IDENTIFIER)) {
        r = true;
        exit_section_(b, l, m, IDENTIFIER_EXPRESSION, r, true, null);
      }
      else if (g < 2 && parseTokensSmart(b, 0, DOT, THIS)) {
        r = true;
        exit_section_(b, l, m, THIS_EXPRESSION, r, true, null);
      }
      else if (g < 3 && weird_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, WEIRD_EXPRESSION, r, true, null);
      }
      else if (g < 4 && super_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, SUPER_EXPRESSION, r, true, null);
      }
      else if (g < 5 && generic_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, GENERIC_EXPRESSION, r, true, null);
      }
      else if (g < 6 && consumeTokenSmart(b, LBRACK)) {
        r = report_error_(b, expression(b, l, 6));
        r = consumeToken(b, RBRACK) && r;
        exit_section_(b, l, m, ARRAY_POSITION_EXPRESSION, r, true, null);
      }
      else if (g < 7 && expressionList_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, EXPRESSION_LIST_EXPRESSION, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, OPERATOR_INCREMENT)) {
        r = true;
        exit_section_(b, l, m, INCREMENT_AFTER_EXPRESSION, r, true, null);
      }
      else if (g < 11 && consumeTokenSmart(b, OPERATOR_DECREMENT)) {
        r = true;
        exit_section_(b, l, m, DECREMENT_AFTER_EXPRESSION, r, true, null);
      }
      else if (g < 19 && consumeTokenSmart(b, OPERATOR_SUBTRACTION)) {
        r = expression(b, l, 19);
        exit_section_(b, l, m, SUBTRACTION_EXPRESSION, r, true, null);
      }
      else if (g < 16 && consumeTokenSmart(b, OPERATOR_MULTIPLICATION)) {
        r = expression(b, l, 16);
        exit_section_(b, l, m, MULTIPLICATION_EXPRESSION, r, true, null);
      }
      else if (g < 17 && consumeTokenSmart(b, OPERATOR_DIVSION)) {
        r = expression(b, l, 17);
        exit_section_(b, l, m, DIVISION_EXPRESSION, r, true, null);
      }
      else if (g < 18 && consumeTokenSmart(b, OPERATOR_ADDITION)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, ADDITION_EXPRESSION, r, true, null);
      }
      else if (g < 20 && consumeTokenSmart(b, "<<")) {
        r = expression(b, l, 20);
        exit_section_(b, l, m, BITWISE_SHIFT_LEFT_EXPRESSION, r, true, null);
      }
      else if (g < 21 && consumeTokenSmart(b, ">>")) {
        r = expression(b, l, 21);
        exit_section_(b, l, m, BITWISE_SHIFT_RIGHT_EXPRESSION, r, true, null);
      }
      else if (g < 22 && consumeTokenSmart(b, ">>>")) {
        r = expression(b, l, 22);
        exit_section_(b, l, m, BITWISE_SHIFT_RIGHT_UNSIGNED_EXPRESSION, r, true, null);
      }
      else if (g < 23 && consumeTokenSmart(b, OPERATOR_GREATER_THAN_EQUAL)) {
        r = expression(b, l, 23);
        exit_section_(b, l, m, GREATER_EQUAL_EXPRESSION, r, true, null);
      }
      else if (g < 24 && consumeTokenSmart(b, OPERATOR_LESS_THAN_EQUAL)) {
        r = expression(b, l, 24);
        exit_section_(b, l, m, LESS_EQUAL_EXPRESSION, r, true, null);
      }
      else if (g < 25 && consumeTokenSmart(b, OPERATOR_GREATER_THAN)) {
        r = expression(b, l, 25);
        exit_section_(b, l, m, GREATER_THAN_EXPRESSION, r, true, null);
      }
      else if (g < 26 && consumeTokenSmart(b, OPERATOR_LESS_THAN)) {
        r = expression(b, l, 26);
        exit_section_(b, l, m, LESS_THAN_EXPRESSION, r, true, null);
      }
      else if (g < 27 && instanceOf_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, INSTANCE_OF_EXPRESSION, r, true, null);
      }
      else if (g < 28 && consumeTokenSmart(b, OPERATOR_EXACT_EQUALITY)) {
        r = expression(b, l, 28);
        exit_section_(b, l, m, EXACT_EQUALITY_EXPRESSION, r, true, null);
      }
      else if (g < 29 && consumeTokenSmart(b, OPERATOR_EXACT_INEQUALITY)) {
        r = expression(b, l, 29);
        exit_section_(b, l, m, EXACT_INEQUALITY_EXPRESSION, r, true, null);
      }
      else if (g < 30 && consumeTokenSmart(b, OPERATOR_EQUALITY)) {
        r = expression(b, l, 30);
        exit_section_(b, l, m, EQUALITY_EXPRESSION, r, true, null);
      }
      else if (g < 31 && consumeTokenSmart(b, OPERATOR_INEQUALITY)) {
        r = expression(b, l, 31);
        exit_section_(b, l, m, INEQUALITY_EXPRESSION, r, true, null);
      }
      else if (g < 32 && consumeTokenSmart(b, OPERATOR_BITWISE_AND)) {
        r = expression(b, l, 32);
        exit_section_(b, l, m, BITWISE_AND_EXPRESSION, r, true, null);
      }
      else if (g < 33 && consumeTokenSmart(b, OPERATOR_BITWISE_EXCLUSIVE_OR1)) {
        r = expression(b, l, 33);
        exit_section_(b, l, m, BITWISE_EXCLUSIVE_OR_1_EXPRESSION, r, true, null);
      }
      else if (g < 34 && consumeTokenSmart(b, OPERATOR_BITWISE_EXCLUSIVE_OR2)) {
        r = expression(b, l, 34);
        exit_section_(b, l, m, BITWISE_EXCLUSIVE_OR_2_EXPRESSION, r, true, null);
      }
      else if (g < 35 && consumeTokenSmart(b, OPERATOR_BITWISE_OR)) {
        r = expression(b, l, 35);
        exit_section_(b, l, m, BITWISE_OR_EXPRESSION, r, true, null);
      }
      else if (g < 36 && consumeTokenSmart(b, OPERATOR_OR)) {
        r = expression(b, l, 36);
        exit_section_(b, l, m, OR_EXPRESSION, r, true, null);
      }
      else if (g < 37 && consumeTokenSmart(b, OPERATOR_AND)) {
        r = expression(b, l, 37);
        exit_section_(b, l, m, AND_EXPRESSION, r, true, null);
      }
      else if (g < 38 && consumeTokenSmart(b, OPERATOR_TERNARY)) {
        r = report_error_(b, expression(b, l, 38));
        r = ternary_expression_1(b, l + 1) && r;
        exit_section_(b, l, m, TERNARY_EXPRESSION, r, true, null);
      }
      else if (g < 39 && consumeTokenSmart(b, OPERATOR_ASSIGNMENT)) {
        r = expression(b, l, 38);
        exit_section_(b, l, m, ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 40 && consumeTokenSmart(b, OPERATOR_ADDITION_ASSIGNMENT)) {
        r = expression(b, l, 39);
        exit_section_(b, l, m, ADDITION_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 41 && consumeTokenSmart(b, OPERATOR_MULTIPLICATION_ASSIGNMENT)) {
        r = expression(b, l, 40);
        exit_section_(b, l, m, MULTIPLICATION_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 42 && consumeTokenSmart(b, OPERATOR_SUBTRACTION_ASSIGNMENT)) {
        r = expression(b, l, 41);
        exit_section_(b, l, m, SUBTRACTION_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 43 && consumeTokenSmart(b, OPERATOR_DIVISION_ASSIGNMENT)) {
        r = expression(b, l, 42);
        exit_section_(b, l, m, DIVIDE_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 45 && consumeTokenSmart(b, OPERATOR_AND_ASSIGNMENT)) {
        r = expression(b, l, 44);
        exit_section_(b, l, m, AND_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 46 && consumeTokenSmart(b, OPERATOR_BITWISE_SHIFT_RIGHT_ASSIGNMENT)) {
        r = expression(b, l, 45);
        exit_section_(b, l, m, SHIFT_RIGHT_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 47 && consumeTokenSmart(b, OPERATOR_BITWISE_SHIFT_RIGHT_UNSIGNED_ASSIGNMENT)) {
        r = expression(b, l, 46);
        exit_section_(b, l, m, SHIFT_RIGHT_UNSIGNED_ASSIGN_EXPRESSION, r, true, null);
      }
      else if (g < 48 && consumeTokenSmart(b, OPERATOR_BITWISE_SHIFT_LEFT_ASSIGNMENT)) {
        r = expression(b, l, 47);
        exit_section_(b, l, m, SHIFT_LEFT_ASSIGN_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // '(' expression ')'
  //     |   this
  //     |   super
  //     |   literal
  //     |   identifier
  //     |   classInterfaceOrPrimitiveType '.' CLASS_KEYWORD
  //     |   void '.' CLASS_KEYWORD
  //     |   nonWildcardTypeArguments (explicitGenericInvocationSuffix | this arguments)
  public static boolean primary_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMARY_EXPRESSION, "<primary expression>");
    r = primary_expression_0(b, l + 1);
    if (!r) r = consumeTokenSmart(b, THIS);
    if (!r) r = consumeTokenSmart(b, SUPER);
    if (!r) r = literal(b, l + 1);
    if (!r) r = consumeTokenSmart(b, IDENTIFIER);
    if (!r) r = primary_expression_5(b, l + 1);
    if (!r) r = parseTokensSmart(b, 0, VOID, DOT, CLASS_KEYWORD);
    if (!r) r = primary_expression_7(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '(' expression ')'
  private static boolean primary_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // classInterfaceOrPrimitiveType '.' CLASS_KEYWORD
  private static boolean primary_expression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expression_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classInterfaceOrPrimitiveType(b, l + 1);
    r = r && consumeTokensSmart(b, 0, DOT, CLASS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // nonWildcardTypeArguments (explicitGenericInvocationSuffix | this arguments)
  private static boolean primary_expression_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expression_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = nonWildcardTypeArguments(b, l + 1);
    r = r && primary_expression_7_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // explicitGenericInvocationSuffix | this arguments
  private static boolean primary_expression_7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expression_7_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = explicitGenericInvocationSuffix(b, l + 1);
    if (!r) r = primary_expression_7_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // this arguments
  private static boolean primary_expression_7_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_expression_7_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THIS);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '.' new nonWildcardTypeArguments? innerCreator
  private static boolean weird_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "weird_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, DOT, NEW);
    r = r && weird_expression_0_2(b, l + 1);
    r = r && innerCreator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nonWildcardTypeArguments?
  private static boolean weird_expression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "weird_expression_0_2")) return false;
    nonWildcardTypeArguments(b, l + 1);
    return true;
  }

  // '.' super superSuffix
  private static boolean super_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "super_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, DOT, SUPER);
    r = r && superSuffix(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '.' explicitGenericInvocation
  private static boolean generic_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, DOT);
    r = r && explicitGenericInvocation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '(' expressionList? ')'
  private static boolean expressionList_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && expressionList_expression_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // expressionList?
  private static boolean expressionList_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList_expression_0_1")) return false;
    expressionList(b, l + 1);
    return true;
  }

  // new creator
  public static boolean instantiation_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instantiation_expression")) return false;
    if (!nextTokenIsSmart(b, NEW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, NEW);
    r = r && creator(b, l + 1);
    exit_section_(b, m, INSTANTIATION_EXPRESSION, r);
    return r;
  }

  public static boolean cast_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cast_expression")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = cast_expression_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 9);
    exit_section_(b, l, m, CAST_EXPRESSION, r, p, null);
    return r || p;
  }

  // '(' classInterfaceOrPrimitiveType ')'
  private static boolean cast_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cast_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && classInterfaceOrPrimitiveType(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean increment_before_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "increment_before_expression")) return false;
    if (!nextTokenIsSmart(b, OPERATOR_INCREMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, OPERATOR_INCREMENT);
    p = r;
    r = p && expression(b, l, 14);
    exit_section_(b, l, m, INCREMENT_BEFORE_EXPRESSION, r, p, null);
    return r || p;
  }

  public static boolean decrement_before_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "decrement_before_expression")) return false;
    if (!nextTokenIsSmart(b, OPERATOR_DECREMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, OPERATOR_DECREMENT);
    p = r;
    r = p && expression(b, l, 13);
    exit_section_(b, l, m, DECREMENT_BEFORE_EXPRESSION, r, p, null);
    return r || p;
  }

  public static boolean unary_negation_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_negation_expression")) return false;
    if (!nextTokenIsSmart(b, OPERATOR_SUBTRACTION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, OPERATOR_SUBTRACTION);
    p = r;
    r = p && expression(b, l, 12);
    exit_section_(b, l, m, UNARY_NEGATION_EXPRESSION, r, p, null);
    return r || p;
  }

  public static boolean logical_compliment_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "logical_compliment_expression")) return false;
    if (!nextTokenIsSmart(b, OPERATOR_NOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, OPERATOR_NOT);
    p = r;
    r = p && expression(b, l, 15);
    exit_section_(b, l, m, LOGICAL_COMPLIMENT_EXPRESSION, r, p, null);
    return r || p;
  }

  // instanceof classInterfaceOrPrimitiveType
  private static boolean instanceOf_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOf_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, INSTANCEOF);
    r = r && classInterfaceOrPrimitiveType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ':' expression
  private static boolean ternary_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_COLON);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

}
