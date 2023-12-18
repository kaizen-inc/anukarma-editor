package inc.kaizen.ui.editor.anukarma.language.annotate.impl

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType
import inc.kaizen.ui.editor.anukarma.language.annotate.IAnnotator
import inc.kaizen.ui.editor.anukarma.language.fix.AnukarmaCreatePropertyQuickFix

class MissingFeatureAnnotator: IAnnotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if(element.elementType == IAnukarmaLanguageFileType.IDENTIFIER &&
            element.parent.elementType == IAnukarmaLanguageFileType.FEATURE_DEPENDENCY_ID) {
            if(!findTaskById(element)) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Missing feature for configured ID")
                    .range(element.textRange)
                    .problemGroup { "Anukarma" }
                    .tooltip("The task with ID '${element.text}' does not exist")
                    .highlightType(ProblemHighlightType.ERROR)
                    // ** Tutorial step 19. - Add a quick fix for the string containing possible properties
                    .withFix(AnukarmaCreatePropertyQuickFix("Create New Feature with ID ${element.text}"))
                    .create()
            }
        }
    }

    private fun findTaskById(element: PsiElement): Boolean {
        return element.containingFile.text.contains("feature(\"${element.text}\")")
    }
}