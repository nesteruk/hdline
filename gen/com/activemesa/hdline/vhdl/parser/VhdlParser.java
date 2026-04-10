// This is a generated file. Not intended for manual editing.
package com.activemesa.hdline.vhdl.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

import static com.intellij.lang.parser.GeneratedParserUtilBase.TRUE_CONDITION;
import static com.intellij.lang.parser.GeneratedParserUtilBase.adapt_builder_;
import static com.intellij.lang.parser.GeneratedParserUtilBase.consumeToken;
import static com.intellij.lang.parser.GeneratedParserUtilBase.enter_section_;
import static com.intellij.lang.parser.GeneratedParserUtilBase.exit_section_;
import static generated.VhdlGeneratedTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class VhdlParser implements PsiParser, LightPsiParser {

  @Override
  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  @Override
  public void parseLight(IElementType t, PsiBuilder b) {
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, 0, null);
    designFile(b);
    exit_section_(b, 0, m, t, true, true, TRUE_CONDITION);
  }

  private void designFile(PsiBuilder b) {
    while (!b.eof()) {
      if (!entityDeclaration(b) && !architectureBody(b)) {
        b.advanceLexer();
      }
    }
  }

  private boolean entityDeclaration(PsiBuilder b) {
    if (b.getTokenType() != ENTITY) {
      return false;
    }
    consumeToken(b, ENTITY);
    if (!consumeIdentifier(b)) {
      return false;
    }
    consumeToken(b, IS);
    if (b.getTokenType() == PORT) {
      portClause(b);
    }
    consumeToken(b, END);
    if (b.getTokenType() == ENTITY) {
      consumeToken(b, ENTITY);
    }
    consumeIdentifier(b);
    consumeToken(b, SEMICOLON);
    return true;
  }

  private void portClause(PsiBuilder b) {
    consumeToken(b, PORT);
    consumeToken(b, LPAREN);
    if (interfaceSignalDeclaration(b)) {
      while (b.getTokenType() == SEMICOLON) {
        consumeToken(b, SEMICOLON);
        if (!interfaceSignalDeclaration(b)) {
          break;
        }
      }
    }
    consumeToken(b, RPAREN);
    consumeToken(b, SEMICOLON);
  }

  private boolean interfaceSignalDeclaration(PsiBuilder b) {
    if (!identifierList(b)) {
      return false;
    }
    consumeToken(b, COLON);
    if (b.getTokenType() == IN || b.getTokenType() == OUT) {
      b.advanceLexer();
    }
    subtypeIndication(b);
    return true;
  }

  private boolean architectureBody(PsiBuilder b) {
    if (b.getTokenType() != ARCHITECTURE) {
      return false;
    }
    consumeToken(b, ARCHITECTURE);
    if (!consumeIdentifier(b)) {
      return false;
    }
    consumeToken(b, OF);
    consumeIdentifier(b);
    consumeToken(b, IS);
    while (signalDeclaration(b)) {
      // consume declarations
    }
    consumeToken(b, BEGIN);
    while (!b.eof() && b.getTokenType() != END) {
      if (!concurrentStatement(b)) {
        b.advanceLexer();
      }
    }
    consumeToken(b, END);
    if (b.getTokenType() == ARCHITECTURE) {
      consumeToken(b, ARCHITECTURE);
    }
    consumeIdentifier(b);
    consumeToken(b, SEMICOLON);
    return true;
  }

  private boolean signalDeclaration(PsiBuilder b) {
    if (b.getTokenType() != SIGNAL) {
      return false;
    }
    consumeToken(b, SIGNAL);
    if (!identifierList(b)) {
      return false;
    }
    consumeToken(b, COLON);
    subtypeIndication(b);
    consumeToken(b, SEMICOLON);
    return true;
  }

  private boolean concurrentStatement(PsiBuilder b) {
    return signalAssignment(b) || processStatement(b);
  }

  private boolean processStatement(PsiBuilder b) {
    if (b.getTokenType() != PROCESS) {
      return false;
    }
    consumeToken(b, PROCESS);
    consumeToken(b, BEGIN);
    while (!b.eof() && b.getTokenType() != END) {
      if (!signalAssignment(b)) {
        b.advanceLexer();
      }
    }
    consumeToken(b, END);
    if (b.getTokenType() == PROCESS) {
      consumeToken(b, PROCESS);
    }
    consumeToken(b, SEMICOLON);
    return true;
  }

  private boolean signalAssignment(PsiBuilder b) {
    if (!consumeIdentifier(b)) {
      return false;
    }
    consumeToken(b, ASSIGN);
    expression(b);
    consumeToken(b, SEMICOLON);
    return true;
  }

  private boolean identifierList(PsiBuilder b) {
    if (!consumeIdentifier(b)) {
      return false;
    }
    while (b.getTokenType() == COMMA) {
      consumeToken(b, COMMA);
      consumeIdentifier(b);
    }
    return true;
  }

  private void subtypeIndication(PsiBuilder b) {
    IElementType token = b.getTokenType();
    if (token == STD_LOGIC || token == IDENTIFIER || token == EXTENDED_IDENTIFIER) {
      b.advanceLexer();
      if (b.getTokenType() == LPAREN) {
        consumeToken(b, LPAREN);
        expression(b);
        if (b.getTokenType() == DOWNTO || b.getTokenType() == TO) {
          b.advanceLexer();
          expression(b);
        }
        consumeToken(b, RPAREN);
      }
      return;
    }
    if (token == STD_LOGIC_VECTOR) {
      b.advanceLexer();
      consumeToken(b, LPAREN);
      expression(b);
      if (b.getTokenType() == DOWNTO || b.getTokenType() == TO) {
        b.advanceLexer();
        expression(b);
      }
      consumeToken(b, RPAREN);
    }
  }

  private void expression(PsiBuilder b) {
    primary(b);
    while (isBinaryOperator(b.getTokenType())) {
      b.advanceLexer();
      primary(b);
    }
  }

  private void primary(PsiBuilder b) {
    IElementType token = b.getTokenType();
    if (token == IDENTIFIER || token == EXTENDED_IDENTIFIER || token == NUMBER
        || token == STRING || token == CHARACTER_LITERAL) {
      b.advanceLexer();
      return;
    }
    if (token == LPAREN) {
      consumeToken(b, LPAREN);
      expression(b);
      consumeToken(b, RPAREN);
    }
  }

  private boolean consumeIdentifier(PsiBuilder b) {
    IElementType token = b.getTokenType();
    if (token == IDENTIFIER || token == EXTENDED_IDENTIFIER) {
      b.advanceLexer();
      return true;
    }
    return false;
  }

  private boolean isBinaryOperator(IElementType token) {
    return token == PLUS || token == MINUS || token == STAR || token == SLASH || token == AMP;
  }
}
