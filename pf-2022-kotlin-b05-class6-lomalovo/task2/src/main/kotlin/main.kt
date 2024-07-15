import java.io.BufferedWriter
import java.io.File
import java.time.LocalTime
import kotlin.math.roundToInt
import kotlin.random.Random
const val n = 1000000 //number of lines
fun main(args: Array<String>) {
    val tmpFile = kotlin.io.path.createTempFile().toFile()
    createLogFile(tmpFile,n)
    print(analys(tmpFile).toString() +" nanoseconds")
}

fun generateFile(file: File,n: Int){
    val writer = file.bufferedWriter()
    repeat(n){
        writer.write(List(2){ Random.nextInt(1,1000000000)}.joinToString(" "))
        writer.newLine()
    }
    writer.close()
}
fun gcd(int1:String, int2:String, logger:BufferedWriter): Int{
    val time1 = LocalTime.now()
    var n1 = int1.toInt()
    var n2 = int2.toInt()
    while (n1 != n2) {
        if (n1 > n2)
            n1 -= n2
        else
            n2 -= n1
    }
    val time2 = LocalTime.now()
    val seconds1 = time1.toString().split(":").last().toDouble()
    val seconds2 = time2.toString().split(":").last().toDouble()
    logger.write("GCD ($int1, $int2) is ended, it takes: ${((seconds2-seconds1)*1000000).roundToInt()} nanoseconds")
    logger.newLine()
    return n1
}
fun createLogFile(file:File,n:Int){
    val logger = File("log.txt").bufferedWriter()
    generateFile(file,n)
    file.forEachLine{
        gcd(it.split(" ").first(),it.split(" ").last(),logger)
    }
    logger.close()
}
fun analys(file:File):Double{
    var summ = 0.0
    file.forEachLine {
        summ+=it.split(" ").dropLast(1).last().toInt()
    }
    return summ / n
}