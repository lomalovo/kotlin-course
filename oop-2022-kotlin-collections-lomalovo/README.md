# Курс объектно-ориентированного программирования на МКН СПбГУ
## Задания на работу с коллекциями

Эти задания частично совпадают с заданиями части Collections в [Kotlin Koans](https://kotlinlang.org/docs/tutorials/koans.html). 
В большинстве из них нужно реализовать [функции-расширения](https://kotlinlang.org/docs/reference/extensions.html) для классов, демонстрирующих модель интернет-магазина.
Класс [`Shop`](psi_element://Shop) и другие объявлены в файле [`Shop.kt`](psi_element://Shop.kt). При выполнении этих заданий вам пригодится документация [функций-расширений для коллекций](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html) из стандартной библиотеки.

Исходный код заданий находится в файлах `src/main/kotlin/TaskNN.kt`. Для проверки корректности своих решений можно использовать тесты из `src/test/kotlin`, 
но постарайтесь не смотреть в них раньше времени.

Вся работа должна выполняться в ветке `main`. Для отправки решений на проверку создайте 
пулл-реквест на ветку `task`. 

#### Начисление баллов за задания:

* задания 01-03: 1 балл
* задания 04-06: 1 балл
* задания 07-08: 1 балл
* задания 09-10: 1 балл
* задания 11-12: 1 балл
* задания 13-14: 1 балл

Итого: до 6 баллов.

Если вы претендуете на бонусные баллы по заданию 13,
напишите об этом в сообщении пулл-реквеста и заменшените своего преподавателя практики.

### 01: преобразование типа коллекции

Коллекции различных типов можно преобразовывать друг в друга с помощью функций с названиями `to*`: 
[`toSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/to-set.html) или 
[`toList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/to-list.html) 
(эти имена кликабельны).

Реализуйте функцию-расширение `Shop.getSetOfCustomers()`.
### 02: filter, map

Реализуйте функции-расширения `Shop.getCitiesCustomersAreFrom()` и `Shop.getCustomersFrom()` с помощью [`map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/map.html) и [`filter`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/filter.html).

```kotlin
val numbers = listOf(1, -1, 2)
numbers.filter { it > 0 } == listOf(1, 2)
numbers.map { it * it } == listOf(1, 1, 4)
```

### 03: all, any, предикаты

Реализуйте все функции, используя
    [`all`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/all.html),
    [`any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/any.html),
    [`count`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/count.html),
    [`find`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/find.html).

```kotlin
val numbers = listOf(-1, 0, 2)
val isZero: (Int) -> Boolean = { it == 0 }
numbers.any(isZero) == true
numbers.all(isZero) == false
numbers.count(isZero) == 1
numbers.find { it -> 0 } == 2
```
### 04: flatMap

Реализуйте `Customer.getOrderedProducts()` и `Shop.getAllOrderedProducts()` с помощью 
[`flatMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/flat-map.html).

```kotlin
val result = listOf("abc", "12").flatMap { it.toCharList() }
result == listOf('a', 'b', 'c', '1', '2')
```
### 05: max, min

Реализуйте `Shop.getCustomerWithMaximumNumberOfOrders()` и `Customer.getMostExpensiveOrderedProduct()`
    с помощью
    [`maxOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/max-or-null.html),
    [`minOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/min-or-null.html),
    [`maxByOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/max-by-or-null.html) или
    [`minByOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/max-by-or-null.html).

<pre><code data-lang="text/x-kotlin">listOf(1, 42, 4).maxOrNull() == 42
listOf(&quot;a&quot;, &quot;ab&quot;).minByOrNull { it.length } == &quot;a&quot;
</code></pre>
### 06: суммирование

Для вычисления сумм по элементам коллекции можно использовать
[`sum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/sum.html),
[`sumBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/sum-by.html)

```kotlin
listOf(1, 5, 3).sum() == 9
listOf("a", "b", "cc").sumBy { it.length() } == 4
```

Чтобы суммировать значения типа `Double`, используйте [`sumByDouble`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/sum-by-double.html).

<p>Реализуйте <code>Customer.getTotalOrderPrice()</code>.</p>

### 07: сортировка

Реализуйте `Shop.getCustomersSortedByNumberOfOrders()` и `Shop.customersReportByCity()` с помощью:
    [`sorted`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/sorted.html),
    [`sortedBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/sorted-by.html),
    [`sortedWith`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-with.html),
    [`compareBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/compare-by.html),
    [`thenBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/then-by.html) и др.:


```kotlin
listOf("bbb", "a", "cc").sorted() == listOf("a", "bbb", "cc")
listOf("bbb", "a", "cc").sortedBy { it.length } == listOf("a", "cc", "bbb")
```
### 08: groupBy

Реализуйте `Shop.groupCustomersByCity()` и `Shop.getCustomersByOrderedProduct()`.
Используйте [`groupBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/group-by.html).

```kotlin
val result = listOf("a", "b", "ba", "ccc", "ad").groupBy { it.length() }
result == mapOf(1 to listOf("a", "b"), 2 to listOf("ba", "ad"), 3 to listOf("ccc"))
```

Для реализации `Shop.getCustomersByOrderedProduct` вам могут пригодиться другие функции, например, уже знакомая
`flatMap`, [`mapValues`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/map-values.html) и прочие.
### 09: partition

Реализуйте `Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered()` и `Shop.reorderCustomersAtLeastTwoOrdersFirst()` с помощью [`partition`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/partition.html).

```kotlin
val numbers = listOf(1, 3, -4, 2, -11)
val (positive, negative) = numbers.partition { it &gt; 0 }
positive == listOf(1, 3, 2)
negative == listOf(-4, -11)
```
Обратите внимание на синтаксис [объявления с декомпозицией](http://kotlinlang.org/docs/reference/multi-declarations.html) в данном примере.</p>

### 10: fold, reduce

Реализуйте `Shop.getProductsOrderedByAllCustomers()` с помощью 
[`fold`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/fold.html) 
или [`reduce`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/kotlin.-iterable/reduce.html).

```kotlin
listOf(1, 2, 3, 4).fold(1, {
    partProduct, element ->
    element * partProduct
}) == 24
```

### 11: цепочки вызовов

Реализуйте `Customer.getMostExpensiveDeliveredProduct()` и `Shop.getNumberOfTimesProductWasOrdered()`, 
используя функции для работы с коллекциями.

### 12: функциональный стиль вместо `for`
Перепишите  функцию `doSomethingStrangeWithCollection`, используя функции стандартной библиотеки для
работы с коллекциями и без использования циклов `for` или `forEach { ... }`.

### 13: <strike>странный</strike> нечётный список 

Реализуйте класс `OddList` – список-обёртку над произвольным списком такую, что в ней
содержатся только элементы оригинального списка на нечётных позициях (нумерация позиций – с нуля).

Реализация должна реагировать на неструктурные изменения – замену одного элемента другим
в оригинальном списке – и возвращать 
обновлённые значения. Разрешается неопределённое поведение при структурных изменениях, таких, как добавление элемента
в середину или удаление элемента. 

Например:

```kotlin
val originalList = mutableListOf(0, 1, 2, 3, 4, 5)
val oddList = OddList(originalList)
println(oddList.joinToString()) // 1, 3, 5
originalList[3] = -3
println(oddList.joinToString()) // 1, -3, 5
```

**Дополнительное задание на бонусные баллы**: в реализации класса итератора, возвращаемого из <code>listIterator</code>,
используйте только итератор оригинального списка и не храните индекс.
 
Подсказка: некоторые операции можно реализовать с помощью расширений из стандартной библиотеки, работающих с <code>Iterable</code>, если верно реализован итератор.

### 14: чтение текстового файла
Напишите программу, считывающую текстовый файл, путь к которому указан в первом аргументе, и выводящую все строки,
в которых сумма чисел равна нулю. 

Числа в строке могут быть разделены табуляциями или пробелами, в том числе несколькими.

Строки, начинающиеся с символа `#` нужно игнорировать. 
Строки с некорректными данными нужно игнорировать.
