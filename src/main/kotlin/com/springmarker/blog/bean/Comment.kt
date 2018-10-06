package com.springmarker.blog.bean

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Springmarker
 * @date 2018/7/11 21:46
 */
class Comment(
        /** table base field */
        @TableId(type = IdType.AUTO)
        var id: Int? = null,

        @TableField("word_id")
        var wordId: Int? = -1,

        @TableField("main_comment")
        var mainComment: Int = 0,

        @TableField("observer_name")
        var observerName: String? = null,

        @TableField("to_observer_name")
        var toObserverName: String? = null,

        var date: Date = Date(),

        @TableField("vice_comment_main_comment_id")
        var viceCommentMainCommentId: String? = "",

        var value: String? = null,

        var email: String? = null,
        /** table base field */


        /** added fireld */
        @TableField(exist = false)
        var viceComment: MutableList<Comment> = ArrayList(0),

        @TableField(exist = false)
        var formatDate: String = ""
        /** added fireld */
)