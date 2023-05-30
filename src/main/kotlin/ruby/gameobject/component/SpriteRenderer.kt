package ruby.gameobject.component

import ruby.extension.tint
import ruby.gameobject.GameObject
import ruby.graphics.Colors
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage

class SpriteRenderer : Component() {

    var color: Color? = Colors.WHITE
    var texture: BufferedImage? = null

    override fun render(gfx: Graphics) {
        val pixelsPerUnit = GameObject.findComponent<Camera>()!!.pixelsPerUnit

        val scaleX = this.transform.scale.x * pixelsPerUnit
        val scaleY = this.transform.scale.y * pixelsPerUnit
        val posX = this.transform.position.x * pixelsPerUnit - scaleX / 2
        val posY = this.transform.position.y * pixelsPerUnit - scaleY / 2

        if (this.texture == null && this.color != null) {
            gfx.color = this.color
            gfx.fillRect(posX.toInt(), posY.toInt(), scaleX.toInt(), scaleY.toInt())
        } else {
            val modifiedTexture = if (this.color == null) this.texture!! else this.texture!!.tint(this.color!!)

            gfx.drawImage(
                modifiedTexture,
                posX.toInt(),
                posY.toInt(),
                scaleX.toInt(),
                scaleY.toInt(),
                null
            )
        }
    }
}