package com.springmarker.blog.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.fasterxml.jackson.annotation.JsonView
import java.time.LocalDateTime

/**
 * @author Springmarker
 * @date 2018/7/11 21:51
 */
class Message(

        @TableId(type = IdType.AUTO)
        var id: Int = -1,

        @JsonView(Public::class)
        @TableField("nick_name")
        var nickName: String = "",

        @JsonView(Public::class)
        var date: LocalDateTime = LocalDateTime.now(),

        @JsonView(Public::class)
        var content: String? = null,

        var contactway: String? = null

) {
    interface Public
}