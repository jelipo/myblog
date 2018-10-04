package com.springmarker.blog.dao

import com.springmarker.blog.bean.BlogMain
import com.springmarker.blog.bean.Comment
import com.springmarker.blog.bean.Message
import com.springmarker.blog.bean.Word
import org.apache.ibatis.annotations.Mapper
import java.util.*

/**
 * @author Frank
 * @date 2018/7/7 15:50
 */
@Mapper
interface BlogMainDao {

    fun getWord(map: Map<*, *>): List<BlogMain>

    fun toWord(id: Int): List<Word>

    fun getComments(id: Int): List<Comment>

    fun putReply(parm: Map<*, *>): Int

    fun getWordText(wordTextId: String): Map<*, *>

    fun getMessages(): List<Message>

    fun putMessage(nickname: String, date: Date, content: String, contactway: String): Int

}