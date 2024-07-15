import org.junit.Assert
import org.junit.Test

class TestMaxMin {
    @Test fun testCustomerWithMaximumNumberOfOrders() {
        Assert.assertEquals(
            "getCustomerWithMaximumNumberOfOrders".toMessage(),
            customers[reka],
            shop.getCustomerWithMaximumNumberOfOrders()
        )
        val otherShop = Shop("other", listOf(customers[lucas]!!, customers[bajram]!!))
        Assert.assertEquals(
            "getCustomerWithMaximumNumberOfOrders".toMessage(),
            customers[lucas],
            otherShop.getCustomerWithMaximumNumberOfOrders()
        )
        Assert.assertEquals(
            "getCustomerWithMaximumNumberOfOrders".toMessage(),
            null,
            Shop("empty", emptyList()).getCustomerWithMaximumNumberOfOrders()
        )
    }

    @Test fun testTheMostExpensiveOrderedProduct() {
        Assert.assertEquals(
            "getMostExpensiveOrderedProduct".toMessage(),
            rubyMine,
            customers[nathan]!!.getMostExpensiveOrderedProduct()
        )
        Assert.assertEquals(
            "getMostExpensiveOrderedProduct".toMessage(),
            idea,
            customers[asuka]!!.getMostExpensiveOrderedProduct()
        )
        Assert.assertEquals(
            "getMostExpensiveOrderedProduct".toMessage(),
            null,
            customers[cooper]!!.getMostExpensiveOrderedProduct()
        )
    }
}
