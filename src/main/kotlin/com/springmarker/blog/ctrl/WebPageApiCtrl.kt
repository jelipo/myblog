package com.springmarker.blog.ctrl

import com.springmarker.blog.service.BlogMainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * @author Frank
 * @date 2018/10/5 20:14
 */
@RestController
@RequestMapping("/api")
class WebPageApiCtrl {

    @Autowired
    private lateinit var blogMainService: BlogMainService

    @GetMapping("/word/{nickTitle}/comments")
    fun getComments(request: HttpServletRequest, @PathVariable nickTitle: String): Map<*, *> {

        return blogMainService.getComments(nickTitle)
    }


}