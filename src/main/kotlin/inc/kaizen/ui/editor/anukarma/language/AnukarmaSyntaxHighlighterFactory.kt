package inc.kaizen.ui.editor.anukarma.language

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class AnukarmaSyntaxHighlighterFactory: SyntaxHighlighterFactory() {

    override fun getSyntaxHighlighter(project: Project?, file: VirtualFile?) = AnukarmaSyntaxHighlighter()
}