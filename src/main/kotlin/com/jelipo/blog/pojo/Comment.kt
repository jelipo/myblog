package com.jelipo.blog.pojo

import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import kotlin.collections.ArrayList

/**
 * @author Jelipo
 * @date 2018/7/11 21:46
 */
@Entity
data class Comment(

        @JsonView(Public::class)
        @Id
        var id: Int? = null,

        var wordId: Int? = -1,


        var mainComment: Int = 0,

        @JsonView(Public::class)
        var observerName: String? = null,

        @JsonView(Public::class)
        var toObserverName: String? = null,

        var date: Date = Date(),

        @JsonView(Public::class)
        var viceCommentMainCommentId: String? = "",

        @JsonView(Public::class)
        var value: String? = null,

        var email: String? = null,
        /** table base field */


        /** added fireld */
        @JsonView(Public::class)
        @Transient
        var viceComment: MutableList<Comment> = ArrayList(0),

        @JsonView(Public::class)
        @Transient
        var formatDate: String = ""
        /** added fireld */
) {
    interface Public
}