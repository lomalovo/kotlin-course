/*
 * Задание 1.1. Напишите функцию `sayHello`, которая печатает строку, состоящую из "Hello ",
 * значения переменной name и символа '!' в конце. В решении следует использовать шаблоны
 * строк: https://kotlinlang.org/docs/basic-syntax.html#string-templates. Для печати строки
 * следует использовать функцию `println`.
 */
fun sayHello(name: String) {
    println("Hello $name!")
}

/*
 * Задание 1.2. Напишите функцию `helloUser`, которая запрашивает имя пользователя с клавиатуры,
 * сохраняет его в переменную (val), а затем приветствует его, вызывая функцию `sayHello`.
 * Для ввода с клавиатуры можно использовать функцию `readLine()!!`. Обратите внимание
 * на символы `!!`, тем самым мы гарантируем, что какая-то строка обязательно будет.
 * Что произойдёт, если мы забудем поставить `!!`? Подробнее этот вопрос мы обсудим
 * на второй лекции.
 */
fun helloUser() {
    val firstname = readLine()!!
    sayHello(firstname)
}

/*
 * Задание 1.3. Напишите функцию `square`, которая запрашивает у пользователя целое число
 * и печатает его квадрат. Для преобразования строки в число можно использовать
 * метод строки toInt(). Вычисление квадрата числа можно производить непосредственно
 * в шаблоне строки.
 */
fun square() {
    val number = readLine()!!
    println(number.toInt()*number.toInt())
}
/*
 * Задание 1.4. Напишите функцию `sum`, которая запрашивает у пользователя два целых числа
 * (по одному в каждой строке) и печатает их сумму.
 */
fun sum() {
    println("")
    print(readLine()!!.toInt()+readLine()!!.toInt())
}

fun main() {
    sayHello("World")
    helloUser()
    square()
    sum()
}