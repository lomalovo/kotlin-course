/** Отображение города в список покупателей, проживающих в этом городе. */
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = customers.groupBy { it.city }

/**
 * Отображение каждого продукта в множество покупателей, хотя бы раз заказавших этот продукт.
 * Продукты, которые никто никогда не купил, не должны присутствовать в результате.
 */
fun Shop.groupCustomersByOrderedProduct(): Map<Product, Set<Customer>> {
    var db:Set<Pair<Customer,Product>> = setOf()
    for (cust in customers){
        for (ord in cust.orders){
            for (prod in ord.products){
                db=db.plus(Pair(cust,prod))
            }
        }
    }
    var answer: Map<Product, Set<Customer>> = mapOf()
    for (dbElem in db.groupBy { it.second }){
        var set: Set<Customer> = setOf()
        for (value in dbElem.value){
            set=set.plus(value.first)
        }
        answer=answer.plus(dbElem.key to set)
    }
    return answer
}
