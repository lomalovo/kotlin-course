class UnsupportedOperation(private val op: Char) :
    Exception("Operation '$op' is unsupported") {
}

class NotEnoughNumbers(private val available: Int, private val line: String) :
    Exception("More than $available numbers required in $line") {
}

class MissingInputFile(private val file: String) :
    Exception("Input file $file doesn't exist") {
}

class DifferentAmountOfLines(private val file1: String, private val file2: String) :
    Exception("File $file1 and file $file2 contains different amount of lines") {
}

class IncorrectFormatOfString() :
    Exception("Some lines have incorrect format") {
}

class FileExists(private val file: String) :
    Exception("File $file already exists") {
}

class DivideByZero() :
    Exception("You can't divide by zero") {
}