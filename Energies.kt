package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

class Energies(private val source: BufferedImage) {

    private val height = source.height
    private val width = source.width
    private var maxEnergy = -1.0
    private val energyMatrix = Array(height) { DoubleArray(width) }

    // The new blank image is the same size as the old one
    private val newBuffImage = BufferedImage(source.width, source.height, source.type)

    fun calculateEnergies(): Energies {
        // Calculating for each pixel
        for (x in 0 until width) {
            for (y in 0 until height) {
                energyMatrix[y][x] = calculateEnergy(x, y)
            }
        }
        return this
    }

    fun normalizeEnergies(): BufferedImage {
        var intensity: Int
        var newColor: Color

        for (x in 0 until width) {
            for (y in 0 until height) {
                intensity = (255.0 * energyMatrix[y][x] / maxEnergy).toInt()
                newColor = Color(intensity, intensity, intensity)
                newBuffImage.setRGB(x, y, newColor.rgb)
            }
        }
        return newBuffImage
    }

    private fun calculateEnergy(x: Int, y: Int): Double {
        val posX = when (x) {
            0 -> 1
            this.width - 1 -> this.width - 2
            else -> x
        }

        val posY = when (y) {
            0 -> 1
            this.height - 1 -> this.height - 2
            else -> y
        }

        // Colors of pixels surrounding the current
        val colorLeftPixel = Color(source.getRGB(posX - 1, y))
        val colorRightPixel = Color(source.getRGB(posX + 1, y))
        val colorTopPixel = Color(source.getRGB(x, posY - 1))
        val colorDownPixel = Color(source.getRGB(x, posY + 1))

        val deltaX = (colorLeftPixel.red - colorRightPixel.red).toDouble().pow(2) +
                (colorLeftPixel.green - colorRightPixel.green).toDouble().pow(2) +
                (colorLeftPixel.blue - colorRightPixel.blue).toDouble().pow(2)

        val deltaY = (colorTopPixel.red - colorDownPixel.red).toDouble().pow(2) +
                (colorTopPixel.green - colorDownPixel.green).toDouble().pow(2) +
                (colorTopPixel.blue - colorDownPixel.blue).toDouble().pow(2)

        val pixelEnergy = sqrt(deltaX + deltaY)
        maxEnergy = max(maxEnergy, pixelEnergy)

        return pixelEnergy
    }
}