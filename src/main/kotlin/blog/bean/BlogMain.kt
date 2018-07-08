package blog.bean

import java.time.LocalDate

/**
 * @author Frank
 * @date 2018/7/7 15:22
 */
data class BlogMain(
        var id: String,
        var title: String,
        var date: LocalDate,
        var summary: String,
        var writer: String,
        var backgroundImage: String,
        var formatDate: String
)