package com.springmarker.blog.ctrl

import com.fasterxml.jackson.annotation.JsonView
import com.springmarker.blog.pojo.Comment
import com.springmarker.blog.pojo.Message
import com.springmarker.blog.pojo.Reply
import com.springmarker.blog.service.CommentService
import com.springmarker.blog.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Springmarker
 * @date 2018/10/5 20:14
 */
@RestController
@RequestMapping("/api")
class WebPageApiCtrl {

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var messageService: MessageService

    /**
     * 获取评论
     */
    @JsonView(Comment.Public::class)
    @GetMapping("/word/{wordId}/comments")
    fun getComments(request: HttpServletRequest, @PathVariable wordId: String): Map<*, *> {
        val comments = commentService.getComments(wordId)
        println("" + Date() + "   " + request.getHeader("HTTP_X_FORWARDED_FOR"))
        return comments
    }

    /**
     * 保存评论
     *
     */
    @PostMapping("/word/{wordId}/comments")
    fun postComment(httpServletRequest: HttpServletRequest,
                    @PathVariable wordId: String,
                    @ModelAttribute("replyPojo") replyPojo: Reply): Map<*, *> {
        replyPojo.wordId = wordId.toInt()
        return commentService.putReply(httpServletRequest, replyPojo)
    }

    /**
     * 获取留言List
     */
    @JsonView(Message.Public::class)
    @GetMapping("/messages")
    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val result = messageService.getMessages(request)
        return result
    }

    @PostMapping("/messages")
    fun postMessage(@RequestParam("nickname") nickname: String,
                    @RequestParam("contactway") contactway: String,
                    @RequestParam("message") message: String,
                    httpServletRequest: HttpServletRequest): Map<*, *> {
        return messageService.putMessage(httpServletRequest, nickname, message, contactway)
    }

}