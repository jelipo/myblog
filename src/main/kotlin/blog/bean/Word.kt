package blog.bean

import java.util.*

/**
 * @author Frank
 * @date 2018/7/7 14:14
 */
data class Word(
        var id: Long,
        var title: String,
        var permission: Long,
        var date: Date,
        var summary: String,
        var writer: String,
        var backgroundImage: String,
        var wordTextID: String
)