import java.awt.*
import java.awt.event.*
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JOptionPane
import kotlin.math.max
import kotlin.math.min
import kotlin.system.exitProcess


class Table(title: String) :JFrame(), MouseMotionListener, MouseListener{
    var cellsList :MutableList<Int> = mutableListOf()
    var tableWidth = 1080
    var tableHeight = 720
    var cellsWidth = 300
    var cellsHeight = 300
    var aliveRule = "2 3"
    var deadRule = "3 3"
    private val tableSize : Pair<Int, Int>
        get() = Pair(max(this.size.width*4/5,1), max(this.size.height+1,1))
    private val cellSize : Int
        get() = min(tableSize.first/(cellsWidth+2),tableSize.second/(cellsHeight+2))
    private val tableCenter: Pair<Int,Int>
        get() = Pair(tableSize.first/2,tableSize.second/2)
    private val nullPoint : Pair<Int,Int>
        get() = Pair(tableCenter.first-(cellsWidth/2)*cellSize,tableCenter.second-(cellsHeight/2)*cellSize)
    private val rightDownPoint : Pair<Int,Int>
        get() = Pair(nullPoint.first+cellsWidth*cellSize,nullPoint.second+cellsHeight*cellSize)
    private val buttonSize : Pair<Int,Int>
        get() = Pair((size.width*0.9/5f).toInt(),min(size.width/10f*0.9,height*0.9/TableButtons.values().size).toInt())
    var autoPlay = false
    private val fileChooser = JFileChooser("data")

    private fun createUI(title: String){
        setTitle(title)
        size = Dimension(tableWidth, tableHeight)
        isVisible = true
        layout = null
        addMouseListener(this)
        addMouseMotionListener(this)
        createBufferStrategy(2)
        defaultCloseOperation= DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter(){
            override fun windowClosing(e: WindowEvent?) {
                val clickResult = JOptionPane.showConfirmDialog(this@Table, "Do you want to save it before exit?")
                if (clickResult == JOptionPane.OK_OPTION) {
                    val returnValue = fileChooser.showOpenDialog(null)
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        val selectedFile = fileChooser.selectedFile.absolutePath
                        saveTable(selectedFile,this@Table)
                        isVisible = false
                        dispose()
                    }
                }
                if(clickResult == JOptionPane.NO_OPTION) {
                    isVisible = false
                    dispose()
                }
                exitProcess(0)
            }
        })
        repaint()

        val returnValue = fileChooser.showOpenDialog(null)
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile.absolutePath
            loadTable(selectedFile,this)
        }
        if (returnValue == JFileChooser.CANCEL_OPTION){
            cellsList = MutableList(cellsWidth*cellsHeight) { 0 }
        }
        repaint()

    }

    override fun paint(g: Graphics) {
        var bufferStrategy: BufferStrategy? = bufferStrategy
        if (bufferStrategy == null) {
            createBufferStrategy(2)
            bufferStrategy = getBufferStrategy()
        }
        val g1 = bufferStrategy!!.drawGraphics
        g1.color = Color.white
        g1.fillRect(0, 0, width, height)

        val img = createImage()
        g1.drawImage(img, 0, 0,this)

        g1.dispose()
        bufferStrategy.show()
    }


    private fun createImage(): Image {
        val bufferedImage = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB)
        val g = bufferedImage.graphics
        //drawing field
        g.color = Color.white
        g.fillRect(0,0,size.width, size.height)
        //drawing cells
        val g2d = g as Graphics2D
        g2d.stroke = BasicStroke(1f)
        g.color = Color.black
        for (i in 0..cellsWidth){
            g.drawLine(nullPoint.first+i*cellSize,nullPoint.second,nullPoint.first+i*cellSize,rightDownPoint.second)
        }
        for (i in 0..cellsHeight){
            g.drawLine(nullPoint.first,nullPoint.second+i*cellSize,rightDownPoint.first,nullPoint.second+i*cellSize)
        }
        //drawing aliveCells
        g.color = Color.black
        for(i in cellsList.indices){
            if (cellsList[i]!=0) {
                g.fillRect(nullPoint.first+i%cellsWidth*cellSize,nullPoint.second+i/cellsWidth*cellSize,cellSize,cellSize)
            }
        }
        //drawing generation
        g.color = Color.RED
        if (State.mouseX in nullPoint.first .. rightDownPoint.first && State.mouseY in nullPoint.second .. rightDownPoint.second){
            val cellX = (State.mouseX-nullPoint.first)/cellSize
            val cellY = (State.mouseY-nullPoint.second)/cellSize
            g.drawString(cellsList[cellX+cellY*cellsWidth].toString(),State.mouseX,State.mouseY)
        }
        //drawing buttons
        g.color = Color.blue
        g2d.stroke = BasicStroke(2f)
        for(i in TableButtons.values()){
            g.drawRect(size.width*99/100-buttonSize.first,size.height*19/20-(i.ordinal+1)*buttonSize.second,buttonSize.first,buttonSize.second)
            g.drawString(i.toString(),size.width*99/100-buttonSize.first/2,size.height*19/20-(i.ordinal)*buttonSize.second - buttonSize.second/2)
        }

        return bufferedImage
    }


    init{
        createUI(title)
    }

    private fun click(x:Int, y:Int){
        if (x in nullPoint.first .. rightDownPoint.first && y in nullPoint.second .. rightDownPoint.second){
            val cellX = min((x-nullPoint.first)/cellSize,cellsWidth-1)
            val cellY = min((y-nullPoint.second)/cellSize,cellsHeight-1)
            if(cellsList[cellX+cellY*cellsWidth]!=0) cellsList[cellX+cellY*cellsWidth]=0
            else cellsList[cellX+cellY*cellsWidth]=1
        }
        if (x in size.width*99/100-buttonSize.first..size.width*99/100 && y in size.height*19/20-buttonSize.second..size.height*19/20){
            oneTurn(this)
        }
        if (x in size.width*99/100-buttonSize.first..size.width*99/100 && y in size.height*19/20-2*buttonSize.second..size.height*19/20-buttonSize.second){
            generate(this)
        }
        if (x in size.width*99/100-buttonSize.first..size.width*99/100 && y in size.height*19/20-3*buttonSize.second..size.height*19/20-2*buttonSize.second){
            autoPlay=!autoPlay
        }
        if (x in size.width*99/100-buttonSize.first..size.width*99/100 && y in size.height*19/20-4*buttonSize.second..size.height*19/20-3*buttonSize.second){
            aliveRule = JOptionPane.showInputDialog("Введите два числа через пробел, обозначающие правила для живых клеток")
            deadRule = JOptionPane.showInputDialog("Введите два числа через пробел, обозначающие правила для мертвых клеток")
        }
        if (x in size.width*99/100-buttonSize.first..size.width*99/100 && y in size.height*19/20-5*buttonSize.second..size.height*19/20-4*buttonSize.second){
            val repeatTimes = JOptionPane.showInputDialog("Введите число повторений")
            repeat(repeatTimes.toInt()){
                oneTurn(this)
            }
        }

    }

    override fun mouseDragged(e: MouseEvent) {
    }

    override fun mouseMoved(e: MouseEvent) {
        State.mouseX = e.x
        State.mouseY = e.y
        repaint()
    }

    override fun mouseClicked(e: MouseEvent) {
        click(e.x,e.y)
        repaint()
    }

    override fun mousePressed(e: MouseEvent) {
    }

    override fun mouseReleased(e: MouseEvent) {
    }

    override fun mouseEntered(e: MouseEvent) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }
}

fun playGame(){
    val table = Table("LifeGame")
    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate({
        if (table.autoPlay) {
            oneTurn(table)
            table.repaint()
        }
    }, 0, 100, TimeUnit.MILLISECONDS)
}
enum class TableButtons{
    OneTurn,
    Generate,
    Auto,
    ChangeRules,
    NTurns
}
fun loadTable(path :String,table: Table){
    val reader = File(path).bufferedReader()
    table.aliveRule = reader.readLine()
    table.deadRule = reader.readLine()
    table.cellsWidth = reader.readLine().toInt()
    table.cellsHeight = reader.readLine().toInt()
    table.cellsList = reader.readLines().map{it.split(" ")}.flatMap { it.toList() }.map{ it.toInt() }.toMutableList()
}
fun saveTable(path :String,table:Table){
    val writer = File(path).bufferedWriter()
    writer.write(table.aliveRule)
    writer.newLine()
    writer.write(table.deadRule)
    writer.newLine()
    writer.write(table.cellsWidth.toString())
    writer.newLine()
    writer.write(table.cellsHeight.toString())
    writer.newLine()
    table.cellsList.chunked(table.cellsWidth).forEach { list -> writer.write(list.joinToString(" ")); writer.newLine()}
    writer.close()
}
fun transformation(width:Int,height:Int,table:Table): Int{
    var neighbours :List<Pair<Int,Int>> = listOf()
    for(i in -1..1){
        for(j in -1..1){
            if((i+width) in 0 until table.cellsWidth && (j+height) in 0 until table.cellsHeight) {
                if(table.cellsList[width+i+(height+j)*table.cellsWidth]!=0) neighbours=neighbours.plus(Pair(i+width,j+height))
            }
        }
    }
    neighbours=neighbours.minus(Pair(width,height))
    val amount = neighbours.size
    return if (table.cellsList[width+height*table.cellsWidth]!=0) {
        if(amount in table.aliveRule.split(" ").first().toInt()..table.aliveRule.split(" ").last().toInt()) table.cellsList[width+height*table.cellsWidth]+1
        else 0
    }
    else {
        if(amount in table.deadRule.split(" ").first().toInt()..table.deadRule.split(" ").last().toInt()) 1
        else 0
    }
}
fun oneTurn(table:Table){
    var transformationList :List<Int> = listOf()
    for(i in 0 until table.cellsHeight){
        for(j in 0 until table.cellsWidth)
            transformationList=transformationList.plus(transformation(j,i,table))
    }
    table.cellsList=transformationList.toMutableList()
}
fun generate(table:Table){
    for(i in 0 until table.cellsWidth) {
        for (j in 0 until table.cellsHeight) {
            table.cellsList[i + j * table.cellsWidth]=(0..1).random()
        }
    }
}
object State {
    var mouseX = 0
    var mouseY = 0
}

fun main(args: Array<String>) {
    playGame()
}