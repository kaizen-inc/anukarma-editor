package inc.kaizen.ui.editor.anukarma.language.annotate.impl

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiElement
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.elementType
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType
import inc.kaizen.ui.editor.anukarma.language.annotate.IAnnotator
import inc.kaizen.ui.editor.anukarma.language.fix.AnukarmaCreatePropertyQuickFix

class MissingClassAnnotator: IAnnotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if(element.elementType == IAnukarmaLanguageFileType.IMPLEMENTATION_CLASS) {
            try {
                val klass = JavaPsiFacade.getInstance(element.project)
                    .findClass(element.text, GlobalSearchScope.projectScope(element.project))
                if (klass == null) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Missing implementation")
                        .range(element.textRange)
                        .problemGroup { "Anukarma" }
                        .tooltip("The class ${element.text} does not exist in classpath")
                        .highlightType(ProblemHighlightType.ERROR)
                        // ** Tutorial step 19. - Add a quick fix for the string containing possible properties
                        .withFix(AnukarmaCreatePropertyQuickFix("Create missing class ${element.text}"))
                        .create()
                }
            } catch (e: Exception) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Missing implementation")
                    .range(element.textRange)
                    .problemGroup { "Anukarma" }
                    .tooltip("The class ${element.text} does not exists in classpath")
                    .highlightType(ProblemHighlightType.ERROR)
                    // ** Tutorial step 19. - Add a quick fix for the string containing possible properties
                    .withFix(AnukarmaCreatePropertyQuickFix("key"))
                    .create()
            }
        }
    }
}