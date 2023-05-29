package com.alcano.ruby.animation

import com.alcano.ruby.math.Vector2f
import kotlin.math.PI
import kotlin.math.cos
import kotlin.reflect.KMutableProperty0
import kotlin.time.Duration

object Tween {

    val SINE_EASING: Easing = { .5f - .5f * cos(it * PI.toFloat()) }

    private val NO_EASING: Easing = { it }

    private inline fun <T> tween(
        valueRef: KMutableProperty0<T>,
        to: T,
        duration: Duration,
        noinline easing: Easing = NO_EASING,
        noinline onComplete: () -> Unit = {},
        crossinline interpolator: (a: T, b: T, t: Float) -> T
    ) {
        val startValue = valueRef.get()
        val durationSecs = duration.inWholeSeconds.toFloat()
        var currentTime = 0f

        Thread {
            while (currentTime <= durationSecs) {
                val t = currentTime / durationSecs
                val easedT = easing.invoke(t)

                val interpolatedValue = interpolator.invoke(startValue, to, easedT)
                valueRef.set(interpolatedValue)
                Thread.sleep(10L)

                currentTime += .01f
            }

            onComplete.invoke()
            Thread.currentThread().join()
        }.start()
    }

    fun float(
        valueRef: KMutableProperty0<Float>,
        to: Float,
        duration: Duration,
        easing: Easing,
        onComplete: () -> Unit = {}
    ) = this.tween(valueRef, to, duration, easing, onComplete) { a, b, t -> a + (b - a) * t }

    fun vector2f(
        valueRef: KMutableProperty0<Vector2f>,
        to: Vector2f,
        duration: Duration,
        easing: Easing,
        onComplete: () -> Unit = {}
    ) = this.tween(valueRef, to, duration, easing, onComplete) { a, b, t -> a + (b - a) * t }

}

private typealias Easing = (t: Float) -> Float