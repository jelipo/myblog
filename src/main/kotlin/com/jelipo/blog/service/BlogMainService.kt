package com.jelipo.blog.service

import com.jelipo.blog.pojo.Word
import com.jelipo.blog.repository.WordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.servlet.ModelAndView

/**
 * @author Jelipo
 * @date 2018/7/11 22:35
 */
@Service
class BlogMainService {

    @Autowired
    private lateinit var wordRepository: WordRepository

    fun getIndexWordList(): List<Word?> {
        val page = wordRepository.findAllByPermissionOrderByCreatDateDesc(1, null)
        return page.toList()
    }

    fun getWordByNickTitle(modelAndView: ModelAndView, nickTitle: String): Boolean {
        val list = wordRepository.getNearWordsByNickTitle(nickTitle)
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

    fun getWordsByType(type: String?, page: Int): List<Word> {
        val limit = 10
        return if (StringUtils.isEmpty(type)) {
            val pageList = wordRepository.findAllByOrderByCreatDateDesc(
                PageRequest.of(page, limit)
            )
            pageList.toList()
        } else {
            if (type == "all") {
                Sort.by(Sort.Order.desc("creat_time"))
                val words =
                    wordRepository.findAll(PageRequest.of(page, limit, Sort.by(Word::creatDate.name).descending()))
                words.content
            } else {
                val wordsByType = wordRepository.getWordsByType(
                    type, PageRequest.of(
                        page, limit, Sort.by("creat_date").descending()
                    )
                )
                wordsByType.content
            }

        }
    }

}