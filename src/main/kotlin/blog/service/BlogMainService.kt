package blog.service

import blog.bean.CommentPojo
import blog.bean.Reply
import blog.bean.Word
import blog.dao.BlogMainDao
import org.apache.commons.lang3.StringEscapeUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import redisServer.service.CommentLimit
import util.PackingResult
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashMap

/**
 * @author Frank
 * @date 2018/7/11 22:35
 */
@Service
class BlogMainService {
    @Autowired
    private lateinit var blogMainDao: BlogMainDao

    @Autowired
    private lateinit var commentLimit: CommentLimit

    fun getBlogByPageNum(request: HttpServletRequest, pageNum: Int, getBlogNum: Int, type: String): Map<*, *> {
        if (pageNum == 0 || getBlogNum == 0) {
            return PackingResult.toWorngMap("参数错误")
        }
        val map = HashMap<String, Any>()
        map.put("getBlogNum", getBlogNum)
        map.put("startNum", (pageNum - 1) * getBlogNum)
        map.put("type", type)
        val list = blogMainDao!!.getWord(map)
        val simpleDateFormat = SimpleDateFormat("MM月dd,yyyy")
        for (i in list.indices) {
            val blogMainPojo = list[i]
            blogMainPojo.formatDate = simpleDateFormat.format(blogMainPojo.getDate())
        }
        return PackingResult.toSuccessMap(list)
    }

    fun toWord(request: HttpServletRequest, id: Int): Boolean? {
        val list = blogMainDao!!.toWord(id)
        val listSize = list.size
        val mainWord: Word
        if (listSize == 2) {
            if (list[0].id == id.toLong()) {
                mainWord = list[0]
                request.setAttribute("lastWordId", id)
                request.setAttribute("lastWordTitle", "没有上一篇了")
                request.setAttribute("nextWordId", list[1].id)
                request.setAttribute("nextWordTitle", list[1].title)

            } else {
                mainWord = list[1]
                request.setAttribute("lastWordId", list[0].id)
                request.setAttribute("lastWordTitle", list[0].title)
                request.setAttribute("nextWordId", id)
                request.setAttribute("nextWordTitle", "没有下一篇了")
            }
        } else if (listSize == 1) {
            return false
        } else {
            mainWord = list[1]
            request.setAttribute("lastWordId", list[0].id)
            request.setAttribute("lastWordTitle", list[0].title)
            request.setAttribute("nextWordId", list[2].id)
            request.setAttribute("nextWordTitle", list[2].title)
        }
        val textResult = blogMainDao.getWordText(mainWord.wordTextID)
        request.setAttribute("wordText", textResult["text"])
        request.setAttribute("wordId", id)
        request.setAttribute("title", mainWord.title)
        val simpleDateFormat = SimpleDateFormat("MM月dd,yyyy")
        request.setAttribute("date", simpleDateFormat.format(mainWord.date))
        request.setAttribute("backgroundImage", mainWord.backgroundImage)
        return true
    }


    fun getComments(id: Int): Map<*, *> {
        val list = blogMainDao!!.getComments(id)
        val simpleDateFormat = SimpleDateFormat("MM月dd,yyyy HH:mm:ss")
        val json = HashMap<String, Any>()
        for (i in list.indices) {
            val comment = list[i]
            comment.formatDate = simpleDateFormat.format(comment.date)
            if (comment.ismaincomment == 1) {
                comment.viceComment = ArrayList()
                json[comment.id.toString()] = comment
            } else {
                val vice_main_id = comment.vicecomment_maincomment_id
                val viceComment = json[vice_main_id.toString()] as CommentPojo
                viceComment.viceComment.add(comment)
            }
        }
        return PackingResult.toSuccessMap(json)
    }

    fun putReply(request: HttpServletRequest, reply: Reply): Map<*, *> {
        if (commentLimit.isBanIp(request)!!) {
            return PackingResult.toWorngMap("您暂时还不能操作！")
        }
        if (reply.wordId == "" || reply.value == "" || reply.isNewMainComment == "") {
            return PackingResult.toWorngMap("服务器出现错误！")
        }
        val timeNow = System.currentTimeMillis()
        if (reply.nickname == "") {
            reply.nickname = "游客" + timeNow % 10000000
        }
        val map = HashMap<String, Any?>()
        if (reply.isNewMainComment != "1") {
            if (reply.mainCommentId == "") {
                return PackingResult.toWorngMap("服务器出现错误！")
            }
            map["toObserverName"] = if (reply.toObservername == "") null else reply.toObservername
            map["viceComment_mainComment_id"] = reply.mainCommentId
        }
        map["wordId"] = reply.wordId
        map["isMainComment"] = if (reply.isNewMainComment == "1") 1 else 2
        map["observerName"] = StringEscapeUtils.escapeHtml4(reply.nickname)
        map["date"] = Date(System.currentTimeMillis())
        map["value"] = StringEscapeUtils.escapeHtml4(reply.value).replace("\n", "<br>")
        map["email"] = StringEscapeUtils.escapeHtml4(reply.email)
        val result = blogMainDao.putReply(map)
        return if (result > 0) {
            commentLimit.insertIp(request)
            PackingResult.toSuccessMap(HashMap<String, String>(0))
        } else {
            PackingResult.toWorngMap("服务器出现错误！")
        }
    }

    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val list = blogMainDao.getMessages()
        return PackingResult.toSuccessMap(list)
    }

    fun putMessage(request: HttpServletRequest, nickname: String, content: String, contactway: String): Map<*, *> {
        var nickname = nickname

        if (commentLimit.isBanIp(request)!!) {
            return PackingResult.toWorngMap("您暂时还不能操作！")
        }
        if (content == "") {
            return PackingResult.toWorngMap("回复失败，请检查您的内容！")
        }
        val nowTime = System.currentTimeMillis()
        if (nickname == "") {
            nickname = "游客" + nowTime % 10000000
        }
        val date = Date(nowTime)
        val result = blogMainDao.putMessage(StringEscapeUtils.escapeHtml4(nickname), date, StringEscapeUtils.escapeHtml4(content), StringEscapeUtils.escapeHtml4(contactway))
        return if (result > 0) {
            //加入暂时黑名单
            commentLimit.insertIp(request)
            //留言计数器+1
            val servletContext = request.servletContext
            val messageNUm = servletContext.getAttribute("messageNum") as Int
            servletContext.setAttribute("messageNum", messageNUm + 1)

            PackingResult.toSuccessMap()
        } else {
            PackingResult.toWorngMap("回复失败！")
        }
    }
}