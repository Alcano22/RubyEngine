package com.alcano.ruby.gameobject.component

import com.alcano.ruby.math.Vector2f
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class LineRenderer : Component() {

    var points: MutableList<Vector2f> = ArrayList()
    var lineWidth: Float = 5f
    var color: Color = Color.WHITE
    var isClosed: Boolean = false

    override fun render(gfx: Graphics) {
        val gfx2D = gfx as Graphics2D

        gfx2D.stroke = BasicStroke(this.lineWidth)
        gfx2D.color = this.color

        for (i in this.points.indices) {
            if (i == this.points.lastIndex) {
                if (!this.isClosed) break

                gfx2D.drawLine(
                    (this.transform.position + this.points[i]).x.toInt(),
                    (this.transform.position + this.points[i]).y.toInt(),
                    (this.transform.position + this.points[0]).x.toInt(),
                    (this.transform.position + this.points[0]).y.toInt()
                )
                break
            }

            gfx2D.drawLine(
                (this.transform.position + this.points[i]).x.toInt(),
                (this.transform.position + this.points[i]).y.toInt(),
                (this.transform.position + this.points[i + 1]).x.toInt(),
                (this.transform.position + this.points[i + 1]).y.toInt()
            )
        }
    }

}