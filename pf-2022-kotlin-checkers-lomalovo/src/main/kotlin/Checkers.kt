import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.swing.Swing
import org.jetbrains.skija.*
import org.jetbrains.skiko.SkiaLayer
import org.jetbrains.skiko.SkiaRenderer
import org.jetbrains.skiko.SkiaWindow
import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseAdapter
import java.io.File
import javax.swing.WindowConstants
import kotlin.time.ExperimentalTime

fun main() {
    clearTable()
    createWindow("Checkers")
    while(!gameEnded){
        move()
    }
}

fun createWindow(title: String) = runBlocking(Dispatchers.Swing) {
    val window = SkiaWindow()
    window.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
    window.title = title

    window.layer.renderer = Renderer(window.layer)
    window.layer.addMouseListener(MouseAdapter)

    window.preferredSize = Dimension(1008, 750)
    window.minimumSize = Dimension(1008,750)

    window.pack()
    window.layer.awaitRedraw()
    window.isVisible = true
}

class Renderer(private val layer: SkiaLayer): SkiaRenderer {
    private val typeface = Typeface.makeFromFile("fonts/Boncegro.otf")
    private val font = Font(typeface, 72f)
    private val fontPaintFirst = Paint().apply {
        color = 0xFFFF7F50.toInt()
        mode = PaintMode.FILL
        strokeWidth = 1f
    }
    private val fontPaintSecond = Paint().apply {
        color = 0xFF00538A.toInt()
        mode = PaintMode.FILL
        strokeWidth = 1f
    }

    @ExperimentalTime
    override fun onRender(canvas: Canvas, width: Int, height: Int, nanoTime: Long) {
        val contentScale = layer.contentScale
        canvas.scale(contentScale, contentScale)

        val w = (width / contentScale).toInt()
        val h = (height / contentScale).toInt()
        val centerX = w/2f
        val centerY = h/2f
        val leftTopTableX = centerX-6*72f
        val leftTopTableY = centerY-4*72f
        val rightDownTableX = centerX+2*72f
        val rightDownTableY = centerY+4*72f
        currentCell(leftTopTableX, leftTopTableY, rightDownTableX, rightDownTableY)

        displayBoard(canvas, centerX, centerY)
        displayTurn(canvas, centerX, centerY)
        displayCheckers(canvas, leftTopTableX, leftTopTableY)


        layer.needRedraw()
    }

    private fun displayBoard(canvas: Canvas, centerX: Float, centerY: Float) {
        val image = Image.makeFromEncoded(File("data/deck.png").readBytes())
        canvas.drawImage(image,centerX-504f,centerY-360f)
    }
    private fun drawChecker(left: Float, top: Float,canvas: Canvas,path:String){
        val image = Image.makeFromEncoded(File(path).readBytes())
        canvas.drawImage(image,left+1.5f,top+1.5f)

    }
    private fun displayCheckers(canvas: Canvas,leftTopTableX:Float,leftTopTableY: Float){
        for(x in 0..7){
            for(y in 0..7) {
                when(table[x][y]) {
                    Checker.First -> drawChecker(leftTopTableX + 72f * x, leftTopTableY + 72f * y, canvas,"data/First.png")
                    Checker.Second -> drawChecker(leftTopTableX + 72f * x, leftTopTableY + 72f * y, canvas,"data/Second.png")
                    Checker.FirstQueen -> drawChecker(leftTopTableX + 72f * x, leftTopTableY + 72f * y, canvas,"data/FirstQueen.png")
                    Checker.SecondQueen -> drawChecker(leftTopTableX + 72f * x, leftTopTableY + 72f * y, canvas,"data/SecondQueen.png")
                    Checker.MoveMaybe -> drawChecker(leftTopTableX + 72f * x, leftTopTableY + 72f * y, canvas,"data/moveMaybe.png")
                    else -> {}
                }
            }
        }
    }
    private fun currentCell(leftTopTableX: Float,leftTopTableY: Float,rightDownTableX:Float,rightDownTableY:Float){
        current = if (State.mouseX in leftTopTableX..rightDownTableX && State.mouseY in leftTopTableY..rightDownTableY){
            ((State.mouseX - leftTopTableX) / 72).toInt() to ((State.mouseY - leftTopTableY) / 72).toInt()
        } else{
            -1 to -1
        }
    }
    private fun displayTurn(canvas: Canvas ,centerX: Float ,centerY: Float){
        if(!gameEnded) {
            if (turn == Turn.First) canvas.drawString("First", centerX + 245, centerY - 144, font, fontPaintFirst)
            else canvas.drawString("Second", centerX + 225, centerY - 144, font, fontPaintSecond)
        }
        else {
            if(countFirst==12) canvas.drawString("First won", centerX + 190, centerY - 144, font, fontPaintFirst)
            else canvas.drawString("Second won", centerX + 165, centerY - 144, font, fontPaintSecond)
        }
    }
}

object State {
    var mouseX = 0f
    var mouseY = 0f
}

object MouseAdapter : MouseAdapter() {
    override fun mouseClicked(event: MouseEvent) {
        State.mouseX = event.x.toFloat()
        State.mouseY = event.y.toFloat()
    }
    override fun mousePressed(event: MouseEvent) {
        State.mouseX = event.x.toFloat()
        State.mouseY = event.y.toFloat()
    }
}

