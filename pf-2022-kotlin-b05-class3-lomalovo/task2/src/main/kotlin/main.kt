import java.lang.StringBuilder
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.*

/*
 * В этом задании необходимо написать программу, способную табулировать сразу несколько
 * функций одной вещественной переменной на одном заданном отрезке.
 */

typealias OneVariableFunction = (Double) -> Double
typealias FunctionName = String

// Сформируйте набор как минимум из десяти вещественных функций одной переменной
val availableFunctions : Map<FunctionName, OneVariableFunction> = mapOf(
    "square" to { x -> x*x },
    "sin" to {x -> sin(x)},
    "cos" to {x -> cos(x)},
    "tg" to {x -> tan(x)},
    "ln" to {x -> ln(x)},
    "abs" to {x -> abs(x)},
    "sqrt" to {x -> sqrt(abs(x))},
    "cube" to {x -> x*x*x},
    "-x" to {x -> 1/x},
    "-x^2" to {x -> 1/(x*x)}

)

// Тип данных для представления входных данных
data class InputData(val fromX: Double, val toX: Double, val numberOfPoints: Int,
                     val functionNames: List<String>)

// Чтение входных данных из параметров командной строки
fun prepareData(args: Array<String>): InputData? {
    return InputData(args[0].toDouble(), args[1].toDouble(), args[2].toInt(), args.drop(3).toList())
}

// Тип данных для представления таблицы значений функций
// с заголовками столбцов и строками (первый столбец --- значение x,
// остальные столбцы --- значения функций). Одно из полей --- количество знаков
// после десятичной точки.
data class FunctionTable (val table: List<List<String>>) {
    override fun toString(): String {
        // Код, возвращающий строковое представление таблицы (с использованием StringBuilder)
        // Столбец x выравнивается по левому краю, все остальные столбцы по правому.
        // Для форматирования можно использовать функцию format или класс DecimalFormat
        // (https://www.programiz.com/kotlin-programming/examples/round-number-decimal).
        var string = StringBuilder()
        var maxes: MutableList<Int> = mutableListOf()
        for (i in table.indices) {
            var max = 0
            for (str in table[i]) max=max(max,str.length)
            maxes.add(max)
        }
        for(width in 0 until table[0].size){
            for(length in table.indices) {
                if (length==0) string.append(table[length][width].padEnd(maxes[length]+1))
                else string.append(table[length][width].padStart(maxes[length]+1))
            }
            if (width!=table[0].size-1) string.append("\n")
        }
        return string.toString()
    }
}

/*
 * Возвращает таблицу значений заданных функций на заданном отрезке [fromX, toX]
 * с заданным количеством точек.
 */
fun tabulate(input: InputData): FunctionTable {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    val answer:MutableList<MutableList<String>> = mutableListOf()
    var x = input.fromX
    val column: MutableList<String> = mutableListOf()
    column.add("x")
    while(x<=input.toX){
        column.add(df.format(x).toString())
        x+=(input.toX-input.fromX)/(input.numberOfPoints - 1)
    }
    answer.add(column)
    for (func in input.functionNames){
        val columnNotFirst: MutableList<String> = mutableListOf()
        columnNotFirst.add(func)
        for (x in column.drop(1)){
            columnNotFirst.add(df.format(availableFunctions[func]?.let{it(x.replace(",",".").toDouble())}).toString())
        }
        answer.add(columnNotFirst)
    }
    return FunctionTable(answer)
}

fun main(args: Array<String>) {
    // Входные данные принимаются в аргументах командной строки
    // fromX fromY numberOfPoints function1 function2 function3 ...

    val input = prepareData(args)

    // Собственно табулирование и печать результата (здесь ошибка компиляции, необходимо исправить):
    println(input?.let { tabulate(it).toString() })
}