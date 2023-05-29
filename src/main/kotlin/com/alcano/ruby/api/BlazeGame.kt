package com.alcano.ruby.api

import com.alcano.ruby.core.Engine
import com.alcano.ruby.scene.SceneManager

abstract class BlazeGame {

    val engine: Engine
        get() = Engine.instance

    abstract fun createScenes(sceneManager: SceneManager)
    abstract fun init()
    abstract fun start()

}