package com.springmarker.blog.bean

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * @author Springmarker
 * @date 2018/7/7 14:14
 */
data class Word(
        @TableId(type = IdType.AUTO)
        var id: Int? = null,
        var title: String = "",
        var permission: Long = 0,
        var summary: String = "",
        var writer: String = "",
        @TableField("background_image")
        var backgroundImage: String = "",
        var date: LocalDateTime = LocalDateTime.now(),
        @TableField("type_id")
        var typeId: Int = 0,
        @TableField("allow_comment")
        var allowComment: Int = 0,
        var html: String = "",
        @TableField("nick_title")
        var nickTitle: String = "404"
)