package blog.bean

import java.time.LocalDate

/**
 * @author Frank
 * @date 2018/7/7 15:30
 */
class Comment(
        val id: Int = 0,
        val maincomment: Int = 0,
        val observername: String,
        val toobservername: String,
        val date: LocalDate,
        val formatDate: String,
        val vicecomment_maincomment_id: Int,
        val value: String,
        val viceComment: List<CommentPojo>
)