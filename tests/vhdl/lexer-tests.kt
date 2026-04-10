package com.activemesa.hdline.vhdl.lang

class VhdlLexerTests : VhdlTestCase(VhdlLexer()) {
  fun testEntityAndArchitecture() = doTest(
      """
      entity adder is
        port (
          a : in std_logic;
          b : in std_logic;
          sum : out std_logic
        );
      end entity adder;

      architecture rtl of adder is
        signal carry : std_logic;
      begin
        sum <= a;
      end architecture rtl;
      """.trimIndent()
  )

  fun testCommentsExtendedIdentifiersAndProcess() = doTest(
      """
      entity \data bus\ is
      end entity \data bus\;

      architecture rtl of \data bus\ is
      begin
        -- single line comment
        process begin
          \data bus\ <= '1';
        end process;
      end architecture rtl;
      """.trimIndent()
  )
}
