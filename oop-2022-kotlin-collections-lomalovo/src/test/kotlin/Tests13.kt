import org.junit.Assert
import org.junit.Test

class Test13 {
    private val originalList = (0..15).toList()
    private fun <T : Any> List<T>.makeOddWithChunks() = chunked(2).mapNotNull { it.getOrNull(1) }
    private val oddList = OddList(originalList)

    @Test
    fun testContains() {
        originalList.forEach {
            if (it % 2 == 1) {
                Assert.assertTrue("Неверная реализация contains", oddList.contains(it))
            } else {
                Assert.assertFalse("Неверная реализация contains", oddList.contains(it))
            }
        }
    }

    @Test
    fun testContainsAll() {
        Assert.assertTrue("Неверная реализация containsAll", oddList.containsAll(originalList.filter { it % 2 == 1 }))
        Assert.assertTrue("Неверная реализация containsAll", !oddList.containsAll(originalList.take(5)))
    }

    @Test
    fun testGet() {
        Assert.assertEquals("Неверная реализация get", originalList[1], oddList[0])
        Assert.assertEquals("Неверная реализация get", originalList[13], oddList[6])
    }

    @Test
    fun testIndexOf() {
        originalList.forEach {
            Assert.assertEquals("Неверная реализация indexOf", if (it % 2 == 1) it / 2 else -1, oddList.indexOf(it))
        }
    }

    @Test
    fun testIsEmpty() {
        Assert.assertFalse("Неверная реализация isEmpty", oddList.isEmpty())
        Assert.assertTrue("Неверная реализация isEmpty", OddList(listOf(1)).isEmpty())
    }

    @Test
    fun testLastIndexOf() {
        val o = listOf(1, 2, 3, 4, 5, 1, 3)
        val c = o.makeOddWithChunks()
        val odd = OddList(o)

        o.forEach {
            Assert.assertEquals("Неверная реализация lastIndexOf", c.lastIndexOf(it), odd.lastIndexOf(it))
        }
    }

    @Test
    fun testTransparentGet() {
        val c = originalList.toMutableList()
        val o = OddList(c)
        Assert.assertEquals(c[1], o[0])
        c[1] = 321
        Assert.assertEquals(
            "Неверная реализация get, ожидается, " +
                "что изменения элементов оригинального списка отражаются на содержимом OddList",
            c[1],
            o[0]
        )
    }

    @Test
    fun testEquals() {
        val c = originalList.makeOddWithChunks()
        Assert.assertTrue("Неверная реализация equals", oddList == c)
        Assert.assertTrue("Неверная реализация equals", oddList != c.reversed())
        Assert.assertTrue("Неверная реализация equals", oddList != c.take(1))
    }

    @Test
    fun testIteratorSimple() {
        val c = originalList.makeOddWithChunks()
        Assert.assertEquals("Неверная реализация iterator", c.joinToString(), oddList.joinToString())

        val o1 = originalList.drop(1)
        val c1 = o1.makeOddWithChunks()
        Assert.assertEquals("Неверная реализация iterator", c1, OddList(o1))
    }

    @Test
    fun testIteratorToAndFrom() {
        val c = originalList.makeOddWithChunks()
        val o = OddList(originalList)
        val ci = c.listIterator()
        val co = o.listIterator()
        while (ci.hasNext()) {
            Assert.assertTrue("Неверная реализация hasNext или неверное состояние итератора", co.hasNext())
            Assert.assertEquals("Неверная реализация next или неверное состояние итератора", ci.next(), co.next())
        }
        Assert.assertFalse("Неверная реализация hasNext или неверное состояние итератора", co.hasNext())
        while (ci.hasPrevious()) {
            Assert.assertTrue("Неверная реализация hasPrevious или неверное состояние итератора", co.hasPrevious())
            Assert.assertEquals(
                "Неверная реализация previous или неверное состояние итератора",
                ci.previous(),
                co.previous()
            )
        }
        Assert.assertFalse("Неверная реализация hasPrevious или неверное состояние итератора", co.hasPrevious())
        while (ci.hasNext()) {
            Assert.assertTrue("Неверная реализация hasNext или неверное состояние итератора", co.hasNext())
            Assert.assertEquals("Неверная реализация next или неверное состояние итератора", ci.next(), co.next())
        }
        Assert.assertFalse("Неверная реализация hasNext или неверное состояние итератора", co.hasNext())
    }

    @Test
    fun testIteratorFromLastToFirst() {
        val c = originalList.makeOddWithChunks()
        val o = OddList(originalList)
        val ci = c.listIterator(c.lastIndex)
        val co = o.listIterator(o.lastIndex)
        while (ci.hasPrevious()) {
            Assert.assertTrue("Неверная реализация hasPrevious или неверное состояние итератора", co.hasPrevious())
            Assert.assertEquals(
                "Неверная реализация previous или неверное состояние итератора",
                ci.previous(),
                co.previous()
            )
        }
        Assert.assertFalse("Неверная реализация hasPrevious или неверное состояние итератора", co.hasPrevious())
        while (ci.hasNext()) {
            Assert.assertTrue("Неверная реализация hasNext или неверное состояние итератора", co.hasNext())
            Assert.assertEquals("Неверная реализация next или неверное состояние итератора", ci.next(), co.next())
        }
        Assert.assertFalse("Неверная реализация hasNext или неверное состояние итератора", co.hasNext())
        while (ci.hasPrevious()) {
            Assert.assertTrue("Неверная реализация hasPrevious или неверное состояние итератора", co.hasPrevious())
            Assert.assertEquals(
                "Неверная реализация previous или неверное состояние итератора",
                ci.previous(),
                co.previous()
            )
        }
    }

    @Test
    fun testSublist() {
        val c = originalList.makeOddWithChunks()
        val o = oddList
        for (i in c.indices) {
            for (j in i..c.lastIndex) {
                val cs = c.subList(i, j)
                val os = o.subList(i, j)
                Assert.assertEquals("Неверная реализация sublist", cs.joinToString(), os.joinToString())
            }
        }
    }

    @Test
    fun testSublistShallow() {
        val m = originalList.toMutableList()
        val o = OddList(m)
        val c = m.makeOddWithChunks()
        val os = o.subList(1, o.lastIndex)
        val oss = os.subList(1, os.lastIndex)
        Assert.assertEquals("Неверная реализация subList при вызове subList(subList(...))", c[2], oss[0])
        m[5] = 321
        Assert.assertEquals(
            "Неверная реализация subList: результат должен отражать изменения на соответствующих позициях в " +
                "оригинальном списке",
            321, oss[0]
        )
    }

    @Test
    fun testIteratorNextPreviousIndex() {
        val c = originalList.makeOddWithChunks()
        val o = OddList(originalList)
        val ci = c.listIterator()
        val oi = o.listIterator()
        while (ci.hasNext()) {
            Assert.assertEquals("Неверная реализация nextIndex", ci.nextIndex(), oi.nextIndex())
            Assert.assertEquals("Неверная реализация previousIndex", ci.previousIndex(), oi.previousIndex())
            ci.next()
            oi.next()
        }
        Assert.assertEquals("Неверная реализация nextIndex", ci.nextIndex(), oi.nextIndex())
        Assert.assertEquals("Неверная реализация previousIndex", ci.previousIndex(), oi.previousIndex())
    }

    @Test
    fun testIteratorThrowsNoSuchElement() {
        for (l in listOf(originalList, originalList.dropLast(1))) {
            val o = OddList(l)
            val i = o.listIterator(o.lastIndex)
            val n = i.next()
            try {
                i.next()
                Assert.fail("Неверная реализация next: необходимо бросать NoSuchElementException, когда элемента нет")
            } catch (e: NoSuchElementException) {
            }
            val p = i.previous()
            Assert.assertEquals("Неверная реализация previous или неверное состояние итератора", n, p)
        }
    }
}
