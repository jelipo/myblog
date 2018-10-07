package com.springmarker.blog.service

import com.springmarker.blog.bean.Word
import com.springmarker.blog.dao.BlogMainDao
import com.springmarker.blog.mapper.CommentMapper
import com.springmarker.blog.mapper.WordMapper
import com.springmarker.blog.util.PackingResults

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import java.text.SimpleDateFormat
import javax.servlet.http.HttpServletRequest

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

    private val sdfMMddyyyy = SimpleDateFormat("MM月dd,yyyy")

    fun getBlogByPageNum(request: HttpServletRequest, pageNum: Int, getBlogNum: Int, type: String): Map<*, *> {
        if (pageNum == 0 || getBlogNum == 0) {
            return PackingResults.toWorngMap("参数错误")
        }
        val map = HashMap<String, Any>()
        map["getBlogNum"] = getBlogNum
        map["startNum"] = (pageNum - 1) * getBlogNum
        map["type"] = type
        val list = blogMainDao.getWord(map)
        for (i in list.indices) {
            val blogMainPojo = list[i]
            blogMainPojo.formatDate = sdfMMddyyyy.format(blogMainPojo.date)
        }
        return PackingResults.toSuccessMap(list)
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

    fun getWordsByType(type: String?, page: String?): List<Word> {
        val limit = 10
        return wordMapper.getWordsByType(type, limit, (if (page == null) 0 else (page.toInt() - 1)) * limit)
    }

}