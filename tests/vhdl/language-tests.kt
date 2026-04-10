package com.activemesa.hdline.vhdl.lang

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

abstract class VhdlTestCase(val lexer: Lexer) : LexerTestCase() {
  override fun getDirPath() = "$TEST_DATA_PATH/vhdl/lexer"
  override fun createLexer() = lexer
  override fun doTest(text: String?) = assertSameLinesWithFile(
      "$dirPath/${getTestName(false)}.txt",
      printTokens(text, 0, createLexer())
  )
}
