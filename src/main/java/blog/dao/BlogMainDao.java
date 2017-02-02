package blog.dao;

import blog.bean.BlogMainPojo;
import blog.bean.CommentPojo;
import blog.bean.ReplyPojo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cao on 2017/1/10.
 */

@Repository("blog/dao/BlogMainDao")

public interface BlogMainDao {
    List<BlogMainPojo> getWord(Map map);

    HashMap toWord(int id);

    List<CommentPojo> getComments(int id);

    int putReply(Map parm);
}
