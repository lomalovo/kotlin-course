import kotlin.IllegalStateException

open class Box<T>(var element: T?){
    fun view():T {
        if (element != null) return element!!
        else throw IllegalStateException("Box is empty")
    }
    fun put(smth: T){
        if (element==null) element = smth
        else throw IllegalStateException("Box is not empty")
    }
    fun get():T{
        if (element!=null) {
            val ans = element
            element=null
            return ans!!
        }
        else throw IllegalStateException("Box is empty")
    }
}

fun <T,J>Box<T>.convert(function: (T) -> J): Box<J>{
    return Box(function(this.get()))
}


class BoxList<T>(): ArrayList<Box<T>>() {
    fun add(elem: T){
        this.add(DecoratedBox(elem))
    }
    class DecoratedBox<T>(element: T?) : Box<T>(element) {
        override fun toString(): String {
            return "[$element]"
        }
    }

}


fun part1() {
    println("=== Часть 1: класс Box ===")
    val b = Box(6)
    println("Box b: ${b.view()}")
    try {
        b.put(6)
    } catch (e: IllegalStateException) {
        println("ERROR: ${e.message}")
    }
    b.get()
    try {
        println("Box b: ${b.view()}")
    } catch (e: IllegalStateException) {
        println("ERROR: ${e.message}")
    }
    try {
        b.get()
    } catch (e: IllegalStateException) {
        println("ERROR: ${e.message}")
    }
    b.put(0)
    println("Box b: ${b.view()}")

    val b2 = Box("hello")
    println("Box b2: ${b2.view()}")
    val s = b2.get()
    println("String's length is ${s.length}")
    b2.put("bye")
    val s2 = b2.get()
    println("String's length is ${s2.length}")

}


fun part2() {
    println("\n=== Часть 2: функция convert ===")
    val intBox = Box(42)
    val stringBox = intBox.convert { "$it!" }
    println("Box stringBox: ${stringBox.view()}")
    try {
        println("Box intBox: ${intBox.view()}")
    } catch (e: IllegalStateException) {
        println("ERROR: ${e.message}")
    }
    val intBox2 = stringBox.convert { it.length }
    println("Box intBox2: ${intBox2.view()}")
    val intBox3 = intBox2.convert { it + 1 }
    println("Box intBox3: ${intBox3.view()}")
}


fun part3() {
    println("\n=== Часть 3: класс BoxList ===")
    val intBoxes = BoxList<Int>()
    (1..8).forEach { intBoxes.add(it) }
    println(intBoxes)
    
    val stringBoxes = BoxList<String>()
    ('a'..'h').map { it.toString() }
        .forEach { stringBoxes.add(it) }
    println(stringBoxes)
}

fun main() {
    part1()
    part2()
    part3()
}