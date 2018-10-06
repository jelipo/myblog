package com.springmarker.blog.service

import com.springmarker.blog.bean.Word
import com.springmarker.blog.dao.BlogMainDao
import com.springmarker.blog.mapper.CommentMapper
import com.springmarker.blog.mapper.WordMapper
import org.apache.commons.lang3.StringEscapeUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import util.PackingResult
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashMap

/**
 * @author Springmarker
 * @date 2018/7/11 22:35
 */
@Service
class BlogMainService {

    @Autowired
    private lateinit var blogMainDao: BlogMainDao

    @Autowired
    private lateinit var wordMapper: WordMapper

    @Autowired
    private lateinit var commentMapper: CommentMapper

    fun getIndexWordList(): MutableList<Word?> {
        val wordList = wordMapper.getWordListByPermission(1, 0, 10)
        return wordList
    }


    fun getBlogByPageNum(request: HttpServletRequest, pageNum: Int, getBlogNum: Int, type: String): Map<*, *> {
        if (pageNum == 0 || getBlogNum == 0) {
            return PackingResult.toWorngMap("参数错误")
        }
        val map = HashMap<String, Any>()
        map.put("getBlogNum", getBlogNum)
        map.put("startNum", (pageNum - 1) * getBlogNum)
        map.put("type", type)
        val list = blogMainDao!!.getWord(map)
        val simpleDateFormat = SimpleDateFormat("MM月dd,yyyy")
        for (i in list.indices) {
            val blogMainPojo = list[i]
            blogMainPojo.formatDate = simpleDateFormat.format(blogMainPojo.date)
        }
        return PackingResult.toSuccessMap(list)
    }

    fun getWordByNickTitle(modelAndView: ModelAndView, nickTitle: String): Boolean {
        val list = wordMapper.getNearWordsByNickTitle(nickTitle)
        if (list.size == 3) {
            modelAndView.model["word"] = list[1]
            modelAndView.model["lastWord"] = list[0]
            modelAndView.model["nextWord"] = list[2]
        } else if (list.size == 2) {
            if (list[0].nickTitle == nickTitle) {
                modelAndView.model["word"] = list[0]
                modelAndView.model["nextWord"] = list[1]
            } else {
                modelAndView.model["word"] = list[1]
                modelAndView.model["lastWord"] = list[0]
            }
        } else if (list.size == 1) {
            modelAndView.model["word"] = list[0]
        } else {
            return false
        }
        return true
    }


    fun getMessages(request: HttpServletRequest): Map<*, *> {
        val list = blogMainDao.getMessages()
        return PackingResult.toSuccessMap(list)
    }

    fun putMessage(request: HttpServletRequest, nickname: String, content: String, contactway: String): Map<*, *> {
        var nickname = nickname

//        if (commentLimit.isBanIp(request)!!) {
//            return PackingResult.toWorngMap("您暂时还不能操作！")
//        }
        if (content == "") {
            return PackingResult.toWorngMap("回复失败，请检查您的内容！")
        }
        val nowTime = System.currentTimeMillis()
        if (nickname == "") {
            nickname = "游客" + nowTime % 10000000
        }
        val date = Date(nowTime)
        val result = blogMainDao.putMessage(StringEscapeUtils.escapeHtml4(nickname), date, StringEscapeUtils.escapeHtml4(content), StringEscapeUtils.escapeHtml4(contactway))
        return if (result > 0) {
            //加入暂时黑名单
//            commentLimit.insertIp(request)
            //留言计数器+1
            val servletContext = request.servletContext
            val messageNUm = servletContext.getAttribute("messageNum") as Int
            servletContext.setAttribute("messageNum", messageNUm + 1)

            PackingResult.toSuccessMap()
        } else {
            PackingResult.toWorngMap("回复失败！")
        }
    }
}