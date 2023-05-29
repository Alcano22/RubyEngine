package ruby.gameobject

import ruby.math.Vector2f
import ruby.util.Debug

class Transform(
    var position: Vector2f = Vector2f.ZERO,
    var rotation: Float = 0f,
    var scale: Vector2f = Vector2f.ONE
) {

    override fun toString(): String = Debug.toFieldString(this)

}