package inc.kaizen.ui.editor.anukarma.language.fold

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup.newGroup
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType


class AnukarmaFoldingBuilder: FoldingBuilderEx(), DumbAware {

    override fun buildFoldRegions(node: PsiElement,
                                  document: Document,
                                  quick: Boolean): Array<FoldingDescriptor> {
        // Initialize the list of folding regions
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        navigate(node, descriptors)
        return descriptors.toTypedArray()
    }

    private fun navigate(node: PsiElement, descriptors: MutableList<FoldingDescriptor>) {
        node.children.onEach { child ->

            if (child.elementType == IAnukarmaLanguageFileType.TASK_SECTION) {
                val group = newGroup(node.text)
                descriptors.add(FoldingDescriptor(node.node, node.textRange, group, setOf()))
            } else if (child.elementType == IAnukarmaLanguageFileType.FEATURE_SECTION) {
                val group = newGroup(node.text)
                descriptors.add(FoldingDescriptor(node.node, node.textRange, group, setOf()))
            } else if (child.elementType == IAnukarmaLanguageFileType.EXTENSION_SECTION) {
                val group = newGroup(node.text)
                descriptors.add(FoldingDescriptor(node.node, node.textRange, group, setOf()))
            } else if (child.elementType == IAnukarmaLanguageFileType.PLUGIN_SECTION) {
                val group = newGroup(node.text)
                descriptors.add(FoldingDescriptor(node.node, node.textRange, group, setOf()))
            } else if (child.elementType == IAnukarmaLanguageFileType.TASK_DEPENDENCY) {
                val group = newGroup(node.text)
                descriptors.add(FoldingDescriptor(node.node, node.textRange, group, setOf()))
            } else if (child.elementType == IAnukarmaLanguageFileType.FEATURE_DEPENDENCY) {
                val group = newGroup(node.text)
                descriptors.add(FoldingDescriptor(node.node, node.textRange, group, setOf()))
            }

            navigate(child, descriptors)
        }
    }
    override fun getPlaceholderText(node: ASTNode): String {
        return StringUtil.THREE_DOTS
    }

    override fun isCollapsedByDefault(node: ASTNode) = false
}