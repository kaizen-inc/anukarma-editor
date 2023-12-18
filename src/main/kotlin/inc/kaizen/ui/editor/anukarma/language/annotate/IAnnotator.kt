package inc.kaizen.ui.editor.anukarma.language.annotate

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.psi.PsiElement

interface IAnnotator {

    fun annotate(element: PsiElement, holder: AnnotationHolder)
}