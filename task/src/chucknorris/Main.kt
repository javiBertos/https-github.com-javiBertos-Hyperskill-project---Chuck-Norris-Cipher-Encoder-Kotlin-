package chucknorris

import java.util.Scanner

class ChuckNorrisEncoder {
    private fun encryptToChuckNorrisFromBin(binStr: String): String {
        var encryptedStr = ""
        var lastChar: Char = Char.MIN_VALUE
        var glue = ""

        for (c in binStr) {
            if (c == lastChar) {
                encryptedStr += '0'
                continue
            }

            encryptedStr += "$glue${if (c == '0') "00" else "0"} 0"
            lastChar = c
            glue = " "
        }

        return encryptedStr
    }

    fun processDecryptedString(decryptedSTr: String): String {
        var binaryString = ""
        println("The result:")

        for (chr in decryptedSTr.toCharArray()) {
            binaryString += Integer.toBinaryString(chr.code).padStart(7, '0')
        }

        return this.encryptToChuckNorrisFromBin(binaryString)
    }

    private fun decryptFromChuckNorrisToBin(encryptedStr: String): String {
        var binStr = ""
        val blocks = encryptedStr.split(" ")

        for (i in 0..blocks.lastIndex step 2) {
            binStr += blocks[i + 1].replace("0", if (blocks[i] == "0") "1" else "0")
        }

        return binStr
    }

    fun processEncryptedString(encryptedSTr: String): String {
        println("The result:")

        return this.decryptFromChuckNorrisToBin(encryptedSTr)
            .chunked(7){binCode -> Integer.parseInt(binCode.toString(), 2).toChar()}
                .joinToString("")
    }
}

fun main() {
    // initialise input reader
    val reader = Scanner(System.`in`)

    // initialise encoder class
    val encoder = ChuckNorrisEncoder()

    // request an string and print the encrypted version
    //println("Input string:")
    //println(encoder.processDecryptedString(reader.nextLine()))

    // request a code and print the decrypted string
    println("Input encoded string:")
    println(encoder.processEncryptedString(reader.nextLine().trim()))
}