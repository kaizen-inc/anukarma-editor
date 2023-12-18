package inc.kaizen.ui.editor.anukarma.language.fix

import com.intellij.codeInsight.intention.impl.BaseIntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class AnukarmaCreatePropertyQuickFix(val key: String): BaseIntentionAction() {

    override fun getFamilyName() = "Anukarma"

    override fun getText() = key

    override fun isAvailable(project: Project, editor: Editor, psiFile: PsiFile?) = true

    override fun invoke(project: Project, editor: Editor, psiFile: PsiFile?) {

    }
}