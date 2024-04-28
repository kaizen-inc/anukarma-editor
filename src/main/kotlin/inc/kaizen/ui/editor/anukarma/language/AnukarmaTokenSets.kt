package inc.kaizen.ui.editor.anukarma.language

import com.intellij.psi.tree.TokenSet

var COMMENTS: TokenSet = TokenSet.create(IAnukarmaLanguageFileType.COMMENT)
var TASK: TokenSet = TokenSet.create(IAnukarmaLanguageFileType.TASK_SECTION)
var ID: TokenSet = TokenSet.create(IAnukarmaLanguageFileType.ID)