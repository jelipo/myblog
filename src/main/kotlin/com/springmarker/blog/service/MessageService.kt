package com.springmarker.blog.service

import com.springmarker.blog.dao.BlogMainDao
import com.springmarker.blog.util.PackingResults
import org.apache.commons.lang3.StringEscapeUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Frank
 * @date 2018/10/7 20:00
 */
@Service
class MessageService {

    @Autowired
    private lateinit var blogMainDao: BlogMainDao

    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val list = blogMainDao.getMessages()
        return PackingResults.toSuccessMap(list)
    }

    fun putMessage(request: HttpServletRequest, nickname: String, content: String, contactway: String): Map<*, *> {
        var nickname = nickname

//        if (commentLimit.isBanIp(request)!!) {
//            return PackingResults.toWorngMap("您暂时还不能操作！")
//        }
        if (content == "") {
            return PackingResults.toWorngMap("回复失败，请检查您的内容！")
        }
        val nowTime = System.currentTimeMillis()
        if (nickname == "") {
            nickname = "游客" + nowTime % 10000000
        }
        val date = Date(nowTime)
        val result = blogMainDao.putMessage(StringEscapeUtils.escapeHtml4(nickname), date, StringEscapeUtils.escapeHtml4(content), StringEscapeUtils.escapeHtml4(contactway))
        return if (result > 0) {
            //加入暂时黑名单
//            commentLimit.insertIp(request)
            //留言计数器+1
            val servletContext = request.servletContext
            val messageNUm = servletContext.getAttribute("messageNum") as Int
            servletContext.setAttribute("messageNum", messageNUm + 1)

            PackingResults.toSuccessMap()
        } else {
            PackingResults.toWorngMap("回复失败！")
        }
    }

}