package com.jelipo.blog.repository

import com.jelipo.blog.pojo.Word
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : PagingAndSortingRepository<Word, Int> {

    fun findAllByPermissionOrderByCreatDate(permission: Int, pageable: Pageable): Page<Word>

    @Query("""
        select * from word
        WHERE type_id = (SELECT id from type WHERE "name" = #{type})
        ORDER BY "date" DESC
        limit #{limit}
        offset #{offset}
    """, nativeQuery = true)
    fun getWordsByType(type: String?, limit: Int, offset: Int): List<Word>

    fun findAllByOrderByCreatDateDesc(pageable: Pageable): Page<Word>

    @Query("""
        (SELECT *
         FROM word
         WHERE permission = 1
           AND "date" < (SELECT "date" FROM word WHERE nick_title = #{nickTitle})
         ORDER BY "date" DESC
         LIMIT 1)
        UNION ALL
                (SELECT *
                 FROM word
                 WHERE permission = 1
                   AND "date" = (SELECT "date" FROM word WHERE nick_title = #{nickTitle}))
        UNION ALL
                (SELECT *
                 FROM word
                 WHERE permission = 1
                   AND "date" > (SELECT "date" FROM word WHERE nick_title = #{nickTitle})
                 ORDER BY "date" ASC
                 LIMIT 1);
    """, nativeQuery = true)
    fun getNearWordsByNickTitle(nickTitle: String): List<Word>
}