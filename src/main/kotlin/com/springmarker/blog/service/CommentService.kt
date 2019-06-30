package com.springmarker.blog.service

import com.springmarker.blog.pojo.Comment
import com.springmarker.blog.pojo.Reply
import com.springmarker.blog.mapper.CommentMapper
import com.springmarker.blog.util.PackingResults
import org.apache.commons.lang3.StringEscapeUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Springmarker
 * @date 2018/10/6 18:41
 */
@Service
class CommentService {

    @Autowired
    private lateinit var commentMapper: CommentMapper

    private val simpleDateFormat = SimpleDateFormat("MM月dd,yyyy HH:mm:ss")

    fun getComments(wordId: String): Map<*, *> {
        val list = commentMapper.selectByMap(mapOf("word_id" to wordId.toInt()))
        //val list = commentMapper.getCommentsByWordId(wordId)

        val json = HashMap<String, Comment>()
        for (comment in list) {
            comment.formatDate = simpleDateFormat.format(comment.date)
            if (comment.mainComment == 1) {
                comment.viceComment = ArrayList()
                json[comment.id.toString()] = comment
            } else {
                val viceMainId = comment.viceCommentMainCommentId
                val viceComment = json[viceMainId.toString()] ?: continue
                viceComment.viceComment.add(comment)
            }
        }

        return PackingResults.toSuccessMap(json)
    }

    fun putReply(request: HttpServletRequest, reply: Reply): Map<*, *> {
        val comment = Comment()
        if (reply.value == "" || reply.isNewMainComment == "") {
            return PackingResults.toWorngMap("服务器出现错误！")
        }
        val timeNow = System.currentTimeMillis()
        if (reply.nickname == "") {
            reply.nickname = "游客" + timeNow % 10000000
        }
        if (reply.isNewMainComment != "1") {
            if (reply.mainCommentId == "") {
                return PackingResults.toWorngMap("服务器出现错误！")
            }
            comment.apply {
                toObserverName = if (reply.toObservername == "") null else reply.toObservername
                viceCommentMainCommentId = reply.mainCommentId
            }
        }
        comment.apply {
            wordId = reply.wordId
            mainComment = if (reply.isNewMainComment == "1") 1 else 2
            observerName = StringEscapeUtils.escapeHtml4(reply.nickname)
            date = Date()
            value = StringEscapeUtils.escapeHtml4(reply.value).replace("\n", "<br>")
            email = StringEscapeUtils.escapeHtml4(reply.email)
        }

        val result = commentMapper.insert(comment)
        return if (result > 0) {
//            commentLimit.insertIp(request)
            PackingResults.toSuccessMap(HashMap<String, String>(0))
        } else {
            PackingResults.toWorngMap("服务器出现错误！")
        }
    }

}