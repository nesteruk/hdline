package com.activemesa.hdline.vhdl.lang

import com.intellij.lexer.LexerBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import generated.VhdlGeneratedTypes

class VhdlLexer : LexerBase() {
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

    if (match("--")) {
      tokenEnd = readUntilLineEnd(tokenStart + 2)
      tokenType = VhdlGeneratedTypes.COMMENT
      return
    }

    if (c == '"') {
      tokenEnd = readString(tokenStart + 1)
      tokenType = VhdlGeneratedTypes.STRING
      return
    }

    if (c == '\'' && tokenStart + 2 < endOffset && buffer[tokenStart + 2] == '\'') {
      tokenEnd = tokenStart + 3
      tokenType = VhdlGeneratedTypes.CHARACTER_LITERAL
      return
    }

    if (c == '\\') {
      tokenEnd = readExtendedIdentifier(tokenStart + 1)
      tokenType = if (tokenEnd > tokenStart + 1 && tokenEnd <= endOffset && buffer[tokenEnd - 1] == '\\') {
        VhdlGeneratedTypes.EXTENDED_IDENTIFIER
      } else {
        TokenType.BAD_CHARACTER
      }
      return
    }

    if (c.isDigit()) {
      tokenEnd = readWhile(tokenStart) { it.isDigit() || it == '_' }
      tokenType = VhdlGeneratedTypes.NUMBER
      return
    }

    if (c.isLetter()) {
      tokenEnd = readIdentifierBody(tokenStart + 1)
      val text = buffer.subSequence(tokenStart, tokenEnd).toString().toLowerCase()
      tokenType = KEYWORDS[text] ?: VhdlGeneratedTypes.IDENTIFIER
      return
    }

    if (match("<=")) {
      tokenEnd = tokenStart + 2
      tokenType = VhdlGeneratedTypes.ASSIGN
      return
    }

    if (match("=>")) {
      tokenEnd = tokenStart + 2
      tokenType = VhdlGeneratedTypes.ARROW
      return
    }

    tokenEnd = tokenStart + 1
    tokenType = SINGLE_CHAR_TOKENS[c] ?: TokenType.BAD_CHARACTER
  }

  private fun match(text: String): Boolean {
    if (tokenStart + text.length > endOffset) {
      return false
    }
    for (i in 0 until text.length) {
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

  private fun readString(offset: Int): Int {
    var index = offset
    while (index < endOffset) {
      val ch = buffer[index]
      if (ch == '"' && index + 1 < endOffset && buffer[index + 1] == '"') {
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

  private fun readExtendedIdentifier(offset: Int): Int {
    var index = offset
    while (index < endOffset) {
      if (buffer[index] == '\\') {
        return index + 1
      }
      if (buffer[index] == '\n' || buffer[index] == '\r') {
        return index
      }
      index++
    }
    return endOffset
  }

  private fun readIdentifierBody(offset: Int): Int =
    readWhile(offset) { it.isLetterOrDigit() || it == '_' }

  private fun readWhile(offset: Int, predicate: (Char) -> Boolean): Int {
    var index = offset
    while (index < endOffset && predicate(buffer[index])) {
      index++
    }
    return index
  }

  private companion object {
    val KEYWORDS = mapOf(
        "entity" to VhdlGeneratedTypes.ENTITY,
        "architecture" to VhdlGeneratedTypes.ARCHITECTURE,
        "is" to VhdlGeneratedTypes.IS,
        "begin" to VhdlGeneratedTypes.BEGIN,
        "end" to VhdlGeneratedTypes.END,
        "port" to VhdlGeneratedTypes.PORT,
        "signal" to VhdlGeneratedTypes.SIGNAL,
        "process" to VhdlGeneratedTypes.PROCESS,
        "of" to VhdlGeneratedTypes.OF,
        "in" to VhdlGeneratedTypes.IN,
        "out" to VhdlGeneratedTypes.OUT,
        "downto" to VhdlGeneratedTypes.DOWNTO,
        "to" to VhdlGeneratedTypes.TO,
        "std_logic" to VhdlGeneratedTypes.STD_LOGIC,
        "std_logic_vector" to VhdlGeneratedTypes.STD_LOGIC_VECTOR
    )

    val SINGLE_CHAR_TOKENS = mapOf(
        '(' to VhdlGeneratedTypes.LPAREN,
        ')' to VhdlGeneratedTypes.RPAREN,
        ':' to VhdlGeneratedTypes.COLON,
        ';' to VhdlGeneratedTypes.SEMICOLON,
        ',' to VhdlGeneratedTypes.COMMA,
        '.' to VhdlGeneratedTypes.DOT,
        '+' to VhdlGeneratedTypes.PLUS,
        '-' to VhdlGeneratedTypes.MINUS,
        '*' to VhdlGeneratedTypes.STAR,
        '/' to VhdlGeneratedTypes.SLASH,
        '&' to VhdlGeneratedTypes.AMP
    )
  }
}
