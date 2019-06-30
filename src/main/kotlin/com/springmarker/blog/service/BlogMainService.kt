package com.springmarker.blog.service

import com.springmarker.blog.pojo.Word
import com.springmarker.blog.mapper.WordMapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView

/**
 * @author Springmarker
 * @date 2018/7/11 22:35
 */
@Service
class BlogMainService {

    @Autowired
    private lateinit var wordMapper: WordMapper

    fun getIndexWordList(): MutableList<Word?> {
        return wordMapper.getWordListByPermission(1, 0, 10)
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