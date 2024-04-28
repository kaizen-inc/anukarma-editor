package inc.kaizen.ui.editor.anukarma.language.util

import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiFile
import inc.kaizen.ui.editor.anukarma.language.*
import org.jetbrains.annotations.Nullable
import javax.swing.Icon


class AnukarmaPsiImplUtil {
    companion object {

        fun getKey(element: AnukarmaProperty): String? {
            val keyNode: ASTNode? = element.node.findChildByType(IAnukarmaLanguageFileType.KEY)
            return keyNode?.text?.replace("\\\\ ", " ")
        }

        fun getValue(element: AnukarmaProperty): String? {
            return element.node.findChildByType(IAnukarmaLanguageFileType.VALUE)?.text
        }

        fun getPresentation(element: AnukarmaProperty): ItemPresentation {
            return object : ItemPresentation {
                @Nullable
                override fun getPresentableText(): String {
                    return element.key.toString()
                }

                @Nullable
                override fun getLocationString(): String? {
                    val containingFile: PsiFile = element.containingFile
                    return containingFile.name
                }

                override fun getIcon(unused: Boolean): Icon? {
                    return element.getIcon(0)
                }
            }
        }
    }
}