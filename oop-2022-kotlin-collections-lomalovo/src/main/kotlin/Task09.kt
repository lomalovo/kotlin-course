/** Покупатели, у которых больше недоставленных заказов, чем доставленных - [Order.isDelivered] */
fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = customers.partition {it.orders.count{ it.isDelivered } < it.orders.count{ !it.isDelivered }}.first.toSet()

/** Список покупателей в таком порядке: сначала сделавшие хотя бы два заказа, затем все остальные
 * (внутри частей порядок должен совпадать с порядком покупателей в оригинальном списке [Shop.customers]) */
fun Shop.reorderCustomersAtLeastTwoOrdersFirst(): List<Customer> = customers.partition{ it.orders.size>=2 }.first.plus(customers.partition { it.orders.size>=2 }.second)

