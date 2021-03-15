package seamcarving

import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


fun main(args: Array<String>) {
    val inputFileName = args[args.indexOf("-in") + 1]
    val outputFileName = args[args.indexOf("-out") + 1]

    try {
        val inputFile = File(inputFileName)
        val oldBuffImage = ImageIO.read(inputFile)
        val newBuffImage = Energies(oldBuffImage).calculateEnergies().normalizeEnergies()

        val outputFile = File(outputFileName)
        ImageIO.write(newBuffImage, "png", outputFile)
    } catch (e: IOException) {
        println("File not found or could not be saved")
    }
}