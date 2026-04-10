# AGENTS.md

## Purpose

This repository is a small IntelliJ language-plugin skeleton for HDL work. The current tree is minimal and partially scaffolded: it contains Verilog and VHDL grammar sources, checked-in generated parser artifacts, language/file-type definitions, and lexer test scaffolding.

## Repository Map

- `src/com/activemesa/hdline/verilog/lang/`
  - Verilog language, file type, and runtime lexer.
- `src/com/activemesa/hdline/vhdl/lang/`
  - VHDL language, file type, and runtime lexer.
- `src/META-INF/plugin.xml`
  - IntelliJ plugin descriptor registering Verilog and VHDL file types.
- `grammars/verilog.bnf`, `grammars/_VerilogLexer.flex`
  - Verilog grammar and lexer sources. Treat them as source-of-truth for Verilog parser shape and tokenization.
- `grammars/vhdl.bnf`, `grammars/_VhdlLexer.flex`
  - VHDL grammar and lexer sources. Treat them as source-of-truth for VHDL parser shape and tokenization.
- `gen/`
  - Checked-in generated parser/token artifacts for both languages. Do not hand-edit unless there is a very specific reason and the grammar sources are kept in sync.
- `tests/verilog/`, `tests/vhdl/`
  - Lexer test scaffolding based on IntelliJ `LexerTestCase`.
- `doc/`
  - Reference PDFs.

## Current State

- There is no `README`, Gradle build, Maven build, wrapper script, or other top-level build automation in the repo.
- The repository still has no checked-in Gradle/Maven build or regeneration workflow.
- The grammars and parsers describe minimal Verilog and VHDL subsets, not the full language standards.
- `testData/verilog/lexer` and `testData/vhdl/lexer` back lexer-focused tests.

Future agents should preserve these observations instead of assuming the project is already fully wired up.

## Editing Rules

- Prefer editing `grammars/*.bnf` and `grammars/*.flex` over modifying generated files in `gen/`.
- If parser or lexer behavior changes, regenerate `gen/` using the project’s intended Grammar-Kit/JFlex workflow if that workflow is later added.
- Keep package names consistent under `com.activemesa.hdline.verilog` and `com.activemesa.hdline.vhdl`.
- Do not remove checked-in generated files unless the user explicitly asks for a repo cleanup or build-system migration.
- Preserve the minimal IntelliJ plugin structure unless the task explicitly requires expansion.

## Testing Guidance

- Before claiming tests pass, verify there is a runnable build/test path in the repo. At the moment, none is checked in.
- If adding or fixing lexer tests, update the paired `testData/verilog/lexer/*.txt` or `testData/vhdl/lexer/*.txt` fixtures because the existing tests depend on them.
- If build tooling is introduced, document the exact regeneration and test commands in this file.

## Safe Assumptions For Future Work

- This repo is best treated as an early-stage grammar plugin prototype.
- Generated code in `gen/` is committed and may be relied on by the user, even if regeneration steps are not yet documented.
- Any substantial parser work should start by reconciling grammar intent, plugin registration, package naming, and missing build/test infrastructure.
