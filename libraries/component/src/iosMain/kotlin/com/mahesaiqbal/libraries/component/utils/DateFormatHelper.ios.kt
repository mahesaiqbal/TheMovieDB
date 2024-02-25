package com.mahesaiqbal.libraries.component.utils

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.localTimeZone
import platform.Foundation.timeZoneWithAbbreviation

actual object DateFormatHelper {
    actual fun getFormattedDate(date: String): String {
        val df = NSDateFormatter()
        val inputFormat = "yyyy-MM-dd"
        val outputFormat = "dd MMM yyyy"

        df.dateFormat = inputFormat
        NSTimeZone.timeZoneWithAbbreviation("GMT")?.let { df.timeZone = it }

        val parseDate = df.dateFromString(date)
        df.timeZone = NSTimeZone.localTimeZone
        df.dateFormat = outputFormat

        return parseDate?.let { df.stringFromDate(it) }.orEmpty()
    }
}
