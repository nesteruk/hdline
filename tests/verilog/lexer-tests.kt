package com.activemesa.hdline.verilog.lang

class VerilogLexerTests : VerilogTestCase(VerilogLexer()) {
  fun testSimpleModule() = doTest(
      """
      module adder(a, b, sum);
        input a, b;
        output sum;
        wire carry;
        assign sum = a ^ b;
      endmodule
      """.trimIndent()
  )

  fun testCommentsEscapedIdentifiersAndDirectives() = doTest(
      """
      `define WIDTH 8
      module \adder${'$'}top (clk);
        // single line comment
        /* block comment */
        reg \state${'$'}1 ;
        assign \state${'$'}1 = 42;
      endmodule
      """.trimIndent()
  )
}
