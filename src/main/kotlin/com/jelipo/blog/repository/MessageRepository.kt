package com.jelipo.blog.repository

import com.jelipo.blog.pojo.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<Message, Int> {

    fun findAllByOrderByCreatDateDesc(): List<Message>

}