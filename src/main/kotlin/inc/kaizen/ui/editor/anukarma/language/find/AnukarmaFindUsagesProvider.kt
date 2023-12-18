package inc.kaizen.ui.editor.anukarma.language.find

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.elementType
import inc.kaizen.ui.editor.anukarma.language.AnukarmaLexerAdapter
import inc.kaizen.ui.editor.anukarma.language.COMMENTS
import inc.kaizen.ui.editor.anukarma.language.TASK

class AnukarmaFindUsagesProvider: FindUsagesProvider {

    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(AnukarmaLexerAdapter(),
            TASK,
            COMMENTS,
            TokenSet.EMPTY)
    }
    override fun canFindUsagesFor(element: PsiElement) = element is PsiNamedElement

    override fun getHelpId(element: PsiElement) = null

    override fun getType(element: PsiElement) = element.elementType.toString()

    override fun getDescriptiveName(element: PsiElement) = element.text.toString()

    override fun getNodeText(element: PsiElement, useFullName: Boolean) = element.text.toString()
}