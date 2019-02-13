package com.jonas.binarycalculator

/**
 *
 * This class is a binary String calculator
 *
 * @constructor Creates a binary calculator
 */
class Calculator {


    /**
     * This function adds to Strings representing binary strings
     * and returns a single string being the sum of the binary strings
     *
     * @param String binary charsequense
     * @param String binary charsequense
     * @return a sum in a string format
     */
    fun add(binary1: String, binary2: String): String { //refactoring
        var longestBinary: String
        var shortestBinary: String
        if (binary1.length >= binary2.length) {
            longestBinary = binary1.reversed()
            shortestBinary = binary2.reversed()
        } else {
            longestBinary = binary2.reversed()
            shortestBinary = binary1.reversed()
        }
        val sb = StringBuilder()
        var borrowing = false
        var binaryNum1: Char
        var binaryNum2: Char
        for (i in 1..longestBinary.length) {
            binaryNum1 = longestBinary[i - 1]
            if (i <= shortestBinary.length) {
                binaryNum2 = shortestBinary[i - 1]
                if (binaryNum2 != (binaryNum1)) {
                    if (borrowing) {
                        sb.append('0')
                        borrowing = true
                    } else {
                        sb.append('1')
                        borrowing = false
                    }
                } else if (binaryNum1 == '0') {
                    if (borrowing) {
                        sb.append('1')
                    } else {
                        sb.append('0')
                    }
                    borrowing = false
                } else {
                    if (borrowing) {
                        sb.append('1')
                    } else {
                        sb.append('0')
                    }
                    borrowing = true
                }
            } else if (i <= longestBinary.length && i > shortestBinary.length) {
                if (binaryNum1 == '1') {
                    if (borrowing) {
                        sb.append('0')
                        borrowing = true
                    } else {
                        sb.append('1')
                        borrowing = false
                    }
                } else if (binaryNum1 == '0') {
                    if (borrowing) {
                        sb.append('1')
                    } else {
                        sb.append('0')
                    }
                    borrowing = false
                }
            }
        }
        if (borrowing) {
            sb.append('1')
            borrowing = false
        }
        return sb.toString().reversed();
    }


    /**
     * This function takes in two strings representing binary values
     * subtracting one from the other and returns the sum as a string
     *
     * @param String representing a binary value
     * @param String representing a binary value
     * @return a sum in a string format
     */
    fun subtract(minuendString: String, subtrahendString: String): String {

        var minuend = binaryStringToArray(minuendString)
        var subtrahend = binaryStringToArray(subtrahendString)

        if (subtrahend[subtrahend.size - 1] == 1 && minuend[minuend.size - 1] == 0 ||
            subtrahend.size > minuend.size
        ) {
            return "subtrahend > minuend won't work"
        }

        var sum = IntArray(minuend.size)

        var beginSearchIndex: Int = 0
        var endSearchIndex: Int = 0
        var searchSpan: Int = 0

        var i: Int = 0;
        while (i <= subtrahend.size-1) { //iterating through the subtrahend
            if (minuend[i] == subtrahend[i]) {
                sum[i] = 0
                i++
            } else if (subtrahend[i] == 0 && minuend[i] == 1) {
                sum[i] = 1
                i++
            } else {
                beginSearchIndex = i
                for (j in i+1..minuend.size - 1) {
                    if (minuend[j] == 1) {
                        minuend[j] = 0
                        endSearchIndex = j - 1
                        searchSpan = endSearchIndex - beginSearchIndex + 1
                        break
                    }
                }
                var tempSubtrahend = IntArray(searchSpan)
                var tempMinuend = IntArray(searchSpan)
                for (k in i..endSearchIndex) {
                    tempMinuend[k-i] = minuend[k]
                    if (subtrahend.getOrNull(k) == null ||
                        subtrahend.getOrNull(k) == 0
                    ) {
                        tempSubtrahend[k-i] = 0
                    } else {
                        tempSubtrahend[k-i] = 1
                    }
                }
                sum[i] = 1
                for(v in 1..tempSubtrahend.size-1){
                    if(tempSubtrahend[v] == 1){
                        sum[v] = 0
                    } else {
                        sum[v] = 1
                    }
                }
                i += searchSpan
            }
        }
        for(l in subtrahend.size..minuend.size-1){
            sum[l] = minuend[l]
        }
        fun Char.isBinary(): Boolean {
            return this == '0' || this == '1'
        }
        return sum.contentToString().filter {c: Char -> c.isBinary() }
    }


    /**
     * This function takes in two strings representing binary values
     * multiplying them and returns the sum in a string format
     *
     * @param String representing a binary value
     * @param String representing a binary value
     * @return a sum in a string format
     */
    fun multiply(binary1: String, binary2: String): String {

        var longestBinary: String
        var shortestBinary: String
        if (binary1.length >= binary2.length) {
            longestBinary = binary1.reversed()
            shortestBinary = binary2.reversed()
        } else {
            longestBinary = binary2.reversed()
            shortestBinary = binary1.reversed()
        }
        var binaryList: ArrayList<String> = ArrayList()
        for (i in 0..shortestBinary.length - 1) {
            if (shortestBinary[i] == ('0')) {
                continue
            }
            var currentString = ""
            for (j in 0..i - 1) {
                currentString += "0"
            }
            for (k in 0..longestBinary.length - 1) {
                if (longestBinary[k] == '1') {
                    currentString += "1"
                } else {
                    currentString += "0"
                }
            }
            binaryList.add(currentString.reversed())
        }
        var total = "0"
        binaryList.forEach { element: String ->
            total = add(element, total)
        }
        return total
    }

    fun divide(): String {
        throw UnsupportedOperationException()
    }


    /**
     * This function takes in a single string representing a binary value
     * and returns the binary in base10
     *
     * @param String representing a binary value
     * @return a base10 sum in a string format
     */
    fun calculateBase10(binary: String): String {
        var binaryReversed = binary.reversed()
        var indexWorth = 1
        var sum = 0
        for (i in 0..binaryReversed.length - 1) {
            if (binaryReversed[i] == '1') {
                sum += indexWorth
            }
            println("$i $sum")
            indexWorth *= 2
        }
        return sum.toString()
    }


    /**
     * this function takes a single string representing a binary value
     * and returns a IntArray of the binary.
     * The IntArray will be reversed to enforce correct index handling
     *
     * @Param String representing a binary value
     * @Return an IntArray representing a binary value
     */
    private fun binaryStringToArray(binary: String): IntArray {
        var binaryList: List<Int> = binary.reversed().map { c: Char ->
            c.toString().toInt()
        }
        return binaryList.toIntArray()
    }
}

fun main(args: Array<String>) {
    var calc = Calculator()
    println(calc.subtract("1111", "1000"))
}