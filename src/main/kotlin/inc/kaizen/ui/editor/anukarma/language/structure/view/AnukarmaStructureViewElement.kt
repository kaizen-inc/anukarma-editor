package inc.kaizen.ui.editor.anukarma.language.structure.view

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import inc.kaizen.ui.editor.anukarma.language.AnukarmaProperty
import inc.kaizen.ui.editor.anukarma.language.AnukarmaTaskContent
import inc.kaizen.ui.editor.anukarma.language.AnukarmaTaskSection
import inc.kaizen.ui.editor.anukarma.language.impl.AnukarmaPropertyImpl
import inc.kaizen.ui.editor.anukarma.language.impl.AnukarmaTaskContentImpl

class AnukarmaStructureViewElement(val element: NavigatablePsiElement): StructureViewTreeElement, SortableTreeElement {

    override fun getPresentation() = element.presentation ?: PresentationData()

    override fun getChildren(): Array<TreeElement> {
        if (element is PsiFile) {
            val properties: List<AnukarmaProperty> = PsiTreeUtil.getChildrenOfTypeAsList(
                element,
                AnukarmaProperty::class.java
            )
            val treeElements: MutableList<TreeElement> = ArrayList(properties.size)
            for (property in properties) {
                treeElements.add(AnukarmaStructureViewElement(property as AnukarmaPropertyImpl))
            }
            return treeElements.toTypedArray<TreeElement>()
        } else if(element is AnukarmaTaskSection){
            val properties: List<AnukarmaTaskContent> = PsiTreeUtil.getChildrenOfTypeAsList(
                element,
                AnukarmaTaskContent::class.java
            )
            val treeElements: MutableList<TreeElement> = ArrayList(properties.size)
            for (property in properties) {
                treeElements.add(AnukarmaStructureViewElement(property as AnukarmaTaskContentImpl))
            }
            return treeElements.toTypedArray<TreeElement>()
        }
        return emptyArray()
    }

    override fun navigate(requestFocus: Boolean) {
        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return element.canNavigate()
    }

   override fun canNavigateToSource(): Boolean {
       return element.canNavigateToSource()
   }

    override fun getValue() = element

    override fun getAlphaSortKey() = element.name ?: ""
}