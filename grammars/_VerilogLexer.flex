package com.activemesa.hdline.verilog.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

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

WHITE_SPACE=[ \t\n\x0B\f\r]+
LINE_COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/\\*"([^*]|\\*+[^*/])*\\*+"/"
STRING=\"([^\"\\\r\n]|\\.)*\"
SIZED_NUMBER=([0-9][0-9_]*)?\'[sS]?[dDhHoObB][0-9a-fA-F_xXzZ?]+
UNSIZED_NUMBER=[0-9][0-9_]*
ESCAPED_IDENTIFIER=\\[^ \t\n\r\f]+
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_$]*
DIRECTIVE=\`[a-zA-Z_][a-zA-Z0-9_$]*

%%
<YYINITIAL> {
  {WHITE_SPACE}          { return WHITE_SPACE; }
  {LINE_COMMENT}         { return LINE_COMMENT; }
  {BLOCK_COMMENT}        { return BLOCK_COMMENT; }
  {STRING}               { return STRING; }
  {SIZED_NUMBER}         { return NUMBER; }
  {UNSIZED_NUMBER}       { return NUMBER; }
  {ESCAPED_IDENTIFIER}   { return ESCAPED_IDENTIFIER; }
  {DIRECTIVE}            { return DIRECTIVE; }

  "module"               { return MODULE; }
  "endmodule"            { return ENDMODULE; }
  "input"                { return INPUT; }
  "output"               { return OUTPUT; }
  "wire"                 { return WIRE; }
  "reg"                  { return REG; }
  "assign"               { return ASSIGN; }
  "always"               { return ALWAYS; }
  "initial"              { return INITIAL; }
  "begin"                { return BEGIN; }
  "end"                  { return END; }

  {IDENTIFIER}           { return IDENTIFIER; }

  "("                    { return LPAREN; }
  ")"                    { return RPAREN; }
  "["                    { return LBRACKET; }
  "]"                    { return RBRACKET; }
  "{"                    { return LBRACE; }
  "}"                    { return RBRACE; }
  ";"                    { return SEMICOLON; }
  ":"                    { return COLON; }
  ","                    { return COMMA; }
  "."                    { return DOT; }
  "@"                    { return AT; }
  "#"                    { return HASH; }
  "?"                    { return QUESTION; }
  "="                    { return EQ; }
  "+"                    { return PLUS; }
  "-"                    { return MINUS; }
  "*"                    { return STAR; }
  "/"                    { return SLASH; }
  "%"                    { return PERCENT; }
  "&"                    { return AMP; }
  "|"                    { return PIPE; }
  "^"                    { return CARET; }
  "~"                    { return TILDE; }
}

[^] { return BAD_CHARACTER; }
