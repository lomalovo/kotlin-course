import kotlin.test.Test
import kotlin.test.assertEquals

class Task3Test {
    @Test
    fun fTest() {
        assertEquals(1.0, f(0.0), 1e-5)
        assertEquals(0.0, f(-3.0), 1e-5)
        assertEquals(-1.0, f(1.0), 1e-5)
        assertEquals(1.0, f(2.0), 1e-5)
        assertEquals(1.0, f(10.0), 1e-5)
        assertEquals(-1.0, f(13.0), 1e-5)
    }

    @Test
    fun numberOfDaysTest() {
        assertEquals(365, numberOfDays(2021))
        assertEquals(365, numberOfDays(900))
        assertEquals(366, numberOfDays(8888))
        assertEquals(366, numberOfDays(2000))
        assertEquals(366, numberOfDays(11144))
    }

    @Test
    fun rotate2Test() {
        assertEquals('С', rotate2('С', 1, -1))
        assertEquals('С', rotate2('Ю', 1, 1))
        assertEquals('С', rotate2('З', 1, 2))
        assertEquals('З', rotate2('В', -1, -1))
        assertEquals('З', rotate2('С', -1, 2))
        assertEquals('Ю', rotate2('Ю', 2, 2))
    }

    @Test
    fun ageDescriptionTest() {
        assertEquals("сорок два года", ageDescription(42))
        assertEquals("пятьдесят пять лет", ageDescription(55))
        assertEquals("двадцать три года", ageDescription(23))
        assertEquals("тридцать лет", ageDescription(30))
        assertEquals("сорок один год", ageDescription(41))
        assertEquals("шестьдесят девять лет", ageDescription(69))
        assertEquals("двадцать четыре года", ageDescription(24))
        assertEquals("тридцать шесть лет", ageDescription(36))
        assertEquals("пятьдесят семь лет", ageDescription(57))
        assertEquals("шестьдесят восемь лет", ageDescription(68))

    }

}
