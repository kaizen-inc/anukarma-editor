package inc.kaizen.ui.editor.anukarma.language.format

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable


class AnukarmaBlock(@NotNull node: ASTNode,
                    @Nullable wrap: Wrap,
                    @Nullable alignment: Alignment,
                    private val spacingBuilder: SpacingBuilder): AbstractBlock(node, wrap, alignment) {

    override fun buildChildren(): List<Block> {
        val blocks: MutableList<Block> = ArrayList()
        var child = myNode.firstChildNode
        while (child != null) {
            if (child.elementType !== TokenType.WHITE_SPACE) {
                val block: Block = AnukarmaBlock(
                    child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(),
                    spacingBuilder
                )
                blocks.add(block)
            }
            child = child.treeNext
        }
        return blocks
    }

    override fun getIndent(): Indent {
        return when (myNode.elementType) {
            IAnukarmaLanguageFileType.CONTENT -> {
                Indent.getNoneIndent()
            }
            IAnukarmaLanguageFileType.FILE,
            IAnukarmaLanguageFileType.PLUGIN_ID,
            IAnukarmaLanguageFileType.TASK_CONTENT,
            IAnukarmaLanguageFileType.FEATURE_CONTENT,
            IAnukarmaLanguageFileType.FEATURE_DEPENDENCY_ID,
            IAnukarmaLanguageFileType.TASK_DEPENDENCY_ID-> {
                Indent.getNormalIndent(true)
            }
            else -> {
                Indent.getNoneIndent()
            }
        }
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }

}