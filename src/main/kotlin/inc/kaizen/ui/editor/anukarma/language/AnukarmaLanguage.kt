package inc.kaizen.ui.editor.anukarma.language

import com.intellij.lang.Language

object AnukarmaLanguage: Language("Anukarma") {
    private fun readResolve(): Any = AnukarmaLanguage
}