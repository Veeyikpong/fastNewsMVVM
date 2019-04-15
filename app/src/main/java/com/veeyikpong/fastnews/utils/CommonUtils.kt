package com.veeyikpong.fastnews.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CommonUtils{
    companion object {
        val genders = arrayOf(
            "Male",
            "Female"
        )

        fun getFormattedDate(outputDateFormat: String, date: Date): String? {
            try {
                val formatOutput = SimpleDateFormat(outputDateFormat)
                return formatOutput.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        }
    }
}