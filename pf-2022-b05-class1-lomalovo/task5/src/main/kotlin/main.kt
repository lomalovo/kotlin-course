import java.math.BigInteger

/*
 * Задание 5.1. Запустите функцию `main`, нажав зелёный треугольник слева от её имени. Это действие создаст
 * конфигурацию, которую позже можно отредактировать. В этом задании для ввода данных используются
 * либо параметры командной строки, либо консольный ввод. При запуске через IDEA параметры командной строки
 * задаются в окне "Run/Edit configurations..." ("Program arguments:").
 */
fun main(args: Array<String>) {
    //demoInput(args)
    computeFib(args)
}

/*
 * Задание 5.2. Разберите код функции `demoInput`.
 */
fun demoInput(args: Array<String>) {
    val name = if(args.isNotEmpty()) {
        args.joinToString(" ")
    } else {
        println("Введите имя:")
        readLine()
    }
    println("Привет, $name!")
}

/*
 * Задание 5.3. Напишите программу, которая принимает на вход целое неотрицательное число
 * (либо параметром командной строки, либо с клавиатуры) и печатает число Фибоначчи:
 * https://ru.wikipedia.org/wiki/%D0%A7%D0%B8%D1%81%D0%BB%D0%B0_%D0%A4%D0%B8%D0%B1%D0%BE%D0%BD%D0%B0%D1%87%D1%87%D0%B8
 * с соответствующим номером. Мы считаем, что нулевым числом Фибоначчи является число 0, а первым — число 1.
 *
 * Для представления чисел Фибоначчи следует использовать длинные целые:
 * - https://stackoverflow.com/a/44289523
 * - https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html
 */

fun fib(n: Int): BigInteger {
    if (n == 0) {
        return 0.toBigInteger()
    }
    var a = 0.toBigInteger()
    var b = 1.toBigInteger()
    for (i in 2..n) {
        val c = a + b
        a = b
        b = c
    }
    return b
}

fun computeFib(args: Array<String>) {
    val number = if(args.isNotEmpty()) {
        args.joinToString(" ")
    } else {
        println("Введите номер числа фибоначчи:")
        readLine()!!
    }
    print(fib(number.toInt()))
}

