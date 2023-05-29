package ruby.math

import kotlin.math.pow
import kotlin.math.sqrt

class Vector2f(var x: Float, var y: Float) {

    companion object {
        val ZERO = Vector2f(0f, 0f)
        val ONE = Vector2f(1f, 1f)

        fun lerp(a: Vector2f, b: Vector2f, t: Float) = a + (b - a) * t
    }

    val invertedX: Vector2f
        get() = Vector2f(-this.x, this.y)
    val invertedY: Vector2f
        get() = Vector2f(this.x, -this.y)
    val magnitude: Float
        get() = sqrt(this.x * this.x + this.y * this.y)
    val normalized: Vector2f
        get() {
            if (magnitude == 0f) return ZERO

            return Vector2f(this.x / this.magnitude, this.y / this.magnitude)
        }

    constructor(x: Float) : this(x, 0f)
    constructor() : this(0f, 0f)

    fun normalize() {
        this.x = this.normalized.x
        this.y = this.normalized.y
    }

    operator fun unaryMinus() = this * -1f

    operator fun plus(float: Float): Vector2f = Vector2f(this.x + float, this.y + float)
    operator fun minus(float: Float): Vector2f = Vector2f(this.x - float, this.y - float)
    operator fun times(float: Float): Vector2f = Vector2f(this.x * float, this.y * float)
    operator fun div(float: Float): Vector2f = Vector2f(this.x / float, this.y / float)
    operator fun rem(float: Float): Vector2f = Vector2f(this.x % float, this.y % float)

    operator fun plus(vec2f: Vector2f): Vector2f = Vector2f(this.x + vec2f.x, this.y + vec2f.y)
    operator fun minus(vec2f: Vector2f): Vector2f = Vector2f(this.x - vec2f.x, this.y - vec2f.y)
    operator fun times(vec2f: Vector2f): Vector2f = Vector2f(this.x * vec2f.x, this.y * vec2f.y)
    operator fun div(vec2f: Vector2f): Vector2f = Vector2f(this.x / vec2f.x, this.y / vec2f.y)
    operator fun rem(vec2f: Vector2f): Vector2f = Vector2f(this.x % vec2f.x, this.y / vec2f.y)

    infix fun dst(vec2f: Vector2f): Float =
        sqrt((this.x - vec2f.x).pow(2) + (this.y - vec2f.y).pow(2))

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Vector2f) return false

        return this.x == other.x && this.y == other.y
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String = "($x, $y)"

}