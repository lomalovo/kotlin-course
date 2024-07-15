import org.junit.Assert
import org.junit.Test

class TestSum {
    @Test fun testGetTotalOrderPrice() {
        Assert.assertTrue("getTotalOrderPrice".toMessage(), customers[nathan]!!.getTotalOrderPrice() == 148.0)
    }
}
