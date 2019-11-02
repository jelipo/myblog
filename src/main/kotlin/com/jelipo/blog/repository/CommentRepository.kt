package com.jelipo.blog.repository

import com.jelipo.blog.pojo.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : CrudRepository<Comment, Int> {

    fun findAllByWordId(wordId: Int): List<Comment>

}