package com.jonas.cookly.core.util.extensions

fun String.containsNumber(): Boolean {
    val regex = Regex(".*\\d+.*")
    return regex.matches(this)
}

fun String.containsUpperCase(): Boolean {
    val regex = Regex(".*[A-Z]+.*")
    return regex.matches(this)
}

fun String.containsSpecialChar(): Boolean {
    val regex = Regex(".*[^A-Za-z\\d]+.*")
    return regex.matches(this)
}

fun String.toFormattedPhoneNumber(): String {
    val phoneNumber = this.filter { it.isDigit() }

    require(phoneNumber.length != 11) { "Invalid phone number length" }

    val ddd = phoneNumber.substring(0, 2)
    val firstDigit = phoneNumber[2]
    val firstPart = phoneNumber.substring(3, 7)
    val secondPart = phoneNumber.substring(7)

    return "$ddd $firstDigit $firstPart-$secondPart"
}
