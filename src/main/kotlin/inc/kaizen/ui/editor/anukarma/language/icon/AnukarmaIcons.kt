package inc.kaizen.ui.editor.anukarma.language.icon

import com.intellij.openapi.util.IconLoader.getIcon
import javax.swing.Icon


class AnukarmaIcons {
    companion object {
        val PLUGIN: Icon = getIcon("/icons/plugin.svg", AnukarmaIcons::class.java)
        val PARAMETER: Icon = getIcon("/icons/parameter.svg", AnukarmaIcons::class.java)
        val ANUKARMA: Icon = getIcon("/icons/anukarma.svg", AnukarmaIcons::class.java)
    }
}