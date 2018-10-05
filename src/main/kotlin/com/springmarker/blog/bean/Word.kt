package com.springmarker.blog.bean

import java.time.LocalDateTime

/**
 * @author Frank
 * @date 2018/7/7 14:14
 */
data class Word(
        var id: Long = -1,
        var title: String = "",
        var permission: Long = 0,
        var summary: String = "",
        var writer: String = "",
        var backgroundImage: String = "",
        var date: LocalDateTime = LocalDateTime.now(),
        var typeId: Int = 0,
        var allowComment: Int = 0,
        var html: String = "",
        var nickTitle: String = "404"
)