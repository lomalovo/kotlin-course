/*
 * Перед выполнением заданий рекомендуется просмотреть туториал по регулярным выражениям:
 * https://zetcode.com/kotlin/regularexpressions/
 */

/*
 * Задание 3.1. Проверить, содержит ли заданная строка только цифры?
 */
fun allDigits(s: String) = s.matches("[0-9]+".toRegex())

/*
 * Задание 3.2. Проверить, содержит ли заданная строка подстроку, состоящую
 * из букв abc в указанном порядке, но в произвольном регистре?
 */
fun containsABC(s: String) = "((a|A)(b|B)(c|C))".toRegex().containsMatchIn(s)

/*
 * Задание 3.3. Найти первое вхождение подстроки, состоящей только из цифр,
 * и вернуть её в качестве результата. Вернуть пустую строку, если вхождения нет.
 */
fun findDigitalSubstring(s: String) : String {
    val result = Regex("[0-9]+").find(s)
    if (result == null) return ""
    return result.groupValues.toString().drop(1).dropLast(1)
}

/*
 * Задание 3.4. Заменить все вхождения подстрок строки S, состоящих только из цифр,
 * на заданную строку S1.
 */
fun hideDigits(s: String, s1: String) : String {
    return Regex("[0-9]+").replace(s,s1)
}


fun main() {

}