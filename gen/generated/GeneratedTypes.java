// This is a generated file. Not intended for manual editing.
package generated;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface GeneratedTypes {

  IElementType ALWAYS = new IElementType("always", null);
  IElementType AMP = new IElementType("&", null);
  IElementType ASSIGN = new IElementType("assign", null);
  IElementType AT = new IElementType("@", null);
  IElementType BEGIN = new IElementType("begin", null);
  IElementType BLOCK_COMMENT = new IElementType("block_comment", null);
  IElementType CARET = new IElementType("^", null);
  IElementType COLON = new IElementType(":", null);
  IElementType COMMA = new IElementType(",", null);
  IElementType DIRECTIVE = new IElementType("directive", null);
  IElementType DOT = new IElementType(".", null);
  IElementType END = new IElementType("end", null);
  IElementType ENDMODULE = new IElementType("endmodule", null);
  IElementType EQ = new IElementType("=", null);
  IElementType ESCAPED_IDENTIFIER = new IElementType("escaped_identifier", null);
  IElementType HASH = new IElementType("#", null);
  IElementType IDENTIFIER = new IElementType("identifier", null);
  IElementType INITIAL = new IElementType("initial", null);
  IElementType INPUT = new IElementType("input", null);
  IElementType LBRACE = new IElementType("{", null);
  IElementType LBRACKET = new IElementType("[", null);
  IElementType LINE_COMMENT = new IElementType("line_comment", null);
  IElementType LPAREN = new IElementType("(", null);
  IElementType MINUS = new IElementType("-", null);
  IElementType MODULE = new IElementType("module", null);
  IElementType NUMBER = new IElementType("number", null);
  IElementType OUTPUT = new IElementType("output", null);
  IElementType PERCENT = new IElementType("%", null);
  IElementType PIPE = new IElementType("|", null);
  IElementType PLUS = new IElementType("+", null);
  IElementType QUESTION = new IElementType("?", null);
  IElementType RBRACE = new IElementType("}", null);
  IElementType RBRACKET = new IElementType("]", null);
  IElementType REG = new IElementType("reg", null);
  IElementType RPAREN = new IElementType(")", null);
  IElementType SEMICOLON = new IElementType(";", null);
  IElementType SLASH = new IElementType("/", null);
  IElementType STAR = new IElementType("*", null);
  IElementType STRING = new IElementType("string", null);
  IElementType TILDE = new IElementType("~", null);
  IElementType WIRE = new IElementType("wire", null);

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
