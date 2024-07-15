import java.io.File
import java.nio.charset.Charset

fun main(args: Array<String>) {
    val inputFile = args.first()
    for (arg in args.drop(1)) {
        if(arg !in Charset.availableCharsets()) throw Exception("Кодировки \"$arg\" не существует, или Вы ввели её в неправильном формате")
    }
    val file = File(inputFile).readLines(charset=Charset.forName(args[1]))
    val writer = File(inputFile).bufferedWriter(charset=Charset.forName(args[2]))
    for (line in file.dropLast(1)){
        writer.write(line)
        writer.newLine()
    }
    writer.write(file.last())
    writer.close()
}