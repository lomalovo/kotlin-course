/** Самый дорогой продукт из всех когда-либо доставленных ([Order.isDelivered]) */
fun Customer.getMostExpensiveDeliveredProduct(): Product? =
    orders.filter{it.isDelivered}.flatMap { it.products }.toSet().maxByOrNull { it.price }

/** Сколько раз продукт был заказан? (учтите, что один покупатель может заказать продукт несколько раз) */
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int = customers.flatMap { it.orders.flatMap { it.products } }.count{it==product}


