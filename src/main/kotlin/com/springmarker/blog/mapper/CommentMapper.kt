package com.springmarker.blog.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.springmarker.blog.bean.Comment
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * @author Springmarker
 * @date 2018/10/6 14:21
 */
@Repository
@Mapper
interface CommentMapper : BaseMapper<Comment> {

    fun getCommentsByWordId(nickTitle: String): List<Comment>


}