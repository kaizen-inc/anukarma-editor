package inc.kaizen.ui.editor.anukarma.language.marker

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import inc.kaizen.ui.editor.anukarma.language.icon.AnukarmaIcons

class AnukarmaLineMarkerProvider: RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(
        elements: MutableList<out PsiElement>,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>,
        forNavigation: Boolean
    ) {
        elements.forEach { element ->
            if (element is LeafPsiElement) {
                if ("plugins".contentEquals(element.text)) {
                    val builder: NavigationGutterIconBuilder<PsiElement> =
                        NavigationGutterIconBuilder
                            .create(AnukarmaIcons.PLUGIN)
                            .setTargets(element)
                            .setTooltipText("Plugin")
                    result.add(builder.createLineMarkerInfo(element))
                }
                if ("stopOnFail".contentEquals(element.text) ||
                    "incremental".contentEquals(element.text) ||
                    "generateCode".contentEquals(element.text)
                ) {
                    val builder: NavigationGutterIconBuilder<PsiElement> =
                        NavigationGutterIconBuilder.create(AnukarmaIcons.PARAMETER)
                            .setTargets(element)
                            .setTooltipText("Property")
                    result.add(builder.createLineMarkerInfo(element))
                }
            }
        }
    }

    override fun getLineMarkerInfo(element: PsiElement): RelatedItemLineMarkerInfo<*>? {
        return if ("stopOnFail".contentEquals(element.text) ||
            "incremental".contentEquals(element.text) ||
            "generateCode".contentEquals(element.text) ||
            "plugins".contentEquals(element.text)) {
            super.getLineMarkerInfo(element)
        } else {
            null
        }
    }
}