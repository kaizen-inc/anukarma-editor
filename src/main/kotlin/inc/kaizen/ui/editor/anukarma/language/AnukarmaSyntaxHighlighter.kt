package inc.kaizen.ui.editor.anukarma.language

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType


class AnukarmaSyntaxHighlighter: SyntaxHighlighterBase() {
    companion object {
        val SEPARATOR: TextAttributesKey = createTextAttributesKey("SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEY: TextAttributesKey = createTextAttributesKey("KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val VALUE: TextAttributesKey = createTextAttributesKey("VALUE", DefaultLanguageHighlighterColors.STRING)
        val BRACES: TextAttributesKey = createTextAttributesKey("BRACES", DefaultLanguageHighlighterColors.BRACES)
        val COMMENT: TextAttributesKey = createTextAttributesKey("COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
//        val BAD_CHARACTER: TextAttributesKey = createTextAttributesKey("BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    }

//    private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
    private val KEY_KEYS = arrayOf(KEY)
    private val VALUE_KEYS = arrayOf(VALUE)

    private val COMMENT_KEYS = arrayOf(COMMENT)
    private val EMPTY_KEYS = arrayOfNulls<TextAttributesKey>(0)
    private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
    private val BRACES_KEYS = arrayOf(BRACES)

    override fun getHighlightingLexer() = AnukarmaLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey?> {
        if (tokenType == IAnukarmaLanguageFileType.SEPARATOR) {
            return SEPARATOR_KEYS
        }
        if (tokenType == IAnukarmaLanguageFileType.COMMENT) {
            return COMMENT_KEYS
        }

        if (tokenType == IAnukarmaLanguageFileType.KEY ||
            tokenType == IAnukarmaLanguageFileType.STOPONFAIL ||
            tokenType == IAnukarmaLanguageFileType.INCREMENTAL ||
            tokenType == IAnukarmaLanguageFileType.GENERATECODE ||
            tokenType == IAnukarmaLanguageFileType.PLUGIN_ID ||
            tokenType == IAnukarmaLanguageFileType.PLUGINS ||
            tokenType == IAnukarmaLanguageFileType.ID) {
            return KEY_KEYS
        }
        if (tokenType == IAnukarmaLanguageFileType.BOOLEAN ||
            tokenType == IAnukarmaLanguageFileType.IDENTIFIER ||
            tokenType == IAnukarmaLanguageFileType.QUOTE) {
            return VALUE_KEYS
        }
        if (tokenType == IAnukarmaLanguageFileType.OPEN_BRACKET ||
            tokenType == IAnukarmaLanguageFileType.CLOSE_BRACKET) {
            return BRACES_KEYS
        }
//        if (tokenType == IAnukarmaLanguageFileType.BAD_CHAR_KEYS) {
//            return BAD_CHAR_KEYS
//        }
        return EMPTY_KEYS
    }
}