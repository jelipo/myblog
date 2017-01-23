package blog.dao;

import blog.bean.BlogMainPojo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cao on 2017/1/10.
 */

@Repository("blog/dao/BlogMainDao")

public interface BlogMainDao {
    public List<BlogMainPojo> getWord(Map map);

    public HashMap toWord(int id);
}
