package com.mahesaiqbal.libraries.component.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

actual object DateFormatHelper {
    actual fun getFormattedDate(date: String): String {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMMM yyyy")
        val dateFormatted = inputFormat.parse(date)
        return dateFormatted?.let { outputFormat.format(it) }.orEmpty()
    }
}
