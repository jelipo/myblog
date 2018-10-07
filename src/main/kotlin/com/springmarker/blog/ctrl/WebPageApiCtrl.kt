package com.springmarker.blog.ctrl

import com.springmarker.blog.bean.Reply
import com.springmarker.blog.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
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

    /**
     * 获取评论
     */
    @GetMapping("/word/{wordId}/comments")
    fun getComments(request: HttpServletRequest, @PathVariable wordId: String): Map<*, *> {
        val comments = commentService.getComments(wordId)
        return comments
    }

    /**
     * 保存评论
     *
     */
    @PostMapping("/word/{wordId}/comments")
    fun postComment(httpServletRequest: HttpServletRequest, @PathVariable wordId: String,
                    @ModelAttribute("replyPojo") replyPojo: Reply): Map<*, *> {
        replyPojo.wordId = wordId.toInt()
        return commentService.putReply(httpServletRequest, replyPojo)
    }

}