package inc.kaizen.ui.editor.anukarma.language.structure.view

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.elementType
import inc.kaizen.ui.editor.anukarma.language.AnukarmaSpaces
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType
import inc.kaizen.ui.editor.anukarma.language.icon.AnukarmaIcons

class AnukarmaStructureViewElement(val element: PsiElement): StructureViewTreeElement, SortableTreeElement {

    override fun getPresentation(): PresentationData {

        val icon = when(element.elementType) {
            IAnukarmaLanguageFileType.FEATURE_PROPERTIES,
            IAnukarmaLanguageFileType.FEATURE_PROPERTIES,
            IAnukarmaLanguageFileType.TASK_PROPERTY_IMPLEMENTATION,
            IAnukarmaLanguageFileType.TASK_PROPERTY_GROUP,
            IAnukarmaLanguageFileType.TASK_PROPERTY_STRICT_DEPENDENCY,
            IAnukarmaLanguageFileType.PROPERTY -> {
                AnukarmaIcons.PARAMETER
            }
            IAnukarmaLanguageFileType.PLUGIN_SECTION -> {
                AnukarmaIcons.PLUGIN
            }
            IAnukarmaLanguageFileType.TASK_SECTION -> {
                AnukarmaIcons.TASK
            }
            IAnukarmaLanguageFileType.EXTENSION_SECTION -> {
                AnukarmaIcons.EXTENSION
            }
            IAnukarmaLanguageFileType.FEATURE_SECTION -> {
                AnukarmaIcons.FEATURE
            }
            IAnukarmaLanguageFileType.EXTENSION_FILE_VALUE -> {
                AnukarmaIcons.NAVIGATE
            }
            IAnukarmaLanguageFileType.PLUGIN_ID_VALUE,
            IAnukarmaLanguageFileType.TASK_DEPENDENCY_ID_VALUE,
            IAnukarmaLanguageFileType.FEATURE_DEPENDENCY_ID_VALUE -> {
                AnukarmaIcons.LINK
            }
            else -> {
                null
            }
        }

        val presentableText = if (element is PsiNamedElement) {
            element.name
        } else if (element !is LeafPsiElement) {
            element.firstChild.text
        } else {
            element.text
        }

//        val locationString = if(element !is LeafPsiElement) {
//            "(${element.elementType})"
//        } else {
//            null
//        }

        val presentationData = PresentationData(presentableText, null, icon, null)
        return presentationData
    }

    override fun getChildren(): Array<TreeElement> {
        val properties: List<PsiElement> = getChildrenOfAnyType(element)

        val treeElements: MutableList<TreeElement> = ArrayList(properties.size)
        for (property in properties) {
            treeElements.add(AnukarmaStructureViewElement(property))
        }
        return treeElements.toTypedArray<TreeElement>()
    }

    override fun navigate(requestFocus: Boolean) {
        if (element is NavigationItem) {
            element.navigate(requestFocus)
        }
    }

    override fun canNavigate(): Boolean {
        if (element is NavigationItem) {
            return element.canNavigate()
        }
        return false
    }

    override fun canNavigateToSource(): Boolean {
        if (element is NavigationItem) {
            return element.canNavigateToSource()
        }
        return true
    }

    override fun getValue(): PsiElement = element

    override fun getAlphaSortKey() = element.text ?: ""

    private fun getChildrenOfAnyType(element: PsiElement): MutableList<PsiElement> {
        val result: MutableList<PsiElement> = mutableListOf()
        element.children.forEach { child ->
            if (child !is LeafPsiElement && child !is AnukarmaSpaces) {
                child.children.forEach { grandChild ->
                    if (grandChild !is LeafPsiElement && grandChild !is AnukarmaSpaces) {
                        result.add(grandChild)
                    }
                }
            }
        }
        return result
    }
}