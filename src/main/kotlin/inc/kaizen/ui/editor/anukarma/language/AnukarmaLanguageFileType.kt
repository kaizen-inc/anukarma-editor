package inc.kaizen.ui.editor.anukarma.language

import com.intellij.openapi.fileTypes.LanguageFileType
import inc.kaizen.ui.editor.anukarma.language.icon.AnukarmaIcons

class AnukarmaLanguageFileType: LanguageFileType(AnukarmaLanguage) {
    override fun getName() = "Anukarma"

    override fun getDescription() = "Anukarma"

    override fun getDefaultExtension() = "karma"

    override fun getIcon() = AnukarmaIcons.ANUKARMA
}