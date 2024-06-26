package inc.kaizen.ui.editor.anukarma.language.structure.view

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.LeafPsiElement
import javax.annotation.Nullable

class AnukarmaStructureViewModel(@Nullable editor: Editor, psiFile: PsiFile):
    StructureViewModelBase(psiFile, editor, AnukarmaStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {

    override fun getCurrentEditorElement(): Any? {
        return super.getCurrentEditorElement()
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean {
        return element?.value is LeafPsiElement
    }
}