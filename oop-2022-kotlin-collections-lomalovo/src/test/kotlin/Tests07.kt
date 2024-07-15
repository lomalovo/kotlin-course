import org.junit.Assert
import org.junit.Test

class TestSort {
    @Test fun testGetCustomersSortedByNumberOfOrders() {
        Assert.assertEquals(
            "getCustomersSortedByNumberOfOrders".toMessage(),
            sortedCustomers, shop.getCustomersSortedByNumberOfOrders()
        )
        val n = customers[nathan]!!
        val r = customers[reka]!!
        val otherShop = Shop("other", listOf(n, r))
        Assert.assertEquals(
            "getCustomersSortedByNumberOfOrders".toMessage(),
            listOf(n, r), otherShop.getCustomersSortedByNumberOfOrders(),
        )
    }

    @Test fun testCustomersReportByCity() {
        Assert.assertEquals(
            "getCustomersSortedByNumberOfOrders".toMessage(),
            listOf(bajram, reka, lucas, cooper, asuka, nathan), shop.customersReportByCity().map { it.name }
        )
    }
}
