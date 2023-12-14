package inc.kaizen.ui.editor.anukarma.language.format

import com.intellij.formatting.Alignment
import com.intellij.formatting.FormattingContext
import com.intellij.formatting.FormattingModel
import com.intellij.formatting.FormattingModelBuilder
import com.intellij.formatting.FormattingModelProvider
import com.intellij.formatting.SpacingBuilder
import com.intellij.formatting.Wrap
import com.intellij.formatting.WrapType
import com.intellij.psi.codeStyle.CodeStyleSettings
import inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType
import inc.kaizen.ui.editor.anukarma.language.AnukarmaLanguage
import org.jetbrains.annotations.NotNull

class AnukarmaFormattingModelBuilder: FormattingModelBuilder {

    private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
        return SpacingBuilder(settings, AnukarmaLanguage)
            .around(IAnukarmaLanguageFileType.SEPARATOR)
            .spaceIf(settings.getCommonSettings(AnukarmaLanguage.id).SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .before(IAnukarmaLanguageFileType.PROPERTY)
            .none()
    }

    @NotNull
    override fun createModel(@NotNull formattingContext: FormattingContext): FormattingModel {
        val codeStyleSettings = formattingContext.codeStyleSettings
        return FormattingModelProvider
            .createFormattingModelForPsiFile(
                formattingContext.containingFile,
                AnukarmaBlock(
                    formattingContext.node,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    createSpaceBuilder(codeStyleSettings)
                ),
                codeStyleSettings
            )
    }
}