import org.junit.Assert
import org.junit.Test

class TestCompound {

    @Test fun testMostExpensiveDeliveredProduct() {
        val testShop = shop(
            "test shop for 'most expensive delivered product'",
            customer(
                lucas, Canberra,
                order(idea, isDelivered = false),
                order(reSharper)
            )
        )
        Assert.assertEquals(
            "getMostExpensiveDeliveredProduct".toMessage(),
            reSharper,
            testShop.customers[0].getMostExpensiveDeliveredProduct()
        )
    }

    @Test fun testNumberOfTimesEachProductWasOrdered() {
        Assert.assertEquals(
            "getNumberOfTimesProductWasOrdered".toMessage(),
            3,
            shop.getNumberOfTimesProductWasOrdered(reSharper)
        )
    }
}
