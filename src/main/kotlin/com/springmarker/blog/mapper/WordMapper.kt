package com.springmarker.blog.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.springmarker.blog.bean.Word
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * @author Springmarker
 * @date 2018/10/4 21:44
 */
@Repository
@Mapper
interface WordMapper : BaseMapper<Word> {

    fun findById(id: Int): Word?

    fun getWordListByPermission(permission: Int, start: Int, num: Int): MutableList<Word?>

    fun getWordByNickTitle(nickTitle: String): Word?

    fun getNearWordsByNickTitle(nickTitle: String): List<Word>

}