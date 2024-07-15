/** Множество городов, указанных покупателями (customers) как город проживания (city). */
fun Shop.getCitiesCustomersAreFrom(): Set<City> = customers.map{it.city}.toSet()

/** Список покупателей (customers), проживающих в данном городе */
fun Shop.getCustomersFrom(city: City): List<Customer> = customers.filter{it.city==city}
