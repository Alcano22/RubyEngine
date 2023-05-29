package com.alcano.ruby.math

import kotlin.math.pow

infix fun Int.pow(exponent: Int): Int = this.toDouble().pow(exponent.toDouble()).toInt()
infix fun Float.pow(exponent: Float): Float = this.toDouble().pow(exponent.toDouble()).toFloat()

fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t