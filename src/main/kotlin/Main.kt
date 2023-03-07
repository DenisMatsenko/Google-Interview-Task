import com.github.mm.coloredconsole.colored

fun main() {
    while (true) {
        //Thanks Marcel Matula for such a handy tool!
        colored {
            print("Enter the code: ".bold)
            val code: String = readLine().toString()

            println("Decoding: ${code.green.bold} -> ${Decode(code, 1).yellow.bold}")
        }
    }
}

//Recursive method that calls itself if it finds a number in the string
fun Decode(code: String, factor: Int): String {
    var decodedStr: String = ""

    var i = 0
    while (i < code.length) {
        var char: Char = code[i]

        //Adding letters
        if (char.isLetter())
            decodedStr += char

        //Run this method if it finds a number
        if (code[i].isDigit()) {
            var start = i + 1
            var end = FindEndIndex(code, start)

            decodedStr += Decode(code.substring(start, end + 1), char.digitToInt())
            i = end //Skips brackets
        }
        i++
    }

    return Multiplication(decodedStr, factor)
}

//Multiplying the decoded string in brackets by its multiplier
fun Multiplication(decodedStr: String, factor: Int): String {
    var result: String = ""
    var i: Int = 0
    while (i < factor) {
        result += decodedStr
        i++
    }

    return result
}

//Method returns the index of the closing brace
fun FindEndIndex(code: String, start: Int): Int {
    var skip: Int = 1
    var i: Int = start
    while (i < code.length) {
        val char: Char = code[i]

        if(char.isDigit())
            skip++
        else if(char == ']')
            skip--

        if(skip == 0)
            return i

        i++
    }
    return 0
}