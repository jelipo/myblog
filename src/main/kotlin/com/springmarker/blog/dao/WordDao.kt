package com.springmarker.blog.dao

import com.springmarker.blog.bean.Word
import com.springmarker.blog.mapper.WordMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * @author Frank
 * @date 2018/10/4 23:20
 */
@Repository
class WordDao : WordMapper {

    @Autowired
    private lateinit var wordMapper: WordMapper

    override fun findById(id: Int): Word? {
        return wordMapper.findById(id)
    }

    override fun getWordListByPermission(permission: Int, start: Int, num: Int): MutableList<Word?> {
        return this.wordMapper.getWordListByPermission(permission, start, num)
    }

    override fun getWordByNickTitle(nickTitle: String): Word? {
        return wordMapper.getWordByNickTitle(nickTitle)
    }

}