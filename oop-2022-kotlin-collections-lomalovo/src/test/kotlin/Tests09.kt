import org.junit.Assert
import org.junit.Test

class TestPartition {
    @Test fun testGetCustomersWhoHaveMoreUndeliveredOrdersThanDelivered() {
        Assert.assertEquals(
            "getCustomerWithMaximumNumberOfOrders".toMessage(),
            setOf(customers[reka]),
            shop.getCustomersWithMoreUndeliveredOrdersThanDelivered()
        )
    }

    @Test fun testReorderCustomersAtLeastTwoOrdersFirst() {
        Assert.assertEquals(
            "reorderCustomersAtLeastTwoOrdersFirst".toMessage(),
            listOf(lucas, reka, cooper, nathan, bajram, asuka).map { customers.getValue(it) },
            shop.reorderCustomersAtLeastTwoOrdersFirst()
        )
    }
}
