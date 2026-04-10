package com.machinecode.kmp_github.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number


fun formatDate(inputDate: String, inputFormat: String, outputFormat: String): String {
    return try {

        val localDateTime = LocalDateTime.parse(inputDate)
        val year = localDateTime.year.toString().padStart(4, '0')
        val month = localDateTime.month.number.toString().padStart(2, '0')
        val day = localDateTime.day.toString().padStart(2, '0')
        val hour = localDateTime.hour.toString().padStart(2, '0')
        val minute = localDateTime.minute.toString().padStart(2, '0')

        "$month-$day-${year.takeLast(2)} $hour:$minute"

    } catch (e: Exception) {
        inputDate
    }
}