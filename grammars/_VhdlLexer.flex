package com.activemesa.hdline.vhdl.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static generated.VhdlGeneratedTypes.*;

%%

%{
  public _VhdlLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _VhdlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%ignorecase

WHITE_SPACE=[ \t\n\x0B\f\r]+
COMMENT="--"[^\r\n]*
STRING=\"([^\"\r\n]|\"\")*\"
CHARACTER_LITERAL=\'[^\'\r\n]\'
NUMBER=[0-9][0-9_]*
EXTENDED_IDENTIFIER=\\[^\r\n\\]+\\
IDENTIFIER=[a-zA-Z]([a-zA-Z0-9]|_(?=[a-zA-Z0-9]))*

%%
<YYINITIAL> {
  {WHITE_SPACE}          { return WHITE_SPACE; }
  {COMMENT}              { return COMMENT; }
  {STRING}               { return STRING; }
  {CHARACTER_LITERAL}    { return CHARACTER_LITERAL; }
  {NUMBER}               { return NUMBER; }
  {EXTENDED_IDENTIFIER}  { return EXTENDED_IDENTIFIER; }

  "entity"               { return ENTITY; }
  "architecture"         { return ARCHITECTURE; }
  "is"                   { return IS; }
  "begin"                { return BEGIN; }
  "end"                  { return END; }
  "port"                 { return PORT; }
  "signal"               { return SIGNAL; }
  "process"              { return PROCESS; }
  "of"                   { return OF; }
  "in"                   { return IN; }
  "out"                  { return OUT; }
  "downto"               { return DOWNTO; }
  "to"                   { return TO; }
  "std_logic"            { return STD_LOGIC; }
  "std_logic_vector"     { return STD_LOGIC_VECTOR; }

  {IDENTIFIER}           { return IDENTIFIER; }

  "<="                   { return ASSIGN; }
  "=>"                   { return ARROW; }
  "("                    { return LPAREN; }
  ")"                    { return RPAREN; }
  ":"                    { return COLON; }
  ";"                    { return SEMICOLON; }
  ","                    { return COMMA; }
  "."                    { return DOT; }
  "+"                    { return PLUS; }
  "-"                    { return MINUS; }
  "*"                    { return STAR; }
  "/"                    { return SLASH; }
  "&"                    { return AMP; }
}

[^] { return BAD_CHARACTER; }
