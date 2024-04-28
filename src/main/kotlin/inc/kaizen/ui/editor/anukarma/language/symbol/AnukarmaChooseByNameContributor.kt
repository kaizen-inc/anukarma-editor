package inc.kaizen.ui.editor.anukarma.language.symbol

import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.util.containers.ContainerUtil
import inc.kaizen.ui.editor.anukarma.language.util.findProperties

class AnukarmaChooseByNameContributor: ChooseByNameContributor {

    //    override fun processNames(
//        processor: Processor<in String>,
//        scope: GlobalSearchScope,
//        filter: IdFilter?) {
//        val project: Project? = Objects.requireNonNull(scope.project)
//        project?.let {
//            val propertyKeys = ContainerUtil.map(findProperties(project)) { property ->
//                property.node.text
//            }
//            ContainerUtil.process(propertyKeys, processor)
//        }
//    }
//
//    override fun processElementsWithName(
//        name: String,
//        processor: Processor<in NavigationItem>,
//        parameters: FindSymbolParameters) {
//
//        val propertyKeys = ContainerUtil.map(findProperties(parameters)) {
//            it as NavigationItem
//        }
////        ContainerUtil.process(propertyKeys, processor)
//        propertyKeys.forEach {
//            val processed = processor.process(it)
//            if (!processed) {
//                return@forEach
//            }
//        }
//    }
    override fun getNames(project: Project, includeNonProjectItems: Boolean): Array<String> {
        val propertyKeys = ContainerUtil.map(findProperties(project)) { property ->
            property.node.text
        }
        return propertyKeys.toTypedArray()
    }

    override fun getItemsByName(name: String,
                                pattern: String,
                                project: Project,
                                includeNonProjectItems: Boolean): Array<NavigationItem> {
        val result: MutableList<NavigationItem> = mutableListOf()
        ContainerUtil.map(findProperties(pattern, project)) {
            println(it)
            result.add(it as NavigationItem)
        }
        return result.toTypedArray()
    }
}