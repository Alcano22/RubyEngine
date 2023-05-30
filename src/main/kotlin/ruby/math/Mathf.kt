package ruby.math

import kotlin.math.pow

fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t

fun Int.pow(exponent: Int): Int = this.toFloat().pow(exponent).toInt()
fun Int.pow(exponent: Float): Float = this.toFloat().pow(exponent)