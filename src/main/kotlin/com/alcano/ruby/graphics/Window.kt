package com.alcano.ruby.graphics

import com.alcano.ruby.gameobject.GameObject
import com.alcano.ruby.scene.SceneManager
import com.alcano.ruby.util.Debug
import com.alcano.ruby.input.Input
import com.alcano.ruby.scene.Camera
import java.awt.Canvas
import java.awt.Dimension
import javax.swing.JFrame

class Window(private var _settings: Settings) : JFrame() {

    val canvas = Canvas()
    var settings: Settings
        get() = this._settings
        set(value) {
            this._settings = value
            this.updateSettings()
        }

    init {
        this.updateSettings()

        this.canvas.addKeyListener(Input.get())
        this.add(this.canvas)
        this.pack()

        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.setLocationRelativeTo(null)

        this.isVisible = true
    }

    private fun updateSettings() {
        this.canvas.size = Dimension(settings.width, settings.height)
        this.title = settings.title
        this.isResizable = settings.isResizable

        this.pack()
    }

    fun render() {
        val bs = this.canvas.bufferStrategy
        if (bs == null) {
            this.canvas.createBufferStrategy(3)
            return
        }

        val gfx = bs.drawGraphics

        val currentScene = SceneManager.instance.currentScene!!
        gfx.color = currentScene.backgroundColor
        gfx.fillRect(0, 0, this.width, this.height)

        val cam = GameObject.findWithComponent<Camera>()!!
        gfx.translate(
            width / 2 + cam.transform.position.x.toInt(),
            height / 2 + cam.transform.position.y.toInt()
        )

        currentScene.render(gfx)

        bs.show()
        gfx.dispose()
    }

    class Settings {
        var width = 1024
        var height = 720
        var title = "Blaze Game"
        var isResizable = false

        override fun toString(): String = Debug.toFieldString(this)
    }

    override fun getWidth(): Int {
        return this.canvas.width
    }

    override fun getHeight(): Int {
        return this.canvas.height
    }

}