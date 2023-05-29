package com.alcano.ruby.resources

import com.alcano.ruby.util.Debug
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

object Resources {

    private val fileMap: MutableMap<String, File> = HashMap()
    private val textureMap: MutableMap<String, BufferedImage> = HashMap()
    private val audioClipMap: MutableMap<String, Clip> = HashMap()

    val files: ResourceLoader<File> = ResourceLoader {
        if (fileMap.containsKey(it)) {
            return@ResourceLoader fileMap[it]!!
        }

        val file = File(it)
        if (!file.exists()) {
            Debug.log("No file was found under '$it'", Debug.Level.ERROR)
            return@ResourceLoader null
        }

        fileMap[it] = file

        return@ResourceLoader file
    }

    val textures: ResourceLoader<BufferedImage> = ResourceLoader {
        if (textureMap.containsKey(it)) {
            return@ResourceLoader textureMap[it]!!
        }

        val file = File(it)
        if (!file.exists()) {
            Debug.log("No texture was found under '$it'", Debug.Level.ERROR)
            return@ResourceLoader null
        }

        val texture = ImageIO.read(file)
        textureMap[it] = texture

        return@ResourceLoader texture
    }

    val audioClips: ResourceLoader<Clip> = ResourceLoader {
        if (audioClipMap.contains(it)) {
            return@ResourceLoader audioClipMap[it]!!
        }

        val file = File(it)
        if (!file.exists()) {
            Debug.log("No sound was found under '$it'", Debug.Level.ERROR)
            return@ResourceLoader null
        }

        val audioClip = AudioSystem.getClip()
        audioClip.open(AudioSystem.getAudioInputStream(file))
        audioClipMap[it] = audioClip

        return@ResourceLoader audioClip
    }

}