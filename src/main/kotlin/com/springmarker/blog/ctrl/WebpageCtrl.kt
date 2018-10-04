package com.springmarker.blog.ctrl

import com.springmarker.blog.service.BlogMainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * @author Frank
 * @date 2018/7/7 14:41
 */
@Controller
class WebpageCtrl {

    @Autowired
    private lateinit var blogMainService: BlogMainService


    @ResponseBody
    @GetMapping("/getWord.do")
    fun getWord(request: HttpServletRequest, @RequestParam pageNum: Int, @RequestParam getBlogNum: Int,
                @RequestParam(required = false) type: String): Map<*, *> {
        return blogMainService.getBlogByPageNum(request, pageNum, getBlogNum, type)
    }

    @GetMapping("/toWord.do")
    fun toWord(request: HttpServletRequest, @RequestParam id: Int): String {
        val isSuccess = blogMainService.toWord(request, id)
        return if (isSuccess!!) {
            "word"
        } else {
            "404"
        }
    }

}