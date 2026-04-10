package com.activemesa.hdline.verilog.lang

import com.intellij.lexer.LexerBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import generated.GeneratedTypes

class VerilogLexer : LexerBase() {
  private var buffer: CharSequence = ""
  private var endOffset: Int = 0
  private var tokenStart: Int = 0
  private var tokenEnd: Int = 0
  private var tokenType: IElementType? = null

  override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
    this.buffer = buffer
    this.endOffset = endOffset
    tokenStart = startOffset
    tokenEnd = startOffset
    tokenType = null
    locateToken()
  }

  override fun getState(): Int = 0

  override fun getTokenType(): IElementType? = tokenType

  override fun getTokenStart(): Int = tokenStart

  override fun getTokenEnd(): Int = tokenEnd

  override fun advance() {
    tokenStart = tokenEnd
    locateToken()
  }

  override fun getBufferSequence(): CharSequence = buffer

  override fun getBufferEnd(): Int = endOffset

  private fun locateToken() {
    if (tokenStart >= endOffset) {
      tokenType = null
      tokenEnd = tokenStart
      return
    }

    val c = buffer[tokenStart]

    if (c.isWhitespace()) {
      tokenEnd = readWhile(tokenStart) { it.isWhitespace() }
      tokenType = TokenType.WHITE_SPACE
      return
    }

    if (match("//")) {
      tokenEnd = readUntilLineEnd(tokenStart + 2)
      tokenType = GeneratedTypes.LINE_COMMENT
      return
    }

    if (match("/*")) {
      tokenEnd = readBlockComment(tokenStart + 2)
      tokenType = GeneratedTypes.BLOCK_COMMENT
      return
    }

    if (c == '"') {
      tokenEnd = readString(tokenStart + 1)
      tokenType = GeneratedTypes.STRING
      return
    }

    if (c == '\\') {
      tokenEnd = readWhile(tokenStart + 1) { !it.isWhitespace() }
      tokenType = GeneratedTypes.ESCAPED_IDENTIFIER
      return
    }

    if (c == '`') {
      val directiveEnd = readIdentifierBody(tokenStart + 1)
      if (directiveEnd > tokenStart + 1) {
        tokenEnd = directiveEnd
        tokenType = GeneratedTypes.DIRECTIVE
      } else {
        tokenEnd = tokenStart + 1
        tokenType = TokenType.BAD_CHARACTER
      }
      return
    }

    if (c.isDigit() || (c == '\'' && tokenStart + 1 < endOffset)) {
      val numberEnd = readNumber(tokenStart)
      if (numberEnd > tokenStart) {
        tokenEnd = numberEnd
        tokenType = GeneratedTypes.NUMBER
        return
      }
    }

    if (c.isLetter() || c == '_') {
      tokenEnd = readIdentifierBody(tokenStart + 1)
      val text = buffer.subSequence(tokenStart, tokenEnd).toString()
      tokenType = KEYWORDS[text] ?: GeneratedTypes.IDENTIFIER
      return
    }

    tokenEnd = tokenStart + 1
    tokenType = SINGLE_CHAR_TOKENS[c] ?: TokenType.BAD_CHARACTER
  }

  private fun match(text: String): Boolean {
    if (tokenStart + text.length > endOffset) {
      return false
    }
    for (i in text.indices) {
      if (buffer[tokenStart + i] != text[i]) {
        return false
      }
    }
    return true
  }

  private fun readUntilLineEnd(offset: Int): Int {
    var index = offset
    while (index < endOffset && buffer[index] != '\n' && buffer[index] != '\r') {
      index++
    }
    return index
  }

  private fun readBlockComment(offset: Int): Int {
    var index = offset
    while (index + 1 < endOffset) {
      if (buffer[index] == '*' && buffer[index + 1] == '/') {
        return index + 2
      }
      index++
    }
    return endOffset
  }

  private fun readString(offset: Int): Int {
    var index = offset
    while (index < endOffset) {
      val ch = buffer[index]
      if (ch == '\\' && index + 1 < endOffset) {
        index += 2
        continue
      }
      if (ch == '"') {
        return index + 1
      }
      if (ch == '\n' || ch == '\r') {
        return index
      }
      index++
    }
    return endOffset
  }

  private fun readNumber(offset: Int): Int {
    var index = offset
    if (buffer[index] == '\'') {
      return readBasedNumber(index)
    }
    index = readWhile(index) { it.isDigit() || it == '_' }
    if (index < endOffset && buffer[index] == '\'') {
      return readBasedNumber(index, sizeStart = offset)
    }
    return index
  }

  private fun readBasedNumber(quoteOffset: Int, sizeStart: Int = quoteOffset): Int {
    var index = quoteOffset + 1
    if (index < endOffset && (buffer[index] == 's' || buffer[index] == 'S')) {
      index++
    }
    if (index >= endOffset) {
      return sizeStart
    }
    val base = Character.toLowerCase(buffer[index])
    if (!(base == 'd' || base == 'h' || base == 'o' || base == 'b')) {
      return sizeStart
    }
    index++
    val digitsStart = index
    while (index < endOffset && isVerilogNumberDigit(buffer[index])) {
      index++
    }
    return if (index > digitsStart) index else sizeStart
  }

  private fun readIdentifierBody(offset: Int): Int =
    readWhile(offset) { it.isLetterOrDigit() || it == '_' || it == '$' }

  private fun readWhile(offset: Int, predicate: (Char) -> Boolean): Int {
    var index = offset
    while (index < endOffset && predicate(buffer[index])) {
      index++
    }
    return index
  }

  private fun isVerilogNumberDigit(c: Char): Boolean =
    c.isDigit() || c in 'a'..'f' || c in 'A'..'F' || c == '_' || c == 'x' || c == 'X' ||
        c == 'z' || c == 'Z' || c == '?'

  private companion object {
    val KEYWORDS = mapOf(
        "module" to GeneratedTypes.MODULE,
        "endmodule" to GeneratedTypes.ENDMODULE,
        "input" to GeneratedTypes.INPUT,
        "output" to GeneratedTypes.OUTPUT,
        "wire" to GeneratedTypes.WIRE,
        "reg" to GeneratedTypes.REG,
        "assign" to GeneratedTypes.ASSIGN,
        "always" to GeneratedTypes.ALWAYS,
        "initial" to GeneratedTypes.INITIAL,
        "begin" to GeneratedTypes.BEGIN,
        "end" to GeneratedTypes.END
    )

    val SINGLE_CHAR_TOKENS = mapOf(
        '(' to GeneratedTypes.LPAREN,
        ')' to GeneratedTypes.RPAREN,
        '[' to GeneratedTypes.LBRACKET,
        ']' to GeneratedTypes.RBRACKET,
        '{' to GeneratedTypes.LBRACE,
        '}' to GeneratedTypes.RBRACE,
        ';' to GeneratedTypes.SEMICOLON,
        ':' to GeneratedTypes.COLON,
        ',' to GeneratedTypes.COMMA,
        '.' to GeneratedTypes.DOT,
        '@' to GeneratedTypes.AT,
        '#' to GeneratedTypes.HASH,
        '?' to GeneratedTypes.QUESTION,
        '=' to GeneratedTypes.EQ,
        '+' to GeneratedTypes.PLUS,
        '-' to GeneratedTypes.MINUS,
        '*' to GeneratedTypes.STAR,
        '/' to GeneratedTypes.SLASH,
        '%' to GeneratedTypes.PERCENT,
        '&' to GeneratedTypes.AMP,
        '|' to GeneratedTypes.PIPE,
        '^' to GeneratedTypes.CARET,
        '~' to GeneratedTypes.TILDE
    )
  }
}
