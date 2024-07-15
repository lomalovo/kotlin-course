import mu.KotlinLogging
import java.io.File
import java.lang.NumberFormatException

fun applyOperation(op: Char, arg1: Int, arg2: Int): Int {
    if (op=='/' && arg2==0) throw DivideByZero()
    return when (op) {
        '*' -> arg1*arg2
        '/' -> arg1/arg2
        else -> throw UnsupportedOperation(op)
    }
}

fun applySchema(schema: String) : (List<Int>) -> Int {
    val ops : List<Char> = schema.toList()
    return { args ->
        if (args.size!=ops.size+1) throw NotEnoughNumbers(args.size,args.joinToString(","))
        var res = args.first()
        var opsNumber = 0
        var argsNumber = 1
        while(opsNumber!=ops.size){
            res = applyOperation(ops[opsNumber],res,args[argsNumber])
            opsNumber+=1
            argsNumber+=1
        }
        res
    }
}

fun formatLHS(schema: String, numbers: List<String>): String {
    var string = ""
    var numbersNumber=0
    var schemaNumber=0
    while (schemaNumber!=schema.length){
        string+=numbers[numbersNumber]
        string+=schema[schemaNumber]
        schemaNumber+=1
        numbersNumber+=1
    }
    string+=numbers[numbersNumber]
    return string
}

fun processString(schema: String, input: String): String {
    val transformation = applySchema(schema)
    val numbers = input.split(",")
    try {val result = transformation(numbers.map { it.toInt() })
    } catch(e: NumberFormatException) {throw IncorrectFormatOfString()}
    val result = transformation(numbers.map { it.toInt() })
    return formatLHS(schema, numbers) + "=$result"
}

fun processFiles(schemasFile: String, dataFile: String, outputFile: String) {
    if (!File(schemasFile).exists()) throw MissingInputFile(schemasFile)
    if (!File(dataFile).exists()) throw MissingInputFile(dataFile)
    if (!File(outputFile).createNewFile()) throw FileExists(outputFile)
    val schemasLines = File(schemasFile).readLines()
    val dataLines = File(dataFile).readLines()
    if (schemasLines.size!=dataLines.size) throw DifferentAmountOfLines(schemasFile,dataFile)
    var schemasLineNumber = 0
    val writer = File(outputFile).bufferedWriter()
    while (schemasLines.size!=schemasLineNumber){
        writer.write(processString(schemasLines[schemasLineNumber],dataLines[schemasLineNumber]))
        writer.newLine()
        schemasLineNumber+=1
    }
    writer.close()
}

val logger = KotlinLogging.logger {  }

fun main(args: Array<String>) {
    logger.info {"program started"}
    processFiles(args[0],args[1],args[2])
    logger.info {"program completed"}
}