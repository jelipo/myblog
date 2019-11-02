package com.jelipo.blog.pojo

import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

/**
 * @author Jelipo
 * @date 2018/7/11 21:51
 */
@Entity
data class Message(

        @Id
        var id: Int? = null,

        @JsonView(Public::class)
        var nickName: String = "",

        @JsonView(Public::class)
        var creatDate: Date = Date(),

        @JsonView(Public::class)
        var content: String? = null,

        var contactway: String? = null

) {
    interface Public
}