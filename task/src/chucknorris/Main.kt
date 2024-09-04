package chucknorris

import java.util.Scanner

class ChuckNorrisEncoder {
    fun processDecryptedString(decryptedSTr: String): String {
        return decryptedSTr.toCharArray().joinToString(" ")
    }
}

fun main() {
    // initialise input reader
    val reader = Scanner(System.`in`)

    // initialise encoder class
    val encoder = ChuckNorrisEncoder()

    // request and print the line introduced and "encrypted"
    println("Input string:")
    println(encoder.processDecryptedString(reader.nextLine()))
}