package ruby.api

import ruby.scene.SceneManager

abstract class BlazeGame {

    val engine: ruby.core.Engine
        get() = ruby.core.Engine.instance

    abstract fun createScenes(sceneManager: SceneManager)
    abstract fun init()
    abstract fun start()

}