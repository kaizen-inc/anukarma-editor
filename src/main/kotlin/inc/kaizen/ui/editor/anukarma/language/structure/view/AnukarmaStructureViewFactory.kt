package inc.kaizen.ui.editor.anukarma.language.structure.view

import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class AnukarmaStructureViewFactory: PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            @NotNull
            override fun createStructureViewModel(@Nullable editor: Editor?): StructureViewModel {
                return AnukarmaStructureViewModel(editor!!, psiFile)
            }
        }
    }
}