package inc.kaizen.ui.editor.anukarma.language.fold
//
//import com.intellij.lang.ASTNode
//import com.intellij.lang.folding.FoldingBuilderEx
//import com.intellij.lang.folding.FoldingDescriptor
//import com.intellij.openapi.editor.Document
//import com.intellij.openapi.editor.FoldingGroup
//import com.intellij.openapi.project.DumbAware
//import com.intellij.openapi.util.text.StringUtil
//import com.intellij.psi.PsiElement
//import com.intellij.util.containers.ContainerUtil
//import inc.kaizen.ui.editor.anukarma.language.AnukarmaProperty
//
//
//class AnukarmaFoldingBuilder: FoldingBuilderEx(), DumbAware {
//
//    override fun buildFoldRegions(node: PsiElement,
//                                  document: Document,
//                                  p2: Boolean): Array<FoldingDescriptor> {
//        val group = FoldingGroup.newGroup(SimpleAnnotator.SIMPLE_PREFIX_STR)
//
//        // Initialize the list of folding regions
//        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
//
//        root.accept(object : JavaRecursiveElementWalkingVisitor() {
//            fun visitLiteralExpression(@NotNull literalExpression: PsiLiteralExpression) {
//                super.visitLiteralExpression(literalExpression)
//
//                val value: String = PsiLiteralUtil.getStringLiteralContent(literalExpression)
//                if (value != null && value.startsWith(SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)) {
//                    val project: Project = literalExpression.getProject()
//                    val key: String = value.substring(
//                        SimpleAnnotator.SIMPLE_PREFIX_STR.length() + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length()
//                    )
//                    // find SimpleProperty for the given key in the project
//                    val simpleProperty: SimpleProperty =
//                        ContainerUtil.getOnlyItem(SimpleUtil.findProperties(project, key))
//                    if (simpleProperty != null) {
//                        // Add a folding descriptor for the literal expression at this node.
//                        descriptors.add(
//                            FoldingDescriptor(
//                                literalExpression.getNode(),
//                                TextRange(
//                                    literalExpression.getTextRange().getStartOffset() + 1,
//                                    literalExpression.getTextRange().getEndOffset() - 1
//                                ),
//                                group, setOf(simpleProperty)
//                            )
//                        )
//                    }
//                }
//            }
//        })
//
//        return descriptors.toArray(FoldingDescriptor.EMPTY)
//    }
//
//    override fun getPlaceholderText(node: ASTNode): String? {
//        if (node.psi is PsiLiteralExpression) {
//            val text: String = PsiLiteralUtil.getStringLiteralContent(psiLiteralExpression) ?: return null
//
//            val key: String = text.substring(
//                SimpleAnnotator.SIMPLE_PREFIX_STR.length() +
//                        SimpleAnnotator.SIMPLE_SEPARATOR_STR.length()
//            )
//
//            val simpleProperty: AnukarmaProperty = ContainerUtil.getOnlyItem(
//                SimpleUtil.findProperties(psiLiteralExpression.getProject(), key)
//            )
//            if (simpleProperty == null) {
//                return StringUtil.THREE_DOTS
//            }
//
//            val propertyValue: String = simpleProperty.getValue() ?: return StringUtil.THREE_DOTS
//            // IMPORTANT: keys can come with no values, so a test for null is needed
//            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look
//            // like it has LF embedded in it and embedded " to escaped "
//
//            return propertyValue.replace("\n".toRegex(), "\\n").replace("\"".toRegex(), "\\\\\"")
//        }
//
//        return null
//    }
//
//    override fun isCollapsedByDefault(node: ASTNode) = true
//}