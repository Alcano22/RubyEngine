package ruby.core

import ruby.graphics.Window
import ruby.input.Input
import ruby.scene.SceneManager

class Engine(private val game: BlazeGame, windowSettings: Window.Settings) {

    companion object {
        lateinit var instance: Engine
            private set
    }

    var windowSettings: Window.Settings
        get() = this.window.settings
        set(value) {
            this.window.settings = value
        }

    val window: Window
    val sceneManager: SceneManager
    var isRunning: Boolean = false
        private set

    private var mainThread: Thread

    init {
        instance = this

        this.window = Window(windowSettings)
        this.sceneManager = SceneManager()
        this.mainThread = Thread(GameLoop(this))

        this.game.createScenes(this.sceneManager)
        this.start()
        this.game.init()
        this.game.start()
    }

    fun start() {
        this.mainThread.start()
        this.isRunning = true
    }

    fun stop() {
        this.mainThread.join()
        this.isRunning = false
    }

    fun update() {
        Input.get().update()
        this.sceneManager.currentScene!!.update()
    }

    fun render() = this.window.render()

}