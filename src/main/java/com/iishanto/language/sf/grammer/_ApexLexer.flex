package com.iishanto.language.sf.grammer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.iishanto.language.sf.apex.psi.ApexTypes.*;

%%

%{
  public _ApexLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _ApexLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT=("//".*|("/"\*[^]]*?\*"/"))
WHITE_SPACE=[ \t\n\x0B\f\r]+
NUMBER=-?[0-9]+(\.?[0-9])*L?
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_$]*
STRING='([^'\\]|\\\\.)*'

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  "virtual"                   { return VIRTUAL_KEYWORD; }
  "abstract"                  { return ABSTRACT_KEYWORD; }
  "with"                      { return WITH_KEYWORD; }
  "void"                      { return VOID_KEYWORD; }
  "interface"                 { return INTERFACE_KEYWORD; }
  "class"                     { return CLASS_KEYWORD; }
  "sharing"                   { return SHARING_KEYWORD; }
  "without"                   { return WITHOUT_KEYWORD; }
  "inherited"                 { return INHERITED_KEYWORD; }
  "public"                    { return PUBLIC_KEYWORD; }
  "private"                   { return PRIVATE_KEYWORD; }
  "global"                    { return GLOBAL_KEYWORD; }
  "protected"                 { return PROTECTED_KEYWORD; }
  "final"                     { return FINAL_KEYWORD; }
  "static"                    { return STATIC_KEYWORD; }
  "do"                        { return DO_KEYWORD; }
  "implements"                { return IMPLEMENTS_KEYWORD; }
  "extends"                   { return EXTENDS_KEYWORD; }
  "get"                       { return GET_KEYWORD; }
  "set"                       { return SET_KEYWORD; }
  "true"                      { return TRUE_LITERAL; }
  "false"                     { return FALSE_LITERAL; }
  "new"                       { return NEW_KEYWORD; }
  "if"                        { return IF_KEYWORD; }
  "else"                      { return ELSE_KEYWORD; }
  "when"                      { return WHEN_KEYWORD; }
  "for"                       { return FOR_KEYWORD; }
  "while"                     { return WHILE_KEYWORD; }
  "try"                       { return TRY_KEYWORD; }
  "catch"                     { return CATCH_KEYWORD; }
  "finally"                   { return FINALLY_KEYWORD; }
  "return"                    { return RETURN_KEYWORD; }
  "switch"                    { return SWITCH_KEYWORD; }
  "case"                      { return CASE_KEYWORD; }
  "default"                   { return DEFAULT_KEYWORD; }
  "FROM"                      { return FROM_KEYWORD; }
  "SELECT"                    { return SELECT_KEYWORD; }
  "WHERE"                     { return WHERE_KEYWORD; }
  "ORDER BY"                  { return ORDER_BY_KEYWORD; }
  "LIMIT"                     { return LIMIT_KEYWORD; }
  "OFFSET"                    { return OFFSET_KEYWORD; }
  "GROUP BY"                  { return GROUP_BY_KEYWORD; }
  "HAVING"                    { return HAVING_KEYWORD; }
  "DISTINCT"                  { return DISTINCT_KEYWORD; }
  "FOR"                       { return SOQL_FOR_KEYWORD; }
  "UPDATE"                    { return UPDATE_KEYWORD; }
  "INSERT"                    { return INSERT_KEYWORD; }
  "DELETE"                    { return DELETE_KEYWORD; }
  "UPSERT"                    { return UPSERT_KEYWORD; }
  "MERGE"                     { return MERGE_KEYWORD; }
  "enum"                      { return ENUM_KEYWORD; }
  "break"                     { return BREAK_KEYWORD; }
  "continue"                  { return CONTINUE_KEYWORD; }
  "throw"                     { return THROW_KEYWORD; }
  "this"                      { return THIS_KEYWORD; }
  "super"                     { return SUPER_KEYWORD; }
  "on"                        { return ON_KEYWORD; }
  "override"                  { return OVERRIDE_KEYWORD; }
  "{"                         { return LBRACE; }
  "}"                         { return RBRACE; }
  "("                         { return LPAREN; }
  ")"                         { return RPAREN; }
  "["                         { return LBRACKET; }
  "]"                         { return RBRACKET; }
  ","                         { return COMMA; }
  "."                         { return DOT; }
  ":"                         { return COLON; }
  "=="                        { return EQUAL; }
  "!=="                       { return NOT_EQUAL_EQAL; }
  "!="                        { return NOT_EQUAL; }
  ">="                        { return GREATER_EQUAL; }
  "<="                        { return LESS_EQUAL; }
  "++"                        { return INCREMENT; }
  "--"                        { return DECREMENT; }
  ">"                         { return GREATER; }
  "<"                         { return LESS; }
  "+"                         { return PLUS; }
  "-"                         { return MINUS; }
  "*"                         { return MULTIPLY; }
  "/"                         { return DIVIDE; }
  "&&"                        { return AND; }
  "||"                        { return OR; }
  "!"                         { return NOT; }
  "&"                         { return BITWISE_AND; }
  "|"                         { return BITWISE_OR; }
  "^"                         { return BITWISE_XOR; }
  "="                         { return ASSIGN; }
  ";"                         { return SEMICOLON; }
  "@"                         { return AT; }
  "[]"                        { return ARRAY_BRACKET; }
  "ApexTrigger"               { return APEXTRIGGER; }
  "ApexInterface"             { return APEXINTERFACE; }
  "ApexScript"                { return APEXSCRIPT; }
  "ApexEnum"                  { return APEXENUM; }
  "ApexClassOrInterface"      { return APEXCLASSORINTERFACE; }
  "ConstructorDefinition"     { return CONSTRUCTORDEFINITION; }
  "SOSL"                      { return SOSL; }

  {COMMENT}                   { return COMMENT; }
  {WHITE_SPACE}               { return WHITE_SPACE; }
  {NUMBER}                    { return NUMBER; }
  {IDENTIFIER}                { return IDENTIFIER; }
  {STRING}                    { return STRING; }

}

[^] { return BAD_CHARACTER; }
