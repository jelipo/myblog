package com.jelipo.blog.ctrl

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author  Jelipo
 * @date  2019/6/26 12:56
 */
@Controller
class SiteMapCtrl {

    @GetMapping("sitemap.xml")
    fun sitemap(request: HttpServletRequest, response: HttpServletResponse): String {
        val url = request.requestURL
        val tempContextUrl = url.delete(url.length - request.requestURI.length, url.length).append("/").toString()
        response.setHeader("Content-Type", "application/xml")
        println(request.serverName)
        var listOf = listOf<String>("")
        listOf.groupBy {
            it.length
        }
        return "sitemap"
    }

}