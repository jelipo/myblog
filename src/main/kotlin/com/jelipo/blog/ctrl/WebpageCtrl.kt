package com.jelipo.blog.ctrl

import com.jelipo.blog.service.BlogMainService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

/**
 * @author Jelipo
 * @date 2018/7/7 14:41
 */
@Controller
class WebpageCtrl(
    private val blogMainService: BlogMainService
) {

    @GetMapping("/word/{nickTitle}.html")
    fun toWord(request: HttpServletRequest, @PathVariable nickTitle: String, modelAndView: ModelAndView): ModelAndView {
        val result = blogMainService.getWordByNickTitle(modelAndView, nickTitle)
        if (!result) {
            modelAndView.viewName = "404"
            return modelAndView
        }
        modelAndView.viewName = "word"
        return modelAndView
    }

    @GetMapping("/list")
    fun getWords(modelAndView: ModelAndView): ModelAndView {
        val words = blogMainService.getWordsByType(null, 1)
        modelAndView.model["list"] = words
        modelAndView.viewName = "wordList"
        return modelAndView
    }

    @GetMapping("/list/{type}")
    fun getWordsByType(@PathVariable type: String?, modelAndView: ModelAndView): ModelAndView {
        val words = blogMainService.getWordsByType(type, 1)
        modelAndView.model["list"] = words
        modelAndView.model["type"] = type
        modelAndView.model["page"] = "1"
        modelAndView.viewName = "wordList"
        return modelAndView
    }

    @GetMapping("/list-api/{type}")
    fun getWordsByTypeApi(
        @PathVariable type: String, @RequestParam("page", required = false) page: Int?, modelAndView: ModelAndView
    ): ModelAndView {
        val words = blogMainService.getWordsByType(type, page ?: 1)
        modelAndView.model["list"] = words
        modelAndView.model["type"] = type
        modelAndView.model["page"] = page
        modelAndView.viewName = "webparts/list"
        return modelAndView
    }

}