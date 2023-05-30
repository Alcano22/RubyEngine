package ruby.gameobject.component

import ruby.gameobject.GameObject
import ruby.gameobject.Transform
import ruby.scene.Scene
import ruby.scene.SceneManager
import java.awt.Graphics

abstract class Component {

    private lateinit var gameObjectUUID: String

    val gameObject: GameObject
        get() = GameObject.find(gameObjectUUID)
    val transform: Transform
        get() = gameObject.transform
    val scene: Scene
        get() = SceneManager.instance.currentScene!!

    var canUpdate = false

    open fun start() {}
    open fun update() {}
    open fun render(gfx: Graphics) {}

    protected inline fun <reified T : Component> addComponent(): T = this.gameObject.addComponent<T>()
    protected inline fun <reified T : Component> removeComponent() = this.gameObject.removeComponent<T>()
    protected inline fun <reified T : Component> getComponents(): List<T> = this.gameObject.getComponents<T>()
    protected inline fun <reified T : Component> getComponent(): T? = this.gameObject.getComponent<T>()

    fun initGameObject(uuid: String) {
        gameObjectUUID = uuid
    }

}