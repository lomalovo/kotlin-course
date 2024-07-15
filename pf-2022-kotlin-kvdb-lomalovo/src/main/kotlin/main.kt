import java.io.File
//моя база данных в файле устроена так: в каждой строке первым элементом лежит хэшкод ключей из этой же строки,
// а после лежат ключ1 значение1 ключ2 значение2 ключ3 значение3.....
// разделителем ключей и значений является символ separator, это значит, что его нельзя использовать, ни в ключах, ни в значениях
const val separator = '☻'
fun main(args: Array<String>) {
    val command = args[0]
    val filename = args[1]
    if (!File(filename).exists()) throw Exception("File $filename isn't exist")
    val dbReader = File(filename).bufferedReader(bufferSize = 100)
    val dbWriter = File("tmp.txt").bufferedWriter(bufferSize = 100)
    val otherArgs = args.drop(2)
    if (otherArgs.isEmpty()) print("")
    else {
        if (command == "put") {
            var stringFounded = false
            //проверяем DB на наличие в ней нашего ключа, параллельно переписываем файл в tmp.txt,
            // используя при этом ограниченное количество константной доппамяти
            dbReader.forEachLine {
                if (otherArgs[0].hashCode().toString() == it.split("$separator").first()) { //сверяем хэшкод ключа, он совпал
                    if (otherArgs[0] !in getEverySecond(it)) { //ищем ключ в массиве из ключей(смотрим на каждую вторую запись)
                        dbWriter.write(
                            it + "$separator" + otherArgs[0] + "$separator" + otherArgs.drop(1).joinToString(" ")
                        ) //если не нашли, дописываем
                        dbWriter.newLine()
                    } else {
                        val newLine = it.split("$separator").toMutableList() // если нашли
                        newLine[(getEverySecond(it).indexOf(otherArgs[0])) * 2 + 2] =
                            otherArgs.drop(1).joinToString(" ") //заменяем значение на нужное нам
                        dbWriter.write(newLine.joinToString("$separator")) //выписываем
                        dbWriter.newLine()
                    }
                    stringFounded = true //поднимаем флаг, что нашли хэшкод
                } else { //если хэшкод не совпадает, то наш ключ не лежит в этой строке, значит мы просто её перепишем
                    dbWriter.write(it)
                    dbWriter.newLine()
                }
            }
            if (!stringFounded) { //если ни в одной не было нашего хеша, то создаём строку, с новым номером хэша
                dbWriter.write(
                    otherArgs[0].hashCode().toString() + "$separator" + otherArgs[0] + "$separator" + otherArgs.drop(1).joinToString(" ")
                )
            }
            dbWriter.close()
            File("tmp.txt").copyTo(File(filename), true)
        } else {
            if (command == "get") {
                dbReader.forEachLine {
                    if (otherArgs.first().hashCode().toString() == it.split("$separator").first()) { //ищем хэшкод
                        if (getEverySecond(it).indexOf(otherArgs.first()) != -1) { // ищем ключ
                            print(it.split("$separator")[getEverySecond(it).indexOf(otherArgs.first()) * 2 + 2]) //нашли, выписываем значение
                        } else print("") // не нашли в строке с нужным хэшом
                    } else print("") // вообще не нашли хэш
                }
            } else {
                if (command == "remove") {
                    dbReader.forEachLine {
                        if (otherArgs.first().hashCode().toString() == it.split("$separator").first()) {
                            val indexOfKey = getEverySecond(it).indexOf(otherArgs.first())
                            if (indexOfKey != -1) {
                                val newLine = it.split("$separator").toMutableList()
                                newLine.remove(newLine[indexOfKey * 2 + 1]) //удаляем ключ
                                newLine.remove(newLine[indexOfKey * 2 + 1]) //удаляем значение
                                if (newLine.size != 1) { //проверяем, вдруг после удаления в строке будет только 1 элемент - это хэш, если нет, то переписываем в файл
                                    dbWriter.write(newLine.joinToString("$separator"))
                                    dbWriter.newLine()
                                }
                            }
                        } else {
                            dbWriter.write(it)
                            dbWriter.newLine()
                        }
                    }
                    dbWriter.close()
                    File("tmp.txt").copyTo(File(filename), true)
                } else throw Exception("Incorrect command: $command")
            }
        }
    }
    File("tmp.txt").delete()
}

//функция,которая получает на вход строку, делит её на элементы списка и возвращающает список из элементов через один
fun getEverySecond(dbLine:String):List<String>{
    return dbLine.split("$separator").filterIndexed{index, _ -> index%2!=0}
}

