//products
val idea = Product("IntelliJ IDEA Ultimate", 199.0)
val reSharper = Product("ReSharper", 149.0)
val dotTrace = Product("DotTrace", 159.0)
val dotMemory = Product("DotTrace", 129.0)
val dotCover = Product("DotCover", 99.0)
val appCode = Product("AppCode", 99.0)
val phpStorm = Product("PhpStorm", 99.0)
val pyCharm = Product("PyCharm", 99.0)
val rubyMine = Product("RubyMine", 99.0)
val webStorm = Product("WebStorm", 49.0)
val teamCity = Product("TeamCity", 299.0)
val youTrack = Product("YouTrack", 500.0)
val ideaPremium = Product("IntelliJ IDEA Premium Edition", 10000.0)

//customers
val lucas = "Lucas"
val cooper = "Cooper"
val nathan = "Nathan"
val reka = "Reka"
val bajram = "Bajram"
val asuka = "Asuka"

//cities
val Canberra = City("Canberra")
val Vancouver = City("Vancouver")
val Budapest = City("Budapest")
val Ankara = City("Ankara")
val Tokyo = City("Tokyo")

fun customer(name: String, city: City, vararg orders: Order) = Customer(name, city, orders.toList())
fun order(vararg products: Product, isDelivered: Boolean = true) = Order(products.toList(), isDelivered)
fun shop(name: String, vararg customers: Customer) = Shop(name, customers.toList())

val Lucas = customer(lucas, Canberra,
    order(reSharper),
    order(reSharper, dotMemory, dotTrace)
)

val Nathan = customer(nathan, Vancouver,
    order(rubyMine, webStorm)
)

val Reka = customer(
    reka, Budapest,
    order(idea, isDelivered = false),
    order(idea, isDelivered = false),
    order(idea)
)

val Bajram = customer(
    bajram, Ankara,
    order(reSharper)
)

val Asuka = customer(
    asuka, Tokyo,
    order(idea)
)

val shop = shop(
    "jb test shop",
    Lucas,
    customer(cooper, Canberra),
    Nathan,
    Reka,
    Bajram,
    Asuka
)

val customers: Map<String, Customer> = shop.customers.map { Pair(it.name, it) }.toMap()

val orderedProducts = setOf(idea, reSharper, dotTrace, dotMemory, rubyMine, webStorm)

val sortedCustomers = listOf(cooper, nathan, bajram, asuka, lucas, reka).map { customers[it] }


