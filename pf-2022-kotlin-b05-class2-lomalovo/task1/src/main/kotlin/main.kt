import kotlin.math.min

/*
 * Задание 1.1. Дана строка. Верните строку, содержащую текст "Длина: NN",
 * где NN — длина заданной строки. Например, если задана строка "hello",
 * то результатом должна быть строка "Длина: 5".
 */
fun stringLength(s: String) :String {
    return "Длина: ${s.length}"
}

/*
 * Задание 1.2. Дана непустая строка. Вернуть коды ее первого и последнего символов.
 * Рекомендуется найти специальные функции для вычисления соответствующих символов и их кодов.
 */
fun firstLastCodes(s: String): Pair<Int, Int> {
    return Pair(s.first().code, s.last().code)
}

/*
 * Задание 1.3. Дана строка. Подсчитать количество содержащихся в ней цифр.
 * В решении необходимо воспользоваться циклом for.
 */
fun countDigits(s: String): Int {
    var count = 0
    for (letter in s){
        if (letter.isDigit()) count++
    }
    return count
}

/*
 * Задание 1.4. Дана строка. Подсчитать количество содержащихся в ней цифр.
 * В решении необходимо воспользоваться методом count:
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/count.html
 * и предикатом it.isDigit().
 */
fun countDigits2(s: String): Int {
    return s.count{it.isDigit()}
}

/*
 * Задание 1.5. Дана строка, изображающая арифметическое выражение вида «<цифра>±<цифра>±…±<цифра>»,
 * где на месте знака операции «±» находится символ «+» или «−» (например, «4+7−2−8»). Вывести значение
 * данного выражения (целое число).
 */
fun calcDigits(expr: String): Int {
    var answer = 0
    var expr1 = expr
    answer += expr1.take(1).toInt()  // в этой и следующей строке я прибавляю к ответу первую цифру и удаляю её
    expr1 = expr1.drop(1)
    while ((expr1.indexOf("-") != -1) or (expr1.indexOf("+") != -1)) { //пока есть знаки = или -
        if (expr1.indexOf("-") == 0) {        //проверяю знак
            expr1 = expr1.drop(1)                //убираю знак
            answer -= expr1.take(1).toInt()      //вычитаю цифру
            expr1 = expr1.drop(1)                //убираю цифру
        }
        else {
            expr1 = expr1.drop(1)
            answer += expr1.take(1).toInt()
            expr1 = expr1.drop(1)
        }
    }
    return answer
}

/*
 * Задание 1.6. Даны строки S, S1 и S2. Заменить в строке S первое вхождение строки S1 на строку S2.
 */
fun replaceWithString(s: String, s1: String, s2: String): String {
    return s.replaceFirst(s1,s2)
}


fun main() {

}