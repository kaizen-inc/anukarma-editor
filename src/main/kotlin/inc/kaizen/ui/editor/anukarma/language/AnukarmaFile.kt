package inc.kaizen.ui.editor.anukarma.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import org.jetbrains.annotations.NotNull

class AnukarmaFile(@NotNull viewProvider: FileViewProvider): PsiFileBase(viewProvider, AnukarmaLanguage) {

    override fun getFileType() = AnukarmaLanguageFileType()
}