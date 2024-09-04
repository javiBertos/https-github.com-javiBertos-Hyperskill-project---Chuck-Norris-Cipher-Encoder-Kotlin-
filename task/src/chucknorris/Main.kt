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

        for (chr in decryptedSTr.toCharArray()) {
            binaryString += Integer.toBinaryString(chr.code).padStart(7, '0')
        }

        return this.encryptToChuckNorrisFromBin(binaryString)
    }

    private fun decryptFromChuckNorrisToBin(encryptedStr: String): String {
        var binStr = ""
        val blocks = encryptedStr.split(" ")

        if (blocks.size % 2 != 0) {
            throw Exception("Encoded string is not valid. The number of blocks is odd")
        }

        for (i in 0..blocks.lastIndex step 2) {
            if (blocks[i] != "0" && blocks[i] != "00") {
                throw Exception("Encoded string is not valid. The first block of each sequence is not 0 or 00")
            }

            binStr += blocks[i + 1].replace("0", if (blocks[i] == "0") "1" else "0")
        }

        return binStr
    }

    fun processEncryptedString(encryptedSTr: String): String {
        if (encryptedSTr.replace("0", " ").trim() != "") {
            throw Exception("Encoded string is not valid. The encoded message includes characters other than 0 or spaces")
        }

        val binaryString = this.decryptFromChuckNorrisToBin(encryptedSTr)

        if (binaryString.length % 7 != 0) {
            throw Exception("Encoded string is not valid. The length of the decoded binary string is not a multiple of 7")
        }

        return binaryString.chunked(7){binCode -> Integer.parseInt(binCode.toString(), 2).toChar()}
                .joinToString("")
    }
}

fun main() {
    // initialise input reader3
    val reader = Scanner(System.`in`)

    // initialise encoder class
    val encoder = ChuckNorrisEncoder()

    // option introduced by the user
    var option: String = ""

    // request the option and manage what to do
    do {
        println("Please input operation (encode/decode/exit):")
        option = reader.nextLine()

        try {
            when (option) {
                "encode" -> {
                    // request an string and print the encrypted version
                    println("Input string:")

                    val encodedString = encoder.processDecryptedString(reader.nextLine())

                    println("Encoded string:\n$encodedString")
                }

                "decode" -> {
                    // request a code and print the decrypted string
                    println("Input encoded string:")

                    val decodedString = encoder.processEncryptedString(reader.nextLine())

                    println("Decoded string:\n$decodedString")
                }

                else -> println("There is no '$option' operation")
            }
        } catch (e: Exception) {
            println(e.message)
        }

        println()

    } while (option != "exit")

    println("Bye!")
}