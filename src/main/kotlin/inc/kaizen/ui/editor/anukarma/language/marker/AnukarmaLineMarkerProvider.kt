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
                }else if ("stopOnFail".contentEquals(element.text) ||
                    "incremental".contentEquals(element.text) ||
                    "generateCode".contentEquals(element.text) ||
                    "implementation".contentEquals(element.text) ||
                    "group".contentEquals(element.text) ||
                    "priority".contentEquals(element.text) ||
                    "strictDependency".contentEquals(element.text)
                ) {
                    val builder: NavigationGutterIconBuilder<PsiElement> =
                        NavigationGutterIconBuilder.create(AnukarmaIcons.PARAMETER)
                            .setTargets(element)
                            .setTooltipText("Property")
                    result.add(builder.createLineMarkerInfo(element))
                } else if ("extensions".contentEquals(element.text)) {
                    val builder: NavigationGutterIconBuilder<PsiElement> =
                        NavigationGutterIconBuilder.create(AnukarmaIcons.EXTENSION)
                            .setTargets(element)
                            .setTooltipText("Extension")
                    result.add(builder.createLineMarkerInfo(element))
                } else if ("task".contentEquals(element.text)) {
                    val builder: NavigationGutterIconBuilder<PsiElement> =
                        NavigationGutterIconBuilder.create(AnukarmaIcons.TASK)
                            .setTargets(element)
                            .setTooltipText("Extension")
                    result.add(builder.createLineMarkerInfo(element))
                } else if ("feature".contentEquals(element.text)) {
                    val builder: NavigationGutterIconBuilder<PsiElement> =
                        NavigationGutterIconBuilder.create(AnukarmaIcons.FEATURE)
                            .setTargets(element)
                            .setTooltipText("Extension")
                    result.add(builder.createLineMarkerInfo(element))
                }
            }
        }
    }

    override fun getLineMarkerInfo(element: PsiElement): RelatedItemLineMarkerInfo<*>? {
        return if ("stopOnFail".contentEquals(element.text) ||
            "incremental".contentEquals(element.text) ||
            "generateCode".contentEquals(element.text) ||
            "implementation".contentEquals(element.text) ||
            "group".contentEquals(element.text) ||
            "priority".contentEquals(element.text) ||
            "strictDependency".contentEquals(element.text) ||
            "extensions".contentEquals(element.text) ||
            "task".contentEquals(element.text) ||
            "feature".contentEquals(element.text) ||
            "plugins".contentEquals(element.text)) {
            super.getLineMarkerInfo(element)
        } else {
            null
        }
    }
}