package com.alcano.ruby.input

import com.alcano.ruby.math.Vector2f
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class Input: KeyListener, MouseListener {

    companion object {
        const val NUM_KEYS = 255

        private var instance: Input? = null

        fun get(): Input {
            if (instance == null) {
                instance = Input()
            }

            return instance!!
        }

        private fun getKeyPressed(key: Int): Boolean = get().keysPressed[key]

        fun getKey(key: Int): Boolean = get().keysCurrent[key]
        fun getKeyDown(key: Int): Boolean = get().keysDown[key]
        fun getKeyUp(key: Int): Boolean = get().keysUp[key]

        fun getAxis(axis: Axis): Float {
            if (axis == Axis.HORIZONTAL) {
                if (getKey(KeyCode.A)) return -1f
                if (getKey(KeyCode.D)) return 1f
            } else {
                if (getKey(KeyCode.W)) return 1f
                if (getKey(KeyCode.S)) return -1f
            }

            return 0f
        }

        fun getAxes(): Vector2f = Vector2f(getAxis(Axis.HORIZONTAL), getAxis(Axis.VERTICAL))
    }

    private val keysPressed: BooleanArray = BooleanArray(NUM_KEYS)
    private val keysCurrent: BooleanArray = BooleanArray(NUM_KEYS)
    private val keysDown: BooleanArray = BooleanArray(NUM_KEYS)
    private val keysUp: BooleanArray = BooleanArray(NUM_KEYS)

    fun update() {
        this.keysUp.fill(false)
        for (i in 0 until NUM_KEYS) {
            if (!getKeyPressed(i) && this.keysCurrent[i]) {
                this.keysUp[i] = true
            }
        }

        this.keysDown.fill(false)
        for (i in 0 until NUM_KEYS) {
            if (getKeyPressed(i) && !this.keysCurrent[i]) {
                this.keysDown[i] = true
            }
        }

        this.keysCurrent.fill(false)
        for (i in 0 until NUM_KEYS) {
            if (getKeyPressed(i)) {
                this.keysCurrent[i] = true
            }
        }
    }

    override fun keyPressed(e: KeyEvent?) {
        this.keysPressed[e!!.keyCode] = true
    }

    override fun keyReleased(e: KeyEvent?) {
        this.keysPressed[e!!.keyCode] = false
    }

    override fun keyTyped(e: KeyEvent?) {
    }

    override fun mouseClicked(e: MouseEvent?) {
    }

    override fun mousePressed(e: MouseEvent?) {
    }

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }

    enum class Axis {
        HORIZONTAL,
        VERTICAL
    }
}