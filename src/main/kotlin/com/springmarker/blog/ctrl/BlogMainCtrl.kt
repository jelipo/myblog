package com.springmarker.blog.ctrl

import com.springmarker.blog.service.BlogMainService
import com.springmarker.blog.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Springmarker
 * @date 2018/7/7 14:18
 */
@Controller
class BlogMainCtrl {

    @Autowired
    private lateinit var blogMainService: BlogMainService

    @GetMapping("/", "/index")
    fun index(modelMap: ModelMap): String {
        val wordList = blogMainService.getIndexWordList()
        modelMap["list"] = wordList
        return "index"
    }

    @GetMapping("/toNone.do")
    fun toNone(request: HttpServletRequest): String {
        return "none"
    }

    @GetMapping("/messages")
    fun toMessageBook(request: HttpServletRequest): String {
        return "messageBook"
    }

    @ResponseBody
    @PostMapping("webspider.do")
    fun webspider(request: HttpServletRequest, @RequestParam("title") title: String, @RequestParam("screenshotPath") screenshotPath: String,
                  @RequestParam("htmlStr") htmlStr: String): Map<*, *> {
        val map = HashMap<String, String>()
        map.put("data", "hello")
        return map
    }

}