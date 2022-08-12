package com.jelipo.blog.pojo

import com.fasterxml.jackson.annotation.JsonView
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var wordId: Int? = -1,


    var mainComment: Int = 0,

    @JsonView(Public::class)
    var observerName: String? = null,

    @JsonView(Public::class)
    var toObserverName: String? = null,

    var creatDate: LocalDateTime = LocalDateTime.now(),

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