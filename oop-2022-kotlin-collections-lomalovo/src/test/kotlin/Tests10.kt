import org.junit.Assert
import org.junit.Test

class TestFold {
    @Test fun testGetProductsOrderedByAllCustomers() {
        val testShop1 = shop(
            "test shop",
            customer(
                lucas, Canberra,
                order(idea),
                order(webStorm)
            ),
            customer(
                reka, Budapest,
                order(idea),
                order(youTrack)
            )
        )
        val testShop2 = shop(
            "another test shop",
            customer(
                lucas, Canberra,
                order(idea)
            ),
            customer(
                reka, Budapest,
                order(youTrack),
                order(webStorm)
            )
        )
        Assert.assertEquals(
            "getSetOfProductsOrderedByEveryCustomer".toMessage(),
            setOf(idea),
            testShop1.getSetOfProductsOrderedByEveryCustomer()
        )
        Assert.assertEquals(
            "getSetOfProductsOrderedByEveryCustomer".toMessage(),
            emptySet<Any>(),
            testShop2.getSetOfProductsOrderedByEveryCustomer()
        )
    }
}
