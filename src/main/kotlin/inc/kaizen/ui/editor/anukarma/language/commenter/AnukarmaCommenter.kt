package inc.kaizen.ui.editor.anukarma.language.commenter

import com.intellij.lang.Commenter

class AnukarmaCommenter: Commenter {

    override fun getLineCommentPrefix() = "//"

    override fun getBlockCommentPrefix() = "/*"

    override fun getBlockCommentSuffix() = "*/"

    override fun getCommentedBlockCommentPrefix() = ""

    override fun getCommentedBlockCommentSuffix() = ""
}