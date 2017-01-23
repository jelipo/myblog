package blog.service;


import blog.bean.BlogMainPojo;
import blog.dao.BlogMainDao;
import org.springframework.stereotype.Service;
import redisServer.service.IpLimit;
import server.PackingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cao on 2017/1/10.
 */

@Service("blog/service/BlogMain")
public class BlogMainService {

    public Map getBlogByPageNum(HttpServletRequest request,int pageNum, int getBlogNum) {
        if (ipLimit.isBlackIp(request,"10")){
            return packingResult.toWorngMap("Please stop!");
        }
        if (pageNum == 0 || getBlogNum == 0) {
            return packingResult.toWorngMap("参数错误");
        }
        Map map=new HashMap();
        map.put("getBlogNum",getBlogNum);
        map.put("startNum", (pageNum - 1) * getBlogNum);
        List<BlogMainPojo> list = blogMainDao.getWord(map);
        return packingResult.toSuccessMap(list);
    }

    public Map toWord(HttpServletRequest request,int id) {
        return blogMainDao.toWord(id);
    }

    @Resource(name = "blog/dao/BlogMainDao")
    private BlogMainDao blogMainDao;

    @Resource(name = "server/PackingResult")
    private PackingResult packingResult;

    @Resource(name = "redisServer/service/IpLimit")
    private IpLimit ipLimit;
}
