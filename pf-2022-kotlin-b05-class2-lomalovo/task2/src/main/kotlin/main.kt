import java.lang.StringBuilder

/*
 * Перед выполнением заданий прочитайте короткий туториал о способах формирования строк:
 * https://www.baeldung.com/kotlin/concatenate-strings. В этих заданиях
 * рекомендуется всюду использовать класс StringBuilder.
 *
 * Задание 2.1. Дана непустая строка S и целое число N (> 0). Вернуть строку, содержащую символы
 * строки S, между которыми вставлено по N символов «*» (звездочка).
 */
fun fillWithAsterisks(s: String, n: Int): String {
    val leng=s.length
    val answer = StringBuilder()
    if (leng>1){
        answer.append(s[0])
        for (i in 1 until leng) {
            repeat(n){
                answer.append("*")
            }
            answer.append(s[i])
        }
    }
    else return s
    return answer.toString()
}

/*
 * Задание 2.2. Сформировать таблицу квадратов чисел от 1 до заданного числа N.
 * Например, для N=4 должна получиться следующая строка:

1  1
2  4
3  9
4 16

 * Обратите внимание на выравнивание: числа в первом столбце выравниваются по левому краю,
 * а числа во втором -- по правому, причём между числами должен оставаться как минимум один
 * пробел. В решении можно использовать функции pad*.
 */
fun tabulateSquares(n: Int): String {
    val answer = StringBuilder()
    val lenghtn1 = n.toString().length+1
    val lenghtn2 = (n*n).toString().length
    for (i in 1 until n){
        answer.append("${"${i}".padEnd(lenghtn1)}${"${i*i}".padStart(lenghtn2)}\n")

    }
    answer.append("${"${n}".padEnd(lenghtn1)}${"${n*n}".padStart(lenghtn2)}")
    return answer.toString()
}

fun main() {
    println(fillWithAsterisks("abc", 2))
    println(tabulateSquares(4))
}