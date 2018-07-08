package blog.ctrl

import annotation.myInterface.IpLimit
import annotation.myInterface.ResultCache
import blog.bean.ReplyPojo
import blog.service.BlogMainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Frank
 * @date 2018/7/7 14:18
 */
@Controller
class BlogMainCtrl {

    @Autowired
    private lateinit var blogMainService: BlogMainService


    @IpLimit(10)
    @ResponseBody
    @GetMapping("/getComments.do")
    fun getComments(request: HttpServletRequest, @RequestParam id: Int): Map<*, *> {

        return blogMainService.getComments(id)
    }

    @ResponseBody
    @PostMapping("/postComment.do")
    fun postComment(httpServletRequest: HttpServletRequest, @ModelAttribute("replyPojo") replyPojo: ReplyPojo): Map<*, *> {
        return blogMainService.putReply(httpServletRequest, replyPojo)
    }

    @IpLimit(10)
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


    @IpLimit(10)
    @GetMapping("/message.do")
    fun toMessageBook(request: HttpServletRequest): String {
        return "messageBook"
    }

    @ResultCache(60)
    @IpLimit(10)
    @ResponseBody
    @GetMapping("/getMessages.do")
    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val a = System.currentTimeMillis()
        val result = blogMainService!!.getMessages(request)
        println(System.currentTimeMillis() - a)
        return result
    }

    @ResponseBody
    @PostMapping("/postMessage.do")
    fun postMessage(request: HttpServletRequest, @RequestParam("nickname") nickname: String,
                    @RequestParam("contactway") contactway: String, @RequestParam("message") message: String): Map<*, *> {
        return blogMainService!!.putMessage(request, nickname, message, contactway)
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