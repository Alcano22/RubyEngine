package com.alcano.ruby.core

import com.alcano.ruby.util.Time

class GameLoop(private val engine: Engine): Runnable {

    companion object {
        const val TARGET_FPS = 60f
        const val TARGET_UPDATE_INTERVAL = 1f / TARGET_FPS
    }

    override fun run() {
        var lastTime = System.nanoTime()
        while (engine.isRunning) {
            val currentTime = System.nanoTime()
            val elapsedTime = (currentTime - lastTime) / 1000000000f
            lastTime = currentTime

            Time.deltaTime += elapsedTime

            while (Time.deltaTime >= TARGET_UPDATE_INTERVAL) {
                engine.update()
                Time.deltaTime -= TARGET_UPDATE_INTERVAL
            }

            engine.render()
        }

        engine.stop()
    }

}