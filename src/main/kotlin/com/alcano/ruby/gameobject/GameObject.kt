package com.alcano.ruby.gameobject

import com.alcano.ruby.gameobject.component.Component
import com.alcano.ruby.scene.SceneManager
import java.awt.Graphics
import java.util.*
import kotlin.collections.ArrayList

class GameObject(var transform: Transform = Transform()) {

    val uuid = UUID.randomUUID().toString()
    val componentList: MutableList<Component> = ArrayList()
    var isActive: Boolean = true

    private var canUpdate = false

    companion object {
        fun find(uuid: String): GameObject? {
            val scene = SceneManager.instance.currentScene!!

            return scene.gameObjects.find { it.uuid == uuid }
        }

        inline fun <reified T : Component> findAllWithComponent(): List<GameObject> {
            val scene = SceneManager.instance.currentScene!!

            return scene.gameObjects.filter { it.hasComponent<Component>() }
        }

        inline fun <reified T : Component> findWithComponent(): GameObject? {
            val gameObjects = this.findAllWithComponent<T>()

            if (gameObjects.isEmpty()) return null

            return gameObjects[0]
        }

        inline fun <reified T : Component> findComponents(): List<T> {
            return this.findAllWithComponent<T>().map { it.getComponent<T>()!! }
        }

        inline fun <reified T : Component> findComponent(): T? {
            val gameObject = this.findWithComponent<T>() ?: return null

            return gameObject.getComponent<T>()
        }

        fun instantiate(gameObject: GameObject) {
            val scene = SceneManager.instance.currentScene

            scene!!.gameObjects += gameObject
            gameObject.start()
        }
    }

    fun start() {
        if (!this.isActive) return

        componentList.forEach { it.start() }

        this.canUpdate = true
    }

    fun update() {
        if (!this.isActive || !this.canUpdate) return

        componentList.forEach { it.update() }
    }

    fun render(gfx: Graphics) {
        if (!this.isActive) return

        componentList.forEach { it.render(gfx) }
    }

    inline fun <reified T : Component> addComponent(): T {
        val component = T::class.java.getDeclaredConstructor().newInstance() as T

        component.initGameObject(this.uuid)
        this.componentList += component

        component.start()
        component.canUpdate = true

        return component
    }

    inline fun <reified T : Component> removeComponent() {
        this.componentList.removeAll(this.getComponents<T>())
    }

    inline fun <reified T : Component> getComponents(): List<T> =
        this.componentList
            .filter { T::class.java.isInstance(it) }
            .map { it as T }

    inline fun <reified T : Component> getComponent(): T? {
        val components = this.getComponents<T>()

        if (components.isEmpty()) return null

        return this.getComponents<T>()[0]
    }

    inline fun <reified T : Component> hasComponent(): Boolean {
        return this.getComponent<T>() != null
    }

}