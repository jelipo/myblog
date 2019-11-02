package com.jelipo.blog.ctrl


import com.jelipo.blog.util.PackingResults
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * @author Jelipo
 * @date 2018/7/7 14:52
 */
@Controller
class BackgroundCtrl {
    @GetMapping("/needLogin.do")
    fun login(): String {
        return "manager/login"
    }

    @PostMapping("bossLogin.do")
    fun bossLogin(@RequestParam username: String, @RequestParam password: String, httpSession: HttpSession, request: HttpServletRequest): String {
        //TODO 判断
        if (true) {
            httpSession.setAttribute("isLogin", true)
            return "redirect:/gotoManager.do"
        }
        return "redirect:/needLogin.do"
    }

    @GetMapping("/gotoManager.do")
    fun gotoManager(httpSession: HttpSession): String {
        val isLogin = httpSession.getAttribute("isLogin") as Boolean ?: return "redirect:/needLogin.do"
        return "manager/manager"
    }

    @PostMapping("/uploadBlog.do")
    fun uploadBlog(request: HttpServletRequest,
                   httpSession: HttpSession,
                   @RequestParam("blogFile") blogFiles: MultipartFile,
                   @RequestParam("backgroundImage") backgroundImage: MultipartFile,
                   @RequestParam title: String,
                   @RequestParam writer: String,
                   @RequestParam summary: String,
                   @RequestParam(required = false) allowComment: Boolean?): String {
        val isLogin = httpSession.getAttribute("isLogin") as Boolean ?: return "redirect:/needLogin.do"
        val isSuccess = true
        //TODO 判断
        if (isSuccess) {
            request.setAttribute("message", "上传成功！")
        } else {
            request.setAttribute("message", "上传失败，请检查上传是否为空，或者检查日志！")
        }
        return "redirect:/gotoManager.do"
    }

    @ResponseBody
    @PostMapping("/uploadFile.do")
    fun uploadFile(request: HttpServletRequest, httpSession: HttpSession, @RequestParam("cdnFile") cdnFile: MultipartFile, @RequestParam(required = false) filename: String): Map<*, *> {
        val isLoginAny = httpSession.getAttribute("isLogin") ?: return PackingResults.toWorngMap("出现错误！")
        return HashMap<String, String>()
    }


    @GetMapping("/test.do")
    fun test(httpSession: HttpSession, request: HttpServletRequest): String {
        return "manager/login"
    }
}