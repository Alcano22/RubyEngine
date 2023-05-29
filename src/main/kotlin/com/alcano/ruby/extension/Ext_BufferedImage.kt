package com.alcano.ruby.extension

import java.awt.Color
import java.awt.image.BandCombineOp
import java.awt.image.BufferedImage

fun BufferedImage.tint(color: Color): BufferedImage {
    val tintedTex = this.tintBW()

    val colorMatrix = arrayOf(
        floatArrayOf(color.red / 255f, 0f, 0f, 0f),
        floatArrayOf(color.green / 255f, 0f, 0f, 0f),
        floatArrayOf(color.blue / 255f, 0f, 0f, 0f),
        floatArrayOf(0f, 0f, 0f, 1f)
    )
    val changeColorsOp = BandCombineOp(colorMatrix, null)
    val raster = tintedTex.raster
    val writableRaster = raster.createCompatibleWritableRaster()
    changeColorsOp.filter(raster, writableRaster)

    return BufferedImage(tintedTex.colorModel, writableRaster, true, null)
}

fun BufferedImage.tintBW(): BufferedImage {
    val tintedTex = BufferedImage(this.width, this.height, BufferedImage.TRANSLUCENT)
    val gfx = tintedTex.createGraphics()

    gfx.drawImage(this, 0, 0, null)
    gfx.dispose()

    for (x in 0 until tintedTex.width) {
        for (y in 0 until tintedTex.height) {
            val pixel = Color.decode(tintedTex.getRGB(x, y).toString())
            val bwValue = (pixel.red + pixel.green + pixel.blue) / 3

            tintedTex.setRGB(x, y, Color(bwValue, bwValue, bwValue).rgb)
        }
    }

    return tintedTex
}