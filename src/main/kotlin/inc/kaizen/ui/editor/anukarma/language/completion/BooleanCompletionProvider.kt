package inc.kaizen.ui.editor.anukarma.language.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType

object BooleanCompletionProvider : AnukarmaCompletionProvider() {

//    override val context: ElementPattern<PsiElement>
//        get() = PlatformPatterns
//        .psiElement()
//        .with(object : PatternCondition<PsiElement>(IAnukarmaLanguageFileType.VALUE) {
//            override fun accepts(elem: PsiElement, context: ProcessingContext?) = isTopLevel(elem)
//        })
    override val context: ElementPattern<PsiElement>
        get() = psiElement(IAnukarmaLanguageFileType.IDENTIFIER)
//        get() = psiElement(IAnukarmaLanguageFileType.VALUE)//.withElementType(IAnukarmaLanguageFileType.VALUE)
//            .andOr(
//                psiElement().withElementType(IAnukarmaLanguageFileType.CONTENT),
//                psiElement().withElementType(IAnukarmaLanguageFileType.PROPERTY),
//                psiElement().withElementType(IAnukarmaLanguageFileType.IDENTIFIER),
////                psiElement().withElementType(IAnukarmaLanguageFileType.PROPERTY),
//                psiElement().withElementType(IAnukarmaLanguageFileType.KEY),
//                psiElement().withElementType(IAnukarmaLanguageFileType.VALUE),
//                psiElement().withElementType(IAnukarmaLanguageFileType.BOOLEAN),
////                psiElement().withElementType(IAnukarmaLanguageFileType.TASK_SECTION),
//                //other top level stuff
//            )


    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) {
        resultSet.addElement(LookupElementBuilder.create("true"))
        resultSet.addElement(LookupElementBuilder.create("false"))
    }
}