import java.io.File

/**
 * Напишите программу, считывающую текстовый файл, путь к которому указан в первом аргументе,
 * и выводящую все строки, в которых сумма целых чисел равна нулю.
 *
 * Целые числа в строке могут быть разделены табуляциями или пробелами, в том числе несколькими.
 *
 * Строки, начинающиеся с символа `#` нужно игнорировать.
 * Строки с некорректными данными нужно игнорировать.
 */
fun main(args: Array<String>) {
    val reader = File(args.first()).bufferedReader()
    for (string in reader.readLines()){
        if (string.firstOrNull()!='#' || string.firstOrNull()!=null) {
            val list = string.split("\\s+".toRegex()).map { it.toIntOrNull() }
            if (list.isNotEmpty() && list.all{it!=null} && list.mapNotNull { it }.sum() == 0) println(string)
        }
    }
    reader.close()
}
