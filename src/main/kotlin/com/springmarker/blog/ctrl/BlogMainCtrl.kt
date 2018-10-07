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

    @Autowired
    private lateinit var messageService: MessageService


    @GetMapping("/", "/index")
    fun index(modelMap: ModelMap): String {
        val wordList = blogMainService.getIndexWordList()
        modelMap["list"] = wordList
        return "index"
    }


    @GetMapping("/moreWords.do")
    fun moreWords(request: HttpServletRequest, @RequestParam pageNum: Int, @RequestParam(required = false) type: String): String {
        request.setAttribute("pageNum", pageNum)
        request.setAttribute("type", type)
        return "wordList"
    }


    @GetMapping("/toNone.do")
    fun toNone(request: HttpServletRequest): String {
        return "none"
    }


    @GetMapping("/message.do")
    fun toMessageBook(request: HttpServletRequest): String {
        return "messageBook"
    }

    @ResponseBody
    @GetMapping("/getMessages.do")
    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val a = System.currentTimeMillis()
        val result = messageService.getMessages(request)
        println(System.currentTimeMillis() - a)
        return result
    }

    @ResponseBody
    @PostMapping("/postMessage.do")
    fun postMessage(request: HttpServletRequest, @RequestParam("nickname") nickname: String,
                    @RequestParam("contactway") contactway: String, @RequestParam("message") message: String): Map<*, *> {
        return messageService.putMessage(request, nickname, message, contactway)
    }

    @ResponseBody
    @PostMapping("webspider.do")
    fun webspider(request: HttpServletRequest, @RequestParam("title") title: String, @RequestParam("screenshotPath") screenshotPath: String,
                  @RequestParam("htmlStr") htmlStr: String): Map<*, *> {
        println(title)
        println(screenshotPath)
        println(htmlStr)
        val map = HashMap<String, String>()
        map.put("data", "hello")
        return map
    }

}