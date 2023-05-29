package com.alcano.ruby.extension

import kotlin.random.Random

fun Random.nextFloat(until: Float): Float =
    Random.nextDouble(until.toDouble()).toFloat()
fun Random.nextFloat(from: Float, until: Float): Float =
    Random.nextDouble(from.toDouble(), until.toDouble()).toFloat()