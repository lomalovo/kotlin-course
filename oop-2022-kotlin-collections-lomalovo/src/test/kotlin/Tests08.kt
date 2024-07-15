import org.junit.Assert
import org.junit.Test

class TestGroupBy {
    @Test fun testGroupCustomersByCity() {
        Assert.assertEquals(
            "groupCustomersByCity".toMessage(),
            groupedByCities,
            shop.groupCustomersByCity()
        )
        val otherShop = Shop("other", listOf(Lucas, Reka))
        Assert.assertEquals(
            "groupCustomersByCity".toMessage(),
            mapOf(Canberra to listOf(Lucas), Budapest to listOf(Reka)),
            otherShop.groupCustomersByCity()
        )
    }

    @Test fun testGroupCustomersByProduct() {
        Assert.assertEquals(
            "groupCustomersByOrderedProduct".toMessage(),
            shop.groupCustomersByOrderedProduct(), groupedByProduct
        )
        val otherShop = Shop("other", listOf(Lucas, Reka))
        Assert.assertEquals(
            "groupCustomersByOrderedProduct".toMessage(),
            mapOf(
                idea to setOf(Reka), reSharper to setOf(Lucas), dotMemory to setOf(Lucas), dotTrace to setOf(Lucas)
            ),
            otherShop.groupCustomersByOrderedProduct()
        )
    }
}

val groupedByCities = mapOf(
    Canberra to listOf(lucas, cooper),
    Vancouver to listOf(nathan),
    Budapest to listOf(reka),
    Ankara to listOf(bajram),
    Tokyo to listOf(asuka)
).mapValues { it.value.map { name -> customers[name] } }

val groupedByProduct = mapOf(
    reSharper to setOf(Lucas, Bajram),
    dotTrace to setOf(Lucas),
    dotMemory to setOf(Lucas),
    rubyMine to setOf(Nathan),
    webStorm to setOf(Nathan),
    idea to setOf(Reka, Asuka)
)
