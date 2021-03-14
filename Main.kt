package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


fun main(args: Array<String>) {

    val inputFileName = args[args.indexOf("-in") + 1]
    val outputFileName = args[args.indexOf("-out") + 1]

    try {
        val file = File(inputFileName)
        val source = ImageIO.read(file)

        val width = source.width
        val height = source.height

        // Создаем новое пустое изображение, такого же размера
        val result = BufferedImage(width, height, source.type)

        // Делаем двойной цикл, чтобы обработать каждый пиксель
        for (x in 0 until width) {
            for (y in 0 until height) {

                // Получаем цвет текущего пикселя
                val color = Color(source.getRGB(x, y))

                // Получаем каналы этого цвета
                val blue = color.blue
                val red = color.red
                val green = color.green

                //  Cоздаем новый цвет
                val newColor = Color(255 - red, 255 - green, 255 - blue)

                // Устанавливаем этот цвет в текущий пиксель результирующего изображения
                result.setRGB(x, y, newColor.rgb)
            }
        }

        // Созраняем результат в новый файл
        val output = File(outputFileName)
        ImageIO.write(result, "png", output)
    } catch (e: IOException) {
        println("Файл не найден или не удалось сохранить")
    }
}