package inc.kaizen.ui.editor.anukarma.language.settings

import com.intellij.icons.AllIcons
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import inc.kaizen.ui.editor.anukarma.language.highlight.AnukarmaSyntaxHighlighter

class AnukarmaColorSettingsPage: ColorSettingsPage {

    private val descriptors = arrayOf(
//        AttributesDescriptor("Anukarma//Key", AnukarmaSyntaxHighlighter.KEY),
        AttributesDescriptor("Key", AnukarmaSyntaxHighlighter.KEY),
        AttributesDescriptor("Separator", AnukarmaSyntaxHighlighter.SEPARATOR),
        AttributesDescriptor("Value", AnukarmaSyntaxHighlighter.VALUE),
//        AttributesDescriptor("Bad value", AnukarmaSyntaxHighlighter.BAD_CHARACTER)
    )
    
    override fun getAttributeDescriptors() = descriptors

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = "Anukarma"

    override fun getIcon() = AllIcons.General.Settings

    override fun getHighlighter() = AnukarmaSyntaxHighlighter()

    override fun getDemoText(): String {
        return "# You are reading the \".properties\" entry.\n" +
                "! The exclamation mark can also mark text as comments.\n" +
                "website = https://en.wikipedia.org/\n" +
                "language = English\n" +
                "# The backslash below tells the application to continue reading\n" +
                "# the value onto the next line.\n" +
                "message = Welcome to \\\n" +
                "          Wikipedia!\n" +
                "# Add spaces to the key\n" +
                "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
                "# Unicode\n" +
                "tab : \\u0009"
    }

    override fun getAdditionalHighlightingTagToDescriptorMap() = null
}