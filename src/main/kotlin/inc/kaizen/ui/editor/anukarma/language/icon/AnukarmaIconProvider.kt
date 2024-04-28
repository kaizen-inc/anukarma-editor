package inc.kaizen.ui.editor.anukarma.language.icon

import com.intellij.ide.IconProvider
import com.intellij.psi.PsiElement
import inc.kaizen.ui.editor.anukarma.language.AnukarmaProperty
import org.jetbrains.annotations.NotNull
import javax.swing.Icon

class AnukarmaIconProvider: IconProvider() {

    @Override
    override fun getIcon(@NotNull element: PsiElement, flags: Int): Icon? {
        return if(element is AnukarmaProperty) AnukarmaIcons.ANUKARMA else null
    }
}