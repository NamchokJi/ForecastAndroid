package com.namchok.forecast.domain.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun Int.getDateTime(): String {
    try {
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        return simpleDateFormat.format(this * 1000L)
    } catch (e: Exception) {
        return "n/a"
    }
}
