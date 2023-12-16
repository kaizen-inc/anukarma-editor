package inc.kaizen.ui.editor.anukarma.language.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.openapi.project.DumbAware


class AnukarmaCompletionContributor: CompletionContributor(), DumbAware {

    private val providers = listOf(
        BooleanCompletionProvider
    )

    init {
        providers.forEach { extend(it) }
    }

    private fun extend(provider: AnukarmaCompletionProvider) {
        extend(provider.type, provider.context, provider)
    }

    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        super.fillCompletionVariants(parameters, result)
    }
}
