package chucknorris

import java.util.Scanner

class ChuckNorrisEncoder {
    fun processDecryptedString(decryptedSTr: String): Unit {
        println("The result:")

        for (chr in decryptedSTr.toCharArray()) {
            println("$chr = ${Integer.toBinaryString(chr.code).padStart(7, '0')}")
        }
    }
}

fun main() {
    // initialise input reader
    val reader = Scanner(System.`in`)

    // initialise encoder class
    val encoder = ChuckNorrisEncoder()

    // request and print the line introduced and "encrypted"
    println("Input string:")
    encoder.processDecryptedString(reader.nextLine())
}