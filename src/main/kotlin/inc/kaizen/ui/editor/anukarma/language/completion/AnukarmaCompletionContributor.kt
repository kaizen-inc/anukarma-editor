package inc.kaizen.ui.editor.anukarma.language.completion

import com.intellij.codeInsight.completion.*
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.Pair
import com.intellij.patterns.ElementPattern
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import com.intellij.util.containers.MultiMap


class AnukarmaCompletionContributor: CompletionContributor(), DumbAware {
    private val myMap: MultiMap<CompletionType, Pair<ElementPattern<out PsiElement>, CompletionProvider<CompletionParameters>>> =
        MultiMap<CompletionType, Pair<ElementPattern<out PsiElement>, CompletionProvider<CompletionParameters>>>()

    private val providers = listOf(
//        BooleanCompletionProvider,
        TaskPropertiesCompletionProvider
    )

    init {
        providers.forEach { extend(it) }
    }

    private fun extend(provider: AnukarmaCompletionProvider) {
//        extend(provider.type, provider.context, provider)
        this.myMap.putValue(provider.type, Pair<ElementPattern<out PsiElement>, CompletionProvider<CompletionParameters>>(provider.context, provider))
    }

    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        val iterator: Iterator<Pair<ElementPattern<out PsiElement>, CompletionProvider<CompletionParameters>>> = myMap[parameters.completionType].iterator()

        var pair: Pair<ElementPattern<out PsiElement>, CompletionProvider<CompletionParameters>>
        var context: ProcessingContext
        while (iterator.hasNext()) {
            pair = iterator.next()
            context = ProcessingContext()
            if ((pair.first as ElementPattern<out PsiElement>).accepts(parameters.originalPosition, context)) {
                (pair.second as CompletionProvider<CompletionParameters>).addCompletionVariants(parameters, context, result)
                if (result.isStopped) {
                    return
                }
            }
        }
    }
}
