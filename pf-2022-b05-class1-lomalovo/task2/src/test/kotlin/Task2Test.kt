import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class Task2Test {
    @Test
    fun min3Test1() {
        assertEquals(0, min3(2, 0, 3))
    }

    @Test
    fun min3Test2() {
        assertEquals(-5, min3(2, -5, 0))
    }

    @Test
    fun min3Test3() {
        assertEquals(-1000, min3(-1000, 0, -300))
    }

    @Test
    fun max3Test1() {
        assertEquals(27, max3(2, 27, 3))
    }

    @Test
    fun max3Test2() {
        assertEquals(-51, max3(-1000, -300, -51))
    }

    @Test
    fun max3Test3() {
        assertEquals(77, max3(-123, -432, 77))
    }

    @Test
    fun deg2RadTest1() {
        assertEquals(PI, deg2rad(180.0), 1e-5)
        assertEquals(5*PI, deg2rad(2*360 + 180.0), 1e-5)
    }

    @Test
    fun rad2DegTest1() {
        assertEquals(180.0, rad2deg(PI), 1e-5)
        assertEquals(2*360 + 180.0, rad2deg(5*PI), 1e-5)
    }

    @Test
    fun moreRadDegTests() {
        assertEquals(180.0*1.5, rad2deg(PI*1.5), 1e-5)
        assertEquals(-180.0, rad2deg(-PI), 1e-5)
        assertEquals(0.0, rad2deg(0.0), 1e-5)
        assertEquals(-3.5*180.0, rad2deg(-3.5*PI), 1e-5)
        assertEquals(180.0*99999, rad2deg(PI*99999), 1e-5)
        assertEquals(15*360 + 180.0, rad2deg(31*PI), 1e-5)
        assertEquals(0.0, rad2deg(0.0), 1e-5)
        assertEquals(2*360 + 0.5*180.0, rad2deg(4.5*PI), 1e-5)
        assertEquals(-10*360 + 180.0, rad2deg(-19*PI), 1e-5)
        assertEquals(-15*180.0, rad2deg(-15*PI), 1e-5)
    }

}