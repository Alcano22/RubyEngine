package ruby.scene

import ruby.gameobject.GameObject
import java.awt.Color
import java.awt.Graphics

class Scene(val id: String = "scene-${SceneManager.instance.getNextSceneIndex()}") {

    val gameObjects: MutableList<GameObject> = ArrayList()
    var backgroundColor: Color = Color.BLACK

    init {
        val manager = SceneManager.instance
        manager += this

        if (manager.currentScene == null) {
            manager.load(this)
        }
    }

    fun start() {
        val cameraObj = GameObject()
        cameraObj.addComponent<Camera>()
        this.gameObjects += cameraObj

        gameObjects.forEach { it.start() }
    }

    fun update() {
        gameObjects.forEach { it.update() }
    }

    fun render(gfx: Graphics) {
        gameObjects.forEach { it.render(gfx) }
    }

}