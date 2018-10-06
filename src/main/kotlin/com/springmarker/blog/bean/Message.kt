package com.springmarker.blog.bean

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Springmarker
 * @date 2018/7/11 21:51
 */
class Message(
        var nick_name: String,
        var date: Date,
        var content: String? = null,
        var messageTime: String? = null,
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("MM月dd,yyyy HH:mm:ss")
)