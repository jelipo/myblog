package com.springmarker.blog.mapper

import com.springmarker.blog.bean.Message
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * @author Springmarker
 * @date 2018/10/8 20:18
 */
@Repository
@Mapper
interface MessageMapper {

    fun getMessages(): List<Message>

    fun saveMessage(message: Message): Int

}