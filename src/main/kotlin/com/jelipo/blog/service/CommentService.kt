package com.jelipo.blog.service

import com.jelipo.blog.pojo.Comment
import com.jelipo.blog.pojo.Reply
import com.jelipo.blog.repository.CommentRepository
import com.jelipo.blog.util.PackingResults
import org.apache.commons.lang3.time.DateFormatUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Jelipo
 * @date 2018/10/6 18:41
 */
@Service
class CommentService {

    @Autowired
    private lateinit var commentRepository: CommentRepository

    fun getComments(wordId: String): Map<*, *> {
        val list = commentRepository.findAllByWordId(wordId.toInt())

        val json = HashMap<String, Comment>()
        for (comment in list) {
            comment.formatDate = DateFormatUtils.format(Date(), "MM月dd,yyyy HH:mm:ss");
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
            observerName = reply.nickname
            creatDate = LocalDateTime.now()
            value = reply.value
            email = reply.email
        }

        val savedComment = commentRepository.save(comment)
        return if (savedComment.id != null) {
            PackingResults.toSuccessMap(HashMap<String, String>(0))
        } else {
            PackingResults.toWorngMap("服务器出现错误！")
        }
    }

}