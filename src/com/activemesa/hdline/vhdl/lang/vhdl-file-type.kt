package com.activemesa.hdline.vhdl.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object VhdlFileType : LanguageFileType(VhdlLanguage) {
  override fun getName() = "VHDL File"

  override fun getDescription() = "VHDL hardware description language file"

  override fun getDefaultExtension() = "vhd"

  override fun getIcon(): Icon? = null
}
