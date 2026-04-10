# HDLine

HDLine is an early-stage IntelliJ plugin project for hardware description languages.

At the moment, the repository contains a minimal language-support scaffold for:

- Verilog
- VHDL

The project includes checked-in grammar sources, generated parser artifacts, lightweight runtime lexers, file-type registration, and lexer-focused tests. The current implementation is intentionally small: it supports a practical subset of each language rather than the full Verilog or VHDL standards.

## What This Repository Contains

- Verilog grammar and lexer sources in `grammars/verilog.bnf` and `grammars/_VerilogLexer.flex`
- VHDL grammar and lexer sources in `grammars/vhdl.bnf` and `grammars/_VhdlLexer.flex`
- Checked-in generated parser/token files under `gen/`
- IntelliJ language and file-type definitions under `src/`
- Lexer test scaffolding and expected token output under `tests/` and `testData/`

## Current Scope

The repository currently focuses on the basics needed to grow into a real HDL plugin:

- language definitions for Verilog and VHDL
- file-type registration for common HDL extensions
- minimal parser scaffolds for both languages
- runtime lexers for comments, identifiers, literals, keywords, and core punctuation
- lexer regression tests backed by token fixture files

This is not yet a complete IDE plugin. It does not currently include a full build pipeline, a complete IntelliJ plugin packaging setup, or full-language grammar coverage.

## Supported File Extensions

- Verilog: `.v`, `.vh`
- VHDL: `.vhd`, `.vhdl`

## Repository Layout

- `src/` contains IntelliJ-facing language, lexer, and file-type code plus `META-INF/plugin.xml`
- `grammars/` contains the editable BNF and JFlex sources
- `gen/` contains checked-in generated parser artifacts
- `tests/` contains lexer tests
- `testData/` contains expected lexer output fixtures
- `doc/` contains reference PDFs for the language standards

## Project Status

HDLine should currently be treated as a prototype. The repository is useful as a base for continued work on HDL language support, but it still needs:

- fuller Verilog grammar coverage
- fuller VHDL grammar coverage
- a reproducible build and test setup
- IntelliJ plugin packaging and validation workflow
- richer IDE features beyond tokenization and basic parsing

## Development Notes

If you extend the parser or lexer behavior, prefer editing the grammar sources in `grammars/` and keep the checked-in generated files in `gen/` consistent with those changes.
