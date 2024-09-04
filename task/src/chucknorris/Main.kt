package chucknorris

import java.util.Scanner

class ChuckNorrisEncoder {
    private fun encryptToChuckNorris(binStr: String): String {
        var encodedStr = ""
        var lastChar: Char = Char.MIN_VALUE
        var glue = ""

        for (c in binStr) {
            if (c == lastChar) {
                encodedStr += '0'
                continue
            }

            encodedStr += "$glue${if (c == '0') "00" else "0"} 0"
            lastChar = c
            glue = " "
        }

        return encodedStr
    }

    fun processDecryptedString(decryptedSTr: String): String {
        var binaryString = ""
        println("The result:")

        for (chr in decryptedSTr.toCharArray()) {
            binaryString += Integer.toBinaryString(chr.code).padStart(7, '0')
        }

        return this.encryptToChuckNorris(binaryString)
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