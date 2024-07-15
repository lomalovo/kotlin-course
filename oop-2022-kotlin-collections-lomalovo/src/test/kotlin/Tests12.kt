import kastree.ast.Node
import kastree.ast.Visitor
import kastree.ast.psi.Parser
import org.junit.Test
import org.junit.Assert
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.PsiErrorElement
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.psiUtil.collectDescendantsOfType
import java.io.File

class TestExtensionsOnCollections {
    @Test fun testCollectionOfOneElement() {
        doTest(listOf("a"), listOf("a"))
    }

    @Test fun testSimpleCollection() {
        doTest(listOf("a", "c"), listOf("a", "bb", "c"))
    }

    @Test fun testCollectionWithEmptyStrings() {
        doTest(listOf("", "", "", ""), listOf("", "", "", "", "a", "bb", "ccc", "dddd"))
    }

    @Test fun testCollectionWithTwoGroupsOfMaximalSize() {
        doTest(listOf("a", "c"), listOf("a", "bb", "c", "dd"))
    }

    @Test fun testNoForAndForEachLoops() {
        val func =
            Parser().myParseFile(File("src/main/kotlin/Task12.kt").readLines().joinToString(separator = "\n")).decls
                .find { it is Node.Decl.Func && it.name == "doSomethingStrangeWithCollection" }
                ?: error("Не найдена функция 'doSomethingStrangeWithCollection'")

        Visitor.visit(func) { v, parent ->
            if (v is Node.Expr.For) {
                Assert.fail("Ожидается, что в реализации не используется цикл for.")
            }
            if (v is Node.Expr.Call && (v.expr as? Node.Expr.Name)?.name == "forEach") {
                Assert.fail("Ожидается, что в реализации не используется функция forEach { ... }")
            }
        }
    }

    private fun doTest(expected: Collection<String>?, argument: Collection<String>) {
        Assert.assertEquals("The function 'doSomethingStrangeWithCollection' should do at least something with a collection".inEquals(),
            expected, doSomethingStrangeWithCollection(argument))
    }
}

private fun Parser.myParseFile(code: String, throwOnError: Boolean = true): Node.File {
    val configuration = CompilerConfiguration()
    configuration.put(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)

    val project = KotlinCoreEnvironment.createForProduction(
        Disposer.newDisposable(),
        configuration,
        EnvironmentConfigFiles.JVM_CONFIG_FILES
    ).project

    val parseResult = PsiManager.getInstance(project).findFile(LightVirtualFile("temp.kt", KotlinFileType.INSTANCE, code)) as KtFile
    return converter.convertFile(parseResult.also { file ->
        if (throwOnError) file.collectDescendantsOfType<PsiErrorElement>().let {
            if (it.isNotEmpty()) throw Parser.ParseError(file, it)
        }
    })
}
