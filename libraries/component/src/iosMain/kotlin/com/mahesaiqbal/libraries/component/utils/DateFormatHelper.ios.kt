package com.mahesaiqbal.libraries.component.utils

import platform.Foundation.NSDateFormatter

actual object DateFormatHelper {
    actual fun getFormattedDate(date: String): String {
        val dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd MMMM yyyy"
        val dateFormatted = dateFormatter.dateFromString(date)
        return dateFormatted?.let {
            dateFormatter.stringFromDate(it)
        }.orEmpty()
    }
}
