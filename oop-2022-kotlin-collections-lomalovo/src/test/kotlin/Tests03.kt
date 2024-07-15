import org.junit.Assert
import org.junit.Test

class TestAllAnyAndOtherPredicates {
    @Test fun testAllCustomersAreFromCity() {
        Assert.assertFalse("checkAllCustomersAreFrom".toMessage(), shop.checkAllCustomersAreFrom(Canberra))
        Assert.assertTrue("checkAllCustomersAreFrom".toMessage(), Shop("other", customers.values.filter {
            it.city == Canberra
        }).checkAllCustomersAreFrom(Canberra))
    }

    @Test fun testAnyCustomerWithAtLeastOrdersSize() {
        Assert.assertTrue("hasCustomerFrom".toMessage(), shop.hasCustomerWithAtLeastOrders(3))
        Assert.assertFalse("hasCustomerFrom".toMessage(), shop.hasCustomerWithAtLeastOrders(4))
    }

    @Test fun testCountCustomersFromCity() {
        Assert.assertEquals("countCustomersFrom".toMessage(), 3, shop.countCustomersFrom(setOf(Canberra, Ankara)))
        Assert.assertEquals("countCustomersFrom".toMessage(), 2, shop.countCustomersFrom(setOf(Canberra)))
    }

    @Test fun testAnyCustomerFromCity() {
        Assert.assertEquals("findAnyCustomerFrom".toMessage(), customers[lucas], shop.findAnyCustomerFrom(Canberra))
        Assert.assertEquals("findAnyCustomerFrom".toMessage(), null, shop.findAnyCustomerFrom(City("17")))
    }
}
