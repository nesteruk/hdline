// This is a generated file. Not intended for manual editing.
package generated;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface VhdlGeneratedTypes {

  IElementType AMP = new IElementType("&", null);
  IElementType ARCHITECTURE = new IElementType("architecture", null);
  IElementType ARROW = new IElementType("=>", null);
  IElementType ASSIGN = new IElementType("<=", null);
  IElementType BEGIN = new IElementType("begin", null);
  IElementType CHARACTER_LITERAL = new IElementType("character_literal", null);
  IElementType COLON = new IElementType(":", null);
  IElementType COMMENT = new IElementType("comment", null);
  IElementType COMMA = new IElementType(",", null);
  IElementType DOT = new IElementType(".", null);
  IElementType DOWNTO = new IElementType("downto", null);
  IElementType END = new IElementType("end", null);
  IElementType ENTITY = new IElementType("entity", null);
  IElementType EXTENDED_IDENTIFIER = new IElementType("extended_identifier", null);
  IElementType IDENTIFIER = new IElementType("identifier", null);
  IElementType IN = new IElementType("in", null);
  IElementType IS = new IElementType("is", null);
  IElementType LPAREN = new IElementType("(", null);
  IElementType MINUS = new IElementType("-", null);
  IElementType NUMBER = new IElementType("number", null);
  IElementType OF = new IElementType("of", null);
  IElementType OUT = new IElementType("out", null);
  IElementType PLUS = new IElementType("+", null);
  IElementType PORT = new IElementType("port", null);
  IElementType PROCESS = new IElementType("process", null);
  IElementType RPAREN = new IElementType(")", null);
  IElementType SEMICOLON = new IElementType(";", null);
  IElementType SIGNAL = new IElementType("signal", null);
  IElementType SLASH = new IElementType("/", null);
  IElementType STAR = new IElementType("*", null);
  IElementType STD_LOGIC = new IElementType("std_logic", null);
  IElementType STD_LOGIC_VECTOR = new IElementType("std_logic_vector", null);
  IElementType STRING = new IElementType("string", null);
  IElementType TO = new IElementType("to", null);

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
