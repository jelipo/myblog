package com.springmarker.blog.bean

import java.util.*

/**
 * @author Frank
 * @date 2018/7/11 21:46
 */
class Comment(
        var id: Int = 0,
        var ismaincomment: Int = 0,
        var observername: String,
        var toobservername: String,
        var date: Date,
        var formatDate: String,
        var vicecomment_maincomment_id: Int = 0,
        var value: String,
        var viceComment: MutableList<Comment>
)