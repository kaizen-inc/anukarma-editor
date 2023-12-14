package inc.kaizen.ui.editor.anukarma.language.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType

class AnukarmaCompletionContributor: CompletionContributor(), DumbAware {
   init {
        extend(CompletionType.BASIC,
            PlatformPatterns.psiElement(IAnukarmaLanguageFileType.GENERATECODE),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet
                ) {
                    resultSet.addElement(LookupElementBuilder.create("generateCode"))
                }
            }
        )
    }
}