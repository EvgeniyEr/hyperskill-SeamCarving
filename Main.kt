package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main() {
    println("Enter rectangle width:")
    val width = readLine()!!.toInt()

    println("Enter rectangle height:")
    val height = readLine()!!.toInt()

    println("Enter output image name:")
    val outputFileName = readLine()!!

//    val width = 20
//    val height = 10
//    val outputFileName = "out.png"

    val bImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val graphics2D = bImage.graphics
    graphics2D.color = Color.RED
    val maxWidth = width - 1
    val maxHeight = height - 1
    graphics2D.drawLine(0, 0, maxWidth, maxHeight)
    graphics2D.drawLine(maxWidth, 0, 0, maxHeight)

    ImageIO.write(bImage, "png", File(outputFileName));
    graphics2D.dispose()
}