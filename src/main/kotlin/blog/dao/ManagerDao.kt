package blog.dao

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.util.*

/**
 * @author Frank
 * @date 2018/7/7 15:50
 */
@Mapper
interface ManagerDao {

    fun insertBlog(@Param("title") title: String, @Param("permission") permission: Int, @Param("summary") summary: String,
                   @Param("writer") writer: String, @Param("backgroundImage") backgroundImage: String,
                   @Param("date") date: Date, @Param("typeId") typeId: Int, @Param("allowComment") allowComment: Int,
                   @Param("wordTextId") wordTextId: String): Int

    fun insertWordText(@Param("text") text: String, @Param("wordTextId") wordTextId: String): Int
}