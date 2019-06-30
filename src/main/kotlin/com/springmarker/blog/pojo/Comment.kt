package com.springmarker.blog.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Springmarker
 * @date 2018/7/11 21:46
 */
class Comment(
        /** table base field */
        @JsonView(Public::class)
        @TableId(type = IdType.AUTO)
        var id: Int? = null,

        @TableField("word_id")
        var wordId: Int? = -1,

        @TableField("main_comment")
        var mainComment: Int = 0,

        @JsonView(Public::class)
        @TableField("observer_name")
        var observerName: String? = null,

        @JsonView(Public::class)
        @TableField("to_observer_name")
        var toObserverName: String? = null,

        var date: Date = Date(),

        @JsonView(Public::class)
        @TableField("vice_comment_main_comment_id")
        var viceCommentMainCommentId: String? = "",

        @JsonView(Public::class)
        var value: String? = null,

        var email: String? = null,
        /** table base field */


        /** added fireld */
        @JsonView(Public::class)
        @TableField(exist = false)
        var viceComment: MutableList<Comment> = ArrayList(0),

        @JsonView(Public::class)
        @TableField(exist = false)
        var formatDate: String = ""
        /** added fireld */
) {
    interface Public
}