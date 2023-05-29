package com.alcano.ruby.scene

import com.alcano.ruby.core.Engine

class SceneManager {

    companion object {
        val instance: SceneManager
            get() = Engine.instance.sceneManager
    }

    var currentScene: Scene? = null
        private set

    private val sceneMap: MutableMap<String, Scene> = HashMap()

    fun getNextSceneIndex(): Int = sceneMap.size

    fun load(scene: Scene) {
        this.currentScene = scene
        scene.start()
    }

    operator fun plusAssign(scene: Scene) {
        sceneMap[scene.id] = scene
    }

}