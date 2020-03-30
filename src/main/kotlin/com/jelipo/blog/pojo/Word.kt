package com.jelipo.blog.pojo

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Jelipo
 * @date 2018/7/7 14:14
 */
@Entity
data class Word(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        var title: String = "",

        var permission: Int = 0,

        var summary: String = "",

        var writer: String = "",

        var backgroundImage: String = "",

        var creatDate: LocalDateTime = LocalDateTime.now(),

        var typeId: Int = 0,

        var allowComment: Int = 0,

        var html: String = "",

        var nickTitle: String = "404"

)