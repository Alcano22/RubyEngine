package ruby.util

import java.text.SimpleDateFormat
import java.util.*

object Time {

    var frames: Int = 0
    var deltaTime: Float = 0f

    val time: Float
        get() = frames / 60f

    fun getTimestamp(format: String = "HH:mm:ss"): String {
        return SimpleDateFormat(format).format(Date())
    }

}