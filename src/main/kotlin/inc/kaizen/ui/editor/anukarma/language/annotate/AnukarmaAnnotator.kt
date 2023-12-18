package inc.kaizen.ui.editor.anukarma.language.annotate

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import inc.kaizen.ui.editor.anukarma.language.annotate.impl.*

class AnukarmaAnnotator: Annotator {

    private val annotators: MutableList<IAnnotator> = mutableListOf()

    init {
        annotators.add(MissingClassAnnotator())
        annotators.add(MissingTaskAnnotator())
        annotators.add(MissingFeatureAnnotator())
        annotators.add(DuplicateFeatureIdAnnotator())
        annotators.add(DuplicateTaskIdAnnotator())
        annotators.add(CyclicDependencyAnnotator())
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
//        val prefixRange = TextRange.from(element.textRange.startOffset, SIMPLE_PREFIX_STR.length + 1)
//        val separatorRange = TextRange.from(prefixRange.endOffset, SIMPLE_SEPARATOR_STR.length)
//        val keyRange  = TextRange(separatorRange.endOffset, element.textRange.endOffset - 1)
//        val key: String = value.substring(SIMPLE_PREFIX_STR.length + SIMPLE_SEPARATOR_STR.length)
        annotators.forEach { annotator ->
            annotator.annotate(element, holder)
        }
    }
}