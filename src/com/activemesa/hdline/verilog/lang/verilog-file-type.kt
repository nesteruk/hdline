package com.activemesa.hdline.verilog.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object VerilogFileType : LanguageFileType(VerilogLanguage) {
  override fun getName() = "Verilog File"

  override fun getDescription() = "Verilog hardware description language file"

  override fun getDefaultExtension() = "v"

  override fun getIcon(): Icon? = null
}
