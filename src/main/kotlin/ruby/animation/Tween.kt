package ruby.animation

import ruby.math.Vector2f
import ruby.math.pow
import kotlin.math.*
import kotlin.reflect.KMutableProperty0
import kotlin.time.Duration

object Tween {

    private inline fun <T> tween(
        valueRef: KMutableProperty0<T>,
        to: T,
        duration: Duration,
        noinline easing: Easing = Easings.NO_EASING,
        noinline onComplete: () -> Unit = {},
        crossinline interpolator: (a: T, b: T, it: Float) -> T
    ) {
        val startValue = valueRef.get()
        val durationSecs = duration.inWholeSeconds.toFloat()
        var currentTime = 0f

        Thread {
            while (currentTime <= durationSecs) {
                val it = currentTime / durationSecs
                val easedT = easing.invoke(it)

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
    ) = tween(valueRef, to, duration, easing, onComplete) { a, b, it -> a + (b - a) * it }

    fun vector2f(
        valueRef: KMutableProperty0<Vector2f>,
        to: Vector2f,
        duration: Duration,
        easing: Easing,
        onComplete: () -> Unit = {}
    ) = tween(valueRef, to, duration, easing, onComplete) { a, b, it -> a + (b - a) * it }

    object Easings {
        private const val C1 = 1.70158f
        private const val C2 = this.C1 * 1.525f
        private const val C3 = this.C1 + 1f
        private const val C4 = (2 * PI.toFloat()) / 3
        private const val C5 = (2 * PI.toFloat()) / 4.5f
        private const val C6 = 7.5625f
        private const val C7 = 2.75f

        val NO_EASING: Easing = { it }
        val IN_SINE: Easing = { 1 - cos((it * PI.toFloat()) / 2) }
        val OUT_SINE: Easing = { sin((it * PI.toFloat()) / 2) }
        val IN_OUT_SINE: Easing = { -(cos(PI.toFloat() * it) - 1) / 2 }
        val IN_QUAD: Easing = { it * it }
        val OUT_QUAD: Easing = { 1 - (1 - it).pow(2) }
        val IN_OUT_QUAD: Easing = {
            if (it < .5f) 2 * it * it
            else 1 - (-2 * it + 2).pow(2) / 2
        }
        val IN_CUBIC: Easing = { it.pow(3) }
        val OUT_CUBIC: Easing = { 1 - (1 - it).pow(3) }
        val IN_OUT_CUBIC: Easing = {
            if (it < .5f) 4 * it.pow(3)
            else 1 - (-2 * it + 2).pow(3) / 2
        }
        val IN_QUART: Easing = { it.pow(4) }
        val OUT_QUART: Easing = { 1 - (1 - it).pow(4) }
        val IN_OUT_QUART: Easing = {
            if (it < .5f) 8 * it.pow(4)
            else 1 - (-2 * it + 2).pow(4) / 2
        }
        val IN_QUINT: Easing = { it.pow(5) }
        val OUT_QUINT: Easing = { 1 - (1 - it).pow(5) }
        val IN_OUT_QUINT: Easing = {
            if (it < .5f) 16 * it.pow(5)
            else 1 - (-2 * it + 2).pow(5) / 2
        }
        val IN_EXPO: Easing = {
            if (it == 0f) 0f
            else 2.pow(10 * it - 10)
        }
        val OUT_EXPO: Easing = {
            if (it == 1f) 1f
            else 1 - 2.pow(-10 * it)
        }
        val IN_OUT_EXPO: Easing = {
            when {
                it == 0f -> 0f
                it == 1f -> 1f
                it < .5f -> 2.pow(20 * it - 10) / 2
                else -> (2 - 2.pow(-20 * it + 10)) / 2
            }
        }
        val IN_CIRC: Easing = { 1 - sqrt(1 - it.pow(2)) }
        val OUT_CIRC: Easing = { 1 - (it - 1).pow(2) }
        val IN_OUT_CIRC: Easing = {
            if (it < .5f) (1 - sqrt(1 - (2 * it).pow(2))) / 2
            else (sqrt(1 - (-2 * it + 2).pow(2)) + 1) / 2
        }
        val IN_BACK: Easing = { C3 * it.pow(3) - C1 * it * it }
        val OUT_BACK: Easing = { 1 + C3 * (it - 1).pow(3) + C1 * (it - 1).pow(2) }
        val IN_OUT_BACK: Easing = {
            if (it < .5f) ((2 * it).pow(2) * ((C2 + 1) * 2 * it - C2)) / 2
            else ((2 * it - 2).pow(2) * ((C2 + 1) * (it * 2 - 2) + C2) + 2) / 2
        }
        val IN_ELASTIC: Easing = {
            when (it) {
                0f -> 0f
                1f -> 1f
                else -> (-2).pow(10 * it - 10) * sin((it * 10 - 10.75f) * C4)
            }
        }
        val OUT_ELASTIC: Easing = {
            when (it) {
                0f -> 0f
                1f -> 1f
                else -> 2.pow(-10 * it) * sin((it * 10 - 0.75f) * C4) + 1
            }
        }
        val IN_OUT_ELASTIC: Easing = {
            when {
                it == 0f -> 0f
                it == 1f -> 1f
                it < .5f -> -(2.pow(20 * it - 10) * sin((20 * it - 11.125f) * C5)) / 2
                else -> (2.pow(-20 * it + 10) * sin((20 * it - 11.125f) * C5)) / 2 + 1
            }
        }
        val IN_BOUNCE: Easing = { 1 - OUT_BOUNCE.invoke(1 - it) }
        val OUT_BOUNCE: Easing = {
            when {
                it < 1f / C7 -> C6 * it * it
                it < 2f / C7 -> C6 * (it - 1.5f / C7) * it + .75f
                it < 2.5f / C7 -> C6 * (it - 2.25f / C7) * it + .9375f
                else -> C6 * (it - 2.625f / C7) * it + .984375f
            }
        }
        val IN_OUT_BOUNCE: Easing = {
            if (it < .5f) (1 - OUT_BOUNCE.invoke(1 - 2 * it)) / 2
            else (1 + OUT_BOUNCE.invoke(2 * it - 1)) / 2
        }
    }

}

private typealias Easing = (it: Float) -> Float