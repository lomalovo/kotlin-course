import kastree.ast.Node
import kastree.ast.Visitor
import org.junit.Assert
import org.junit.Test
import kastree.ast.psi.Parser
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

class TestFlatMap {
    @Test fun testGetOrderedProductsSet() {
        Assert.assertEquals(
            "getOrderedProducts".toMessage(),
            setOf(idea), customers[reka]!!.getOrderedProducts()
        )
        Assert.assertEquals(
            "getOrderedProducts".toMessage(),
            setOf(reSharper), customers[bajram]!!.getOrderedProducts()
        )
    }

    private val orderedProducts = setOf(idea, reSharper, dotTrace, dotMemory, rubyMine, webStorm)

    @Test fun testGetAllOrderedProducts() {
        Assert.assertEquals(
            "getAllOrderedProducts".toMessage(),
            orderedProducts, shop.getAllOrderedProducts()
        )
        Assert.assertEquals(
            "getAllOrderedProducts".toMessage(),
            setOf(idea), Shop("other", listOf(customers[asuka]!!)).getAllOrderedProducts()
        )
    }

    @Test
    fun testCodeReuse() {
        val parser = Parser().myParseFile(File("src/main/kotlin/Task04.kt").readLines().joinToString(separator = "\n"))
        val func = parser.decls.filterIsInstance<Node.Decl.Func>()
            .singleOrNull { it.name == "getAllOrderedProducts" }
            ?: error("Не найдена функция getAllOrderedProducts")

        var reusesCode = false
        Visitor.visit(func) { v, parent ->
            if (v is Node.Expr.Call && (v.expr as? Node.Expr.Name)?.name == "getOrderedProducts") {
                reusesCode = true
            }
        }
        Assert.assertTrue("Функция getAllOrderedProducts должна вызывать getOrderedProducts", reusesCode)
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

    val parseResult =
        PsiManager.getInstance(project).findFile(LightVirtualFile("temp.kt", KotlinFileType.INSTANCE, code)) as KtFile
    return converter.convertFile(parseResult.also { file ->
        if (throwOnError) file.collectDescendantsOfType<PsiErrorElement>().let {
            if (it.isNotEmpty()) throw Parser.ParseError(file, it)
        }
    })
}
