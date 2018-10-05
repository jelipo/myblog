package com.springmarker.blog.ctrl

import com.springmarker.blog.service.BlogMainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
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

    @GetMapping("/word/{nickTitle}.html")
    fun toWord(request: HttpServletRequest, @PathVariable nickTitle: String, modelAndView: ModelAndView): ModelAndView {
        val word = blogMainService.getWordByNickTitle(nickTitle)
        if (word == null) {
            modelAndView.viewName = "404"
            return modelAndView
        }
        modelAndView.model["word"] = word
        modelAndView.viewName = "word"
        return modelAndView
    }


}