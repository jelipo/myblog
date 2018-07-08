package blog.dao

import blog.bean.BlogMainPojo
import blog.bean.CommentPojo
import blog.bean.MessagePojo
import blog.bean.Word
import org.apache.ibatis.annotations.Mapper
import java.util.*

/**
 * @author Frank
 * @date 2018/7/7 15:50
 */
@Mapper
interface BlogMainDao {

    fun getWord(map: Map<*, *>): List<BlogMainPojo>

    fun toWord(id: Int): List<Word>

    fun getComments(id: Int): List<CommentPojo>

    fun putReply(parm: Map<*, *>): Int

    fun getWordText(wordTextId: String): Map<*, *>

    fun getMessages(): List<MessagePojo>

    fun putMessage(nickname: String, date: Date, content: String, contactway: String): Int

}