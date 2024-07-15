/** Продукты, которые хоть раз заказали все покупатели. */
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> = customers.flatMap{it.getOrderedProducts()}.filter{filt -> customers.all{an -> filt in an.getOrderedProducts()}}.toSet().fold(setOf()) { partProduct, element ->
    partProduct.plus(element)
}

