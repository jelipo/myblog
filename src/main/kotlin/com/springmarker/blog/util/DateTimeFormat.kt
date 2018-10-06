package com.mayabot.overwatchrobot.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Springmarker
 * @date 2018/8/3 11:01
 */
object DateTimeFormat {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun defaultFormat(date: Date): String {
        return simpleDateFormat.format(date)
    }

    fun defaultFormat(time: Long): String {
        return simpleDateFormat.format(time)
    }

}