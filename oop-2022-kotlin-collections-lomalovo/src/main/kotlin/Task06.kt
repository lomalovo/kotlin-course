// Вычислите общую стоимость заказанных покупателем товаров.
// Замечание: покупатель может заказывать один и тот же товар несколько раз.
fun Customer.getTotalOrderPrice(): Double = orders.flatMap { it.products.toList() }.sumOf{ it.price }

