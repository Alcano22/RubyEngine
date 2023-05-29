package ruby.gameobject.component

import ruby.gameobject.GameObject
import ruby.scene.Camera
import java.awt.Graphics
import java.awt.image.BufferedImage

class Tilemap : Component() {

    val palette: MutableMap<String, BufferedImage> = HashMap()
    val tiles: Array<Array<String>> = Array(20) { Array(20) { "" } }
    var cellSize = 1f

    override fun render(gfx: Graphics) {
        val cellPixelSize = cellSize * GameObject.findComponent<Camera>()!!.pixelsPerUnit

        for (cellX in tiles.indices) {
            for (cellY in tiles[0].indices) {
                val cellTex = this.palette[this.tiles[cellX][cellY]]

                val x = cellX * cellPixelSize - cellPixelSize / 2
                val y = cellY * cellPixelSize - cellPixelSize / 2

                gfx.drawImage(cellTex, x.toInt(), y.toInt(), null)
            }
        }
    }

}