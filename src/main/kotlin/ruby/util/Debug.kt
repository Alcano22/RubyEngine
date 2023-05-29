package ruby.util

object Debug {
    fun log(message: Any?, color: ConsoleColor) {
        println("${color.code}[${Time.getTimestamp()}]$message${ConsoleColor.RESET.code}")
    }

    fun log(message: Any?, level: Level = Level.INFO) =
        log("[$level] $message ${if (level != Level.INFO) "| ${this.getClassAndLineInfo()}" else ""}", level.color)

    private fun getClassAndLineInfo(): String {
        val stackTrace = Thread.currentThread().stackTrace

        for (i in stackTrace.indices.reversed()) {
            val caller = stackTrace[i]

            if (caller.className.contains("com.alcano.ruby")) {
                return "${stackTrace[i - 1].className.split(".").last()}:${stackTrace[i - 1].lineNumber}"
            }
        }

        return "Unknown source"
    }

    fun <T : Any> toFieldString(any: T): String {
        val includedFields =
            any.javaClass.declaredFields.filter { !it.isAnnotationPresent(HideInFieldString::class.java) }

        var string = "${any.javaClass.simpleName}{"
        for (i in includedFields.indices) {
            val field = includedFields[i]
            field.isAccessible = true

            string += "${field.name}: ${field[any]}"

            field.isAccessible = false

            if (i != includedFields.lastIndex) {
                string += ", "
            }
        }

        return "$string}"
    }

    enum class Level(val color: ConsoleColor) {
        INFO(ConsoleColor.RESET),
        WARNING(ConsoleColor.YELLOW),
        ERROR(ConsoleColor.RED)
    }
}