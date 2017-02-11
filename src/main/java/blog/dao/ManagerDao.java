package blog.dao;

import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository("blog/dao/ManagerDao")
public interface ManagerDao {
    int insertBlog(String title, int permission, String summary, String writer, String backgroundImage,
                   String htmlSrc, Date date, int typeId,int allowComment);
}
