/** Все продукты, заказанные данным покупателем. */
fun Customer.getOrderedProducts(): Set<Product> = orders.flatMap{it.products}.toSet()

/** Все продукты, заказанные хотя бы одним покупателем. Переиспользуйте функцию выше. */
fun Shop.getAllOrderedProducts(): Set<Product> = customers.flatMap{it.getOrderedProducts()}.toSet()

