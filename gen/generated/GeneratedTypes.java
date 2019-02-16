// This is a generated file. Not intended for manual editing.
package generated;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.activemesa.verilog.psi.impl.*;

public interface GeneratedTypes {


  IElementType AT = new IElementType("@", null);
  IElementType BOOL = new IElementType("bool", null);
  IElementType BRACE1 = new IElementType("{", null);
  IElementType BRACE2 = new IElementType("}", null);
  IElementType BRACKET1 = new IElementType("[", null);
  IElementType BRACKET2 = new IElementType("]", null);
  IElementType CHAR = new IElementType("char", null);
  IElementType COLON = new IElementType(":", null);
  IElementType COLONCOLON = new IElementType("::", null);
  IElementType COMMA = new IElementType(",", null);
  IElementType DOT = new IElementType(".", null);
  IElementType DOTDASH = new IElementType(".-", null);
  IElementType E = new IElementType("E", null);
  IElementType HAT = new IElementType("^", null);
  IElementType NIL = new IElementType("nil", null);
  IElementType NUMBER = new IElementType("number", null);
  IElementType PAREN1 = new IElementType("(", null);
  IElementType PAREN2 = new IElementType(")", null);
  IElementType QUOTE = new IElementType("'", null);
  IElementType RATIO = new IElementType("ratio", null);
  IElementType SHARP = new IElementType("#", null);
  IElementType SHARP_COMMENT = new IElementType("#_", null);
  IElementType SHARP_EQ = new IElementType("#=", null);
  IElementType SHARP_HAT = new IElementType("#^", null);
  IElementType SHARP_NS = new IElementType("#:", null);
  IElementType SHARP_QMARK = new IElementType("#?", null);
  IElementType SHARP_QMARK_AT = new IElementType("#?@", null);
  IElementType SHARP_QUOTE = new IElementType("#'", null);
  IElementType SLASH = new IElementType("/", null);
  IElementType STRING = new IElementType("string", null);
  IElementType STRING_UNCLOSED = new IElementType("string_unclosed", null);
  IElementType SYM = new IElementType("sym", null);
  IElementType SYNTAX_QUOTE = new IElementType("`", null);
  IElementType TILDE = new IElementType("~", null);
  IElementType TILDE_AT = new IElementType("~@", null);

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
