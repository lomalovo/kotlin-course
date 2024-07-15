import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/*
 * В решениях следующих заданий предполагается использование циклов и диапазонов:
 * - https://kotlinlang.org/docs/control-flow.html#for-loops
 * - https://kotlinlang.org/docs/control-flow.html#while-loops
 * - https://kotlinlang.org/docs/ranges.html
 */

/*
 * Задание 4.1. Пользуясь циклом for, посимвольно напечатайте рамку размера width x height,
 * состоящую из символов frameChar. Можно считать, что width>=2, height>=2.
 * Например, вызов printFrame(5,3,'+') должен напечатать следующее:

+++++
+   +
+++++
 */
fun printFrame(width: Int, height: Int, frameChar: Char = '*') {
    for(h in 1..height){
        for(w in 1..width){
            if (((h == 1) or (h == height) or (w == 1)) and (w!=width)) print(frameChar) else if (w==width) println(frameChar) else print(" ")
        }
    }
}

/*
 * Задание 4.2. Выполните предыдущее задание, пользуясь циклом repeat.
 * Например, следующий код печатает числа от 1 до 10:

    repeat(10) {
        println(it + 1)
    }

 * Переменная `it` в этом коде последовательно принимает значения от 0 до 9, и для
 * каждого нового значения вызывается блок в фигурных скобках.
 *
 * На самом деле repeat — это не синтаксическая конструкция, а функция, принимающая блок
 * в качестве параметра. Если вам интересно, посмотреть на реализацию этой функции можно здесь:
 * https://github.com/JetBrains/kotlin/blob/34e57a45f2d4283be572137b4b497414b8833ee7/libraries/stdlib/src/kotlin/util/Standard.kt#L151
 */
fun printFrame2(width: Int, height: Int, frameChar: Char = '*') {
    repeat(height){h: Int ->
        repeat(width){w: Int ->
            if (((h+1 == 1) or (h+1 == height) or (w+1 == 1)) and (w+1!=width)) print(frameChar) else if (w+1==width) println(frameChar) else print(" ")

        }
    }

}

/*
 * Задание 4.3. Даны целые положительные числа A и B. Найти их наибольший общий делитель (НОД),
 * используя алгоритм Евклида:
 * НОД(A, B) = НОД(B, A mod B),    если B ≠ 0;        НОД(A, 0) = A,
 * где «mod» обозначает операцию взятия остатка от деления.
 */
fun gcd(a: Long, b: Long): Long {
    var nod = arrayOf<Long>(a, b)
    nod.sort()
    while ((nod[0] > 0) and (nod[1] > 0)) {
        nod[1] = nod[1] % nod[0]
        nod.sort()
    }
    return nod[1]
}

/*
 * Задание 4.4. Дано вещественное число X и целое число N (> 0). Найти значение выражения
 * 1 + X + X^2/(2!) + … + X^N/(N!), где N! = 1·2·…·N.
 * Полученное число является приближенным значением функции exp в точке X.
 */
fun expTaylor(x: Double, n: Int): Double {
    var s = 0.0
    var a_n = 1.0
    repeat(n){
        s+=a_n
        a_n*=x
        a_n/=(it+1)
    }
    return s
}

fun main() {
    printFrame(5,3,'+')
}