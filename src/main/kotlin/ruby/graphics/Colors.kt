package ruby.graphics

import java.awt.Color
import kotlin.random.Random

object Colors {

    val BLACK: Color = this["#000000"]
    val WHITE: Color = this["#ffffff"]
    val GRAY: Color = this["#595959"]
    val DARK_GRAY: Color = this["#2e2e2e"]
    val LIGHT_GRAY: Color = this["#828282"]
    val RED: Color = this["#fc0303"]
    val DARK_RED: Color = this["#5e0000"]
    val BLUE: Color = this["#001aff"]
    val DARK_BLUE: Color = this["#000c75"]
    val LIGHT_BLUE: Color = this["#4995e6"]
    val MIDNIGHT_BLUE: Color = this["#071030"]
    val CYAN: Color = this["#46eaf2"]
    val GREEN: Color = this["#228705"]
    val DARK_GREEN: Color = this["#134704"]
    val LIGHT_GREEN: Color = this["#6cd44e"]
    val YELLOW: Color = this["#ebe12f"]
    val ORANGE: Color = this["#de8512"]
    val PURPLE: Color = this["#580ea1"]
    val MAGENTA: Color = this["#78189e"]
    val PINK: Color = this["#d841e0"]
    val BROWN: Color = this["#301d07"]
    val BEIGE: Color = this["#b5a184"]

    operator fun get(hexColor: String): Color = Color.decode(hexColor)

    fun random(): Color {
        val r = Random.nextInt(0, 255)
        val g = Random.nextInt(0, 255)
        val b = Random.nextInt(0, 255)

        return Color(r, g, b)
    }

}