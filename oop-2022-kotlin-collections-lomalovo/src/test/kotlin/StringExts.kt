fun String.toMessage() = "The function '$this' is implemented incorrectly"

fun String.toMessageInEquals() = toMessage().inEquals()

fun String.inEquals() = this + ": "
