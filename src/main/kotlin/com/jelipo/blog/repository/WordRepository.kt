package com.jelipo.blog.repository

import com.jelipo.blog.pojo.Word
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : PagingAndSortingRepository<Word, Int> {

    fun findAllByPermissionOrderByCreatDateDesc(permission: Int, pageable: Pageable?): Page<Word>

    @Query("""
        select * from word
        ORDER BY "creat_date" DESC
        limit :limit
        offset :offset
    """, nativeQuery = true)
    fun getWords(@Param("limit") limit: Int, @Param("offset") offset: Int): List<Word>

    @Query("""
        select * from word
        WHERE type_id = (SELECT id from wordtype WHERE "name" = :type )
        ORDER BY "creat_date" DESC
        limit :limit
        offset :offset
    """, nativeQuery = true)
    fun getWordsByType(@Param("type") type: String?, @Param("limit") limit: Int, @Param("offset") offset: Int): List<Word>

    fun findAllByOrderByCreatDateDesc(pageable: Pageable): Page<Word>

    @Query("""
        (SELECT *
         FROM word
         WHERE permission = 1
           AND "creat_date" < (SELECT "creat_date" FROM word WHERE nick_title = :nickTitle)
         ORDER BY "creat_date" DESC
         LIMIT 1)
        UNION ALL
                (SELECT *
                 FROM word
                 WHERE permission = 1
                   AND "creat_date" = (SELECT "creat_date" FROM word WHERE nick_title = :nickTitle))
        UNION ALL
                (SELECT *
                 FROM word
                 WHERE permission = 1
                   AND "creat_date" > (SELECT "creat_date" FROM word WHERE nick_title = :nickTitle)
                 ORDER BY "creat_date" ASC
                 LIMIT 1);
    """, nativeQuery = true)
    fun getNearWordsByNickTitle(@Param("nickTitle") nickTitle: String): List<Word>
}