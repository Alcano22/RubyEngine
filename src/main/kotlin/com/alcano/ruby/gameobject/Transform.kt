package com.alcano.ruby.gameobject

import com.alcano.ruby.util.Debug
import com.alcano.ruby.math.Vector2f

class Transform(
    var position: Vector2f = Vector2f.ZERO,
    var rotation: Float = 0f,
    var scale: Vector2f = Vector2f.ONE
) {

    override fun toString(): String = Debug.toFieldString(this)

}