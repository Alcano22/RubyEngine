package com.alcano.ruby.math

import kotlin.math.pow
import kotlin.math.sqrt

class Vector2(var x: Int, var y: Int) {

    companion object {
        val ZERO = Vector2(0, 0)
        val ONE = Vector2(1, 1)
    }

    val invertedX: Vector2
        get() = Vector2(-this.x, this.y)
    val invertedY: Vector2
        get() = Vector2(this.x, -this.y)
    val magnitude: Float
        get() = sqrt(this.x.pow(2).toFloat() + this.y.pow(2).toFloat())
    val normalized: Vector2f
        get() = Vector2f(this.x / this.magnitude, this.y / this.magnitude)

    constructor(x: Int) : this(x, 0)
    constructor(): this(0, 0)

    operator fun plus(int: Int) = Vector2(this.x + int, this.y + int)
    operator fun minus(int: Int) = Vector2(this.x - int, this.y - int)
    operator fun times(int: Int) = Vector2(this.x * int, this.y * int)
    operator fun div(int: Int) = Vector2(this.x / int, this.y / int)
    operator fun rem(int: Int) = Vector2(this.x % int, this.y % int)

    operator fun plus(vec2: Vector2) = Vector2(this.x + vec2.x, this.y + vec2.y)
    operator fun minus(vec2: Vector2) = Vector2(this.x - vec2.x, this.y - vec2.y)
    operator fun times(vec2: Vector2) = Vector2(this.x * vec2.x, this.y * vec2.y)
    operator fun div(vec2: Vector2) = Vector2(this.x / vec2.x, this.y / vec2.y)
    operator fun rem(vec2: Vector2) = Vector2(this.x % vec2.x, this.y % vec2.y)

    infix fun dst(vec2: Vector2): Float =
        sqrt((this.x - vec2.x).toFloat().pow(2) + (this.y - vec2.y).toFloat().pow(2))

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Vector2) return false

        return this.x == other.x && this.y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String = "($x, $y)"

}