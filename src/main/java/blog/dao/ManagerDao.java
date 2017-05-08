package blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository("blog/dao/ManagerDao")
public interface ManagerDao {

    int insertBlog(@Param("title") String title, @Param("permission") int permission, @Param("summary") String summary,
                   @Param("writer") String writer, @Param("backgroundImage") String backgroundImage, @Param("date") Date date,
                   @Param("typeId") int typeId, @Param("allowComment") int allowComment, @Param("wordTextId") String wordTextId);

    int insertWordText(@Param("text") String text, @Param("wordTextId") String wordTextId);

}
