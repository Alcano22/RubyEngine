package ruby.core

import ruby.scene.SceneManager

abstract class BlazeGame {

    val engine: Engine
        get() = Engine.instance

    abstract fun createScenes(sceneManager: SceneManager)
    abstract fun init()
    abstract fun start()

}