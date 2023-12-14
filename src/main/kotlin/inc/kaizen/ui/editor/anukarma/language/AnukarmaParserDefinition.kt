package inc.kaizen.ui.editor.anukarma.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class AnukarmaParserDefinition: ParserDefinition {

    val FILE: IFileElementType = IFileElementType(AnukarmaLanguage)

    override fun createLexer(project: Project?) = AnukarmaLexerAdapter()

    override fun createParser(project: Project?) = AnukarmaParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode?): PsiElement = IAnukarmaLanguageFileType.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = AnukarmaFile(viewProvider)
}