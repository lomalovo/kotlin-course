/** Все ли покупатели из указанного города? */
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = customers.all{it.city==city}


/** Есть ли хоть один покупатель, сделавший хотя бы N заказов? */
fun Shop.hasCustomerWithAtLeastOrders(nOrders: Int): Boolean = customers.any { it.orders.size == nOrders }

/** Сколько есть покупателей из указанного множества городов? */
fun Shop.countCustomersFrom(cities: Set<City>): Int = customers.count { it.city in cities }

/** Найти покупателя из указанного города, вернуть null, если такого нет. */
fun Shop.findAnyCustomerFrom(city: City): Customer? = customers.find{it.city == city}
