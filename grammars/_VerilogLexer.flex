package com.activemesa.verilog.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.grammar.psi.BnfTypes;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static generated.GeneratedTypes.*;

%%

%{
  public _VerilogLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _VerilogLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

WHITESPACE=[ \t\n\x0B\f\r]+
STRING=\"([^\"]|\\\")*\"
STRING_UNCLOSED=\"([^\"]|\\\")*
NUMBER=[0-9][0-9]?r[\da-zA-Z]+|M?[0-9]+(\.[0-9]*([eE][0-9]+)?)?
RATIO=[0-9]+"/"[0-9]+
CHAR=\\(u[0-9]{4}|newline|space|backspace|return|.)
BOOL=true|false
SYM=[\w.<>$%&=*/+\-!?_'[^\d]][\w.<>$%&=*/+\-!?_']*(:[\w<>$%&=*/+\-!?_'])+)?

%%
<YYINITIAL> {
  {WHITE_SPACE}          { return WHITE_SPACE; }

  "nil"                  { return NIL; }
  "#^"                   { return SHARP_HAT; }
  "#'"                   { return SHARP_QUOTE; }
  "#_"                   { return SHARP_COMMENT; }
  "#?"                   { return SHARP_QMARK; }
  "#?@"                  { return SHARP_QMARK_AT; }
  "#="                   { return SHARP_EQ; }
  "#:"                   { return SHARP_NS; }
  "("                    { return PAREN1; }
  ")"                    { return PAREN2; }
  "["                    { return BRACKET1; }
  "]"                    { return BRACKET2; }
  "{"                    { return BRACE1; }
  "}"                    { return BRACE2; }
  ":"                    { return COLON; }
  "::"                   { return COLONCOLON; }
  ","                    { return COMMA; }
  "'"                    { return QUOTE; }
  "`"                    { return SYNTAX_QUOTE; }
  "#"                    { return SHARP; }
  "^"                    { return HAT; }
  "~"                    { return TILDE; }
  "~@"                   { return TILDE_AT; }
  "@"                    { return AT; }
  "."                    { return DOT; }
  ".-"                   { return DOTDASH; }
  "/"                    { return SLASH; }

  {WHITESPACE}           { return WHITESPACE; }
  {STRING}               { return STRING; }
  {STRING_UNCLOSED}      { return STRING_UNCLOSED; }
  {NUMBER}               { return NUMBER; }
  {RATIO}                { return RATIO; }
  {CHAR}                 { return CHAR; }
  {BOOL}                 { return BOOL; }
  {SYM}                  { return SYM; }

}

[^] { return BAD_CHARACTER; }
