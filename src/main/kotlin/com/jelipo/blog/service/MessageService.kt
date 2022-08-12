package com.jelipo.blog.service

import com.jelipo.blog.pojo.Message
import com.jelipo.blog.repository.MessageRepository
import com.jelipo.blog.util.PackingResults
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

/**
 * @author Jelipo
 * @date 2018/10/7 20:00
 */
@Service
class MessageService {

    @Autowired
    private lateinit var messageRepository: MessageRepository

    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val list = messageRepository.findAllByOrderByCreatDateDesc()
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
            nickName = nickname,
            content = content,
            contactway = contactway,
            creatDate = LocalDateTime.now()
        )
        val savedMessage = messageRepository.save(message)
        return if (savedMessage.id != null) {
            PackingResults.toSuccessMap()
        } else {
            PackingResults.toWorngMap("回复失败！")
        }
    }

}