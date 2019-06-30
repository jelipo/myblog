package com.springmarker.blog.service

import com.springmarker.blog.pojo.Message
import com.springmarker.blog.mapper.MessageMapper
import com.springmarker.blog.util.PackingResults
import org.apache.commons.lang3.StringEscapeUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

/**
 * @author Springmarker
 * @date 2018/10/7 20:00
 */
@Service
class MessageService {

    @Autowired
    private lateinit var messageMapper: MessageMapper

    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val list = messageMapper.getMessages()
        return PackingResults.toSuccessMap(list)
    }

    fun putMessage(request: HttpServletRequest, nickname: String, content: String, contactway: String): Map<*, *> {
        var nickname = nickname
        if (content.isEmpty()) {
            return PackingResults.toWorngMap("回复失败，请检查您的内容！")
        }
        val nowTime = System.currentTimeMillis()
        if (nickname.isEmpty()) {
            nickname = "游客" + nowTime % 10000000
        }
        val message = Message(
                nickName = StringEscapeUtils.escapeHtml4(nickname),
                content = StringEscapeUtils.escapeHtml4(content),
                contactway = contactway,
                date = LocalDateTime.now()
        )
        val result = messageMapper.saveMessage(message)
        return if (result > 0) {
            PackingResults.toSuccessMap()
        } else {
            PackingResults.toWorngMap("回复失败！")
        }
    }

}