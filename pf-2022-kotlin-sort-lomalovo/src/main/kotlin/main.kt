import java.io.File
import kotlin.Comparator
fun main(args: Array<String>) {
    val allFlags = arrayOf("-n","--numeric-sort","-v","--version-sort","-f","--ignore-case","-r","--reverse","-o","output","-u","--unique") //ввожу массив из нужных мне флагов в нужном порядке, позже понадобится при определении порядка компараторов
    var comp: Comparator<String> = Comparator{o1,o2 ->
        when {
            o1<o2 -> -1
            o1==o2 -> 0
            else -> 1
        }
    }
    //стандартная sort
    var files : Array<String> = arrayOf()
    var flagsArrayUnsorted : Array<String> = arrayOf()
    var flagsArraySorted : Array<String> = arrayOf()
    val arguments = if (args.isNotEmpty()) {
        args
    } else {
        throw Exception("You didn't enter anything in the arguments")
    }
    for (arg in arguments){
        if (arg.matches(Regex("-.+"))){
            if (arg.matches(Regex("(-[frunvo])|(--(ignore-case|reverse|unique|numeric-sort|version-sort|output))"))) flagsArrayUnsorted=flagsArrayUnsorted.plus(arg) //делаю проверку на флаг
            else throw Exception("Flag $arg is incorrect")
            }
        else if(File(arg).exists()) files=files.plus(arg) //проверка на файл
        else throw Exception("File $arg is not found")
    }
    for(flag in allFlags){
        if (flag in flagsArrayUnsorted) flagsArraySorted=flagsArraySorted.plus(flag) //сортируем флаги
    }
    if(inFlags("-n","--numeric-sort",flagsArraySorted) and inFlags("-v","--version-sort",flagsArraySorted)) throw Exception("Flag -n and Flag -v is incompatible")
    mergeFilesToFile(files)
    //последовательный вызов компараторов
    for (flagString in flagsArraySorted){
        for(flag in Flag.values()){
            if ((flag.shortArg == flagString) or (flag.longArg == flagString)){
                comp = flag.comp.invoke(comp)
            }
        }
    }

    var finalArray = File("mergedFiles.txt").readLines().sortedWith(comp)
    if(inFlags("-u","--unique",flagsArraySorted)){finalArray=finalArray.toSet().toList()}
    //проверка на флаг -o
    if(inFlags("-o","--output",flagsArraySorted)){
        val writer = File("outputFile.txt").bufferedWriter()
        for (line in finalArray){
            writer.write(line)
            writer.newLine()
        }
        writer.close()
    }
    else {
        for (line in finalArray) {
            print("$line\n")
        }
    }
}
fun mergeFilesToFile(inputFileNames: Array<String>){
    val writer = File("mergedFiles.txt").bufferedWriter()
    for (inputFileName in inputFileNames) {
        for (line in File(inputFileName).readLines()) {
            writer.write(line)
            writer.newLine()
        }
    }
    writer.close()

} //функция для слива файлов в один
fun inFlags (shortArg:String, longArg:String, inArray:Array<String>): Boolean{
    return (shortArg in inArray) or (longArg in inArray)
}