package inc.kaizen.ui.editor.anukarma.language.util

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.ProjectScope
import inc.kaizen.ui.editor.anukarma.language.AnukarmaFile
import inc.kaizen.ui.editor.anukarma.language.INSTANCE


//class AnukarmaUtil {

fun findProperties(key: String, project: Project): MutableList<PsiElement> {
    val result: MutableList<PsiElement> = ArrayList<PsiElement>()
//    val projectFileIndex = ProjectFileIndex.getInstance(parameters.project)
    try {
        val virtualFiles = FileTypeIndex.getFiles(INSTANCE, ProjectScope.getProjectScope(project))
        virtualFiles.forEach { virtualFile: VirtualFile ->
            val simpleFile: PsiFile? = PsiManager.getInstance(project).findFile(virtualFile)
            if (simpleFile != null) {
                val properties: MutableList<PsiElement> = mutableListOf()
                getChildrenOfTypeAsList(
                    simpleFile,
                    PsiElement::class.java,
                    properties
                )
                for (property in properties) {
                    if (property !is PsiWhiteSpace
                        && key == property.node.text) {
                        println(property.node.text)
                        result.add(property)
                    }
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}

fun findProperties(project: Project): MutableList<PsiElement> {
    val properties: MutableList<PsiElement> = mutableListOf()
    val virtualFiles = FileTypeIndex.getFiles(INSTANCE, ProjectScope.getProjectScope(project))
    for (virtualFile in virtualFiles) {
        val simpleFile: AnukarmaFile? = PsiManager.getInstance(project).findFile(virtualFile!!) as AnukarmaFile?
        if (simpleFile != null) {
            getChildrenOfTypeAsList(
                simpleFile,
                PsiElement::class.java,
                properties
            )
        }
    }
    return properties
}

fun <T : PsiElement?> getChildrenOfTypeAsList(
    element: PsiElement?,
    aClass: Class<out T>,
    collector: MutableList<PsiElement>
) {
    element?.children?.forEach { child ->
        if (child !is PsiWhiteSpace && aClass.isInstance(child)) {
            collector.add(aClass.cast(child) as PsiElement)
        }
        getChildrenOfTypeAsList(child, aClass, collector)
    }
}