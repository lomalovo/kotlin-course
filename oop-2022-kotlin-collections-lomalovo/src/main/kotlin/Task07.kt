/** Список покупателей по возрастанию количества заказов */
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = customers.sortedBy { it.orders.size }

/** Сортировка покупателей по городу (в алфавитном порядке), а в пределах города –
 * по убыванию общей стоимости всех заказов */
fun Shop.customersReportByCity(): List<Customer> =
    customers.sortedWith(Comparator{p1:Customer,p2:Customer -> when{
        p1.city.toString()<p2.city.toString() -> -1
        p1.city.toString()>p2.city.toString() -> 1
        p1.orders.flatMap { it.products.toList() }.sumOf{ it.price } < p2.orders.flatMap { it.products.toList() }.sumOf{ it.price } -> 1
        p1.orders.flatMap { it.products.toList() }.sumOf{ it.price } > p2.orders.flatMap { it.products.toList() }.sumOf{ it.price } -> -1
        else -> 0
    }} )

