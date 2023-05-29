package ruby.audio

import ruby.util.Debug
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl
import kotlin.math.log10

class Sound(val audioClip: Clip, var volume: Float, var pitch: Float) {

    val isRunning: Boolean
        get() = this.audioClip.isRunning

    var isLoop: Boolean = false

    fun play() {
        this.applyVolume()
        this.applyLoop()

        this.audioClip.framePosition = 0

        val thread = Thread {
            this.audioClip.start()
        }
        thread.start()
        thread.join()
    }

    fun stop() = this.audioClip.stop()

    private fun applyVolume() {
        if (this.volume < 0f || volume > 1f) {
            Debug.log("Sound volume must be between 0.0 and 1.0")
            return
        }

        val gainControl = this.audioClip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
        gainControl.value = 20f * log10(this.volume)
    }

    private fun applyLoop() {
        this.audioClip.setLoopPoints(0, if (this.isLoop) this.audioClip.frameLength - 1 else 0)
        this.audioClip.loop(if (this.isLoop) -1 else 0)
    }

}