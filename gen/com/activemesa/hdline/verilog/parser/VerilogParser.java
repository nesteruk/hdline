// This is a generated file. Not intended for manual editing.
package com.activemesa.hdline.verilog.parser;

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
import static generated.GeneratedTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class VerilogParser implements PsiParser, LightPsiParser {

  @Override
  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  @Override
  public void parseLight(IElementType t, PsiBuilder b) {
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, 0, null);
    sourceText(b);
    exit_section_(b, 0, m, t, true, true, TRUE_CONDITION);
  }

  private void sourceText(PsiBuilder b) {
    while (!b.eof()) {
      if (!moduleDeclaration(b)) {
        b.advanceLexer();
      }
    }
  }

  private boolean moduleDeclaration(PsiBuilder b) {
    if (b.getTokenType() != MODULE) {
      return false;
    }

    consumeToken(b, MODULE);
    if (!consumeIdentifier(b)) {
      return false;
    }

    if (b.getTokenType() == LPAREN) {
      portList(b);
    }

    consumeToken(b, SEMICOLON);

    while (!b.eof() && b.getTokenType() != ENDMODULE) {
      if (!moduleItem(b)) {
        b.advanceLexer();
      }
    }

    consumeToken(b, ENDMODULE);
    return true;
  }

  private void portList(PsiBuilder b) {
    consumeToken(b, LPAREN);
    if (consumeIdentifier(b)) {
      while (b.getTokenType() == COMMA) {
        consumeToken(b, COMMA);
        consumeIdentifier(b);
      }
    }
    consumeToken(b, RPAREN);
  }

  private boolean moduleItem(PsiBuilder b) {
    IElementType token = b.getTokenType();
    if (token == INPUT || token == OUTPUT) {
      return identifierListStatement(b);
    }
    if (token == WIRE) {
      return identifierListStatement(b);
    }
    if (token == REG) {
      return identifierListStatement(b);
    }
    if (token == ASSIGN) {
      return assignStatement(b);
    }
    if (token == INITIAL || token == ALWAYS) {
      return proceduralBlock(b);
    }
    return false;
  }

  private boolean identifierListStatement(PsiBuilder b) {
    b.advanceLexer();
    if (!consumeIdentifier(b)) {
      return false;
    }
    while (b.getTokenType() == COMMA) {
      consumeToken(b, COMMA);
      consumeIdentifier(b);
    }
    consumeToken(b, SEMICOLON);
    return true;
  }

  private boolean assignStatement(PsiBuilder b) {
    consumeToken(b, ASSIGN);
    if (!assignment(b)) {
      return false;
    }
    while (b.getTokenType() == COMMA) {
      consumeToken(b, COMMA);
      assignment(b);
    }
    consumeToken(b, SEMICOLON);
    return true;
  }

  private boolean assignment(PsiBuilder b) {
    if (!consumeIdentifier(b)) {
      return false;
    }
    consumeToken(b, EQ);
    expression(b);
    return true;
  }

  private boolean proceduralBlock(PsiBuilder b) {
    b.advanceLexer();
    if (b.getTokenType() == BEGIN) {
      consumeToken(b, BEGIN);
      while (!b.eof() && b.getTokenType() != END) {
        if (!assignmentStatement(b)) {
          b.advanceLexer();
        }
      }
      consumeToken(b, END);
      return true;
    }
    return assignmentStatement(b);
  }

  private boolean assignmentStatement(PsiBuilder b) {
    if (!assignment(b)) {
      return false;
    }
    consumeToken(b, SEMICOLON);
    return true;
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
    if (token == IDENTIFIER || token == ESCAPED_IDENTIFIER || token == NUMBER || token == STRING) {
      b.advanceLexer();
      return;
    }
    if (token == LPAREN) {
      consumeToken(b, LPAREN);
      expression(b);
      consumeToken(b, RPAREN);
      return;
    }
    if (token == LBRACE) {
      consumeToken(b, LBRACE);
      expression(b);
      while (b.getTokenType() == COMMA) {
        consumeToken(b, COMMA);
        expression(b);
      }
      consumeToken(b, RBRACE);
    }
  }

  private boolean consumeIdentifier(PsiBuilder b) {
    IElementType token = b.getTokenType();
    if (token == IDENTIFIER || token == ESCAPED_IDENTIFIER) {
      b.advanceLexer();
      return true;
    }
    return false;
  }

  private boolean isBinaryOperator(IElementType token) {
    return token == PLUS || token == MINUS || token == STAR || token == SLASH
        || token == PERCENT || token == AMP || token == PIPE || token == CARET;
  }
}
