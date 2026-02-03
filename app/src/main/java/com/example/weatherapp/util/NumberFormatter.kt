package com.example.weatherapp.util

import java.text.NumberFormat
import java.util.Locale

fun formatNumber(number: Int): String {
    val locale = Locale.getDefault()
    val formatted = NumberFormat.getIntegerInstance(locale).format(number)
    
    return if (locale.language == "ar") {
        convertToArabicIndic(formatted)
    } else {
        formatted
    }
}

fun formatNumber(number: Double): String {
    val locale = Locale.getDefault()
    val numberFormat = NumberFormat.getNumberInstance(locale)
    numberFormat.maximumFractionDigits = 0
    val formatted = numberFormat.format(number)
    
    return if (locale.language == "ar") {
        convertToArabicIndic(formatted)
    } else {
        formatted
    }
}

fun convertToArabicIndic(text: String): String {
    return text.map { char ->
        when (char) {
            '0' -> '٠'
            '1' -> '١'
            '2' -> '٢'
            '3' -> '٣'
            '4' -> '٤'
            '5' -> '٥'
            '6' -> '٦'
            '7' -> '٧'
            '8' -> '٨'
            '9' -> '٩'
            else -> char
        }
    }.joinToString("")
}

