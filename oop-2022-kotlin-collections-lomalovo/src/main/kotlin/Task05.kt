/** Покупатель с самым большим числом заказов */
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = customers.maxByOrNull { it.orders.size }

/** Самый дорогой продукт из всех когда-либо заказанных данным покупателем */
fun Customer.getMostExpensiveOrderedProduct(): Product? = orders.flatMap{it.products.toSet()}.toSet().maxByOrNull { it.price }

