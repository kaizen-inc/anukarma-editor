package inc.kaizen.ui.editor.anukarma.language.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType

object TaskPropertiesCompletionProvider : AnukarmaCompletionProvider() {

    override val context: ElementPattern<PsiElement>
        get() = psiElement(IAnukarmaLanguageFileType.WHITESPACES)
            .withParent(psiElement(IAnukarmaLanguageFileType.TASK_SECTION))

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) {
        resultSet.addElement(LookupElementBuilder.create("implementation"))
        resultSet.addElement(LookupElementBuilder.create("group"))
        resultSet.addElement(LookupElementBuilder.create("strictDependency"))
        resultSet.addElement(LookupElementBuilder.create("dependsOn"))
    }
}