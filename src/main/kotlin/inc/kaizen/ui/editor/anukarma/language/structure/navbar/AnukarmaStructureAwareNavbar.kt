package inc.kaizen.ui.editor.anukarma.language.structure.navbar

import com.intellij.icons.AllIcons
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension
import com.intellij.lang.Language
import com.intellij.openapi.project.Project
import inc.kaizen.ui.editor.anukarma.language.AnukarmaFile
import inc.kaizen.ui.editor.anukarma.language.AnukarmaLanguage
import inc.kaizen.ui.editor.anukarma.language.AnukarmaProperty
import org.jetbrains.annotations.Nullable
import javax.swing.Icon

class AnukarmaStructureAwareNavbar(override val language: Language = AnukarmaLanguage) : StructureAwareNavBarModelExtension() {

    override fun getPresentableText(any: Any): String? {
        if(any is Project) {
            return any.name
        }
        if (any is AnukarmaFile) {
            return any.name
        }
        if (any is AnukarmaProperty) {
            return any.key.text
        }

        return null
    }

    @Nullable
    override fun getIcon(any: Any?): Icon? {
        if (any is AnukarmaProperty) {
            return AllIcons.Nodes.Property
        }
        return null
    }
}