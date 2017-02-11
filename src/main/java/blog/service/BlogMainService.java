package blog.service;


import blog.bean.BlogMainPojo;
import blog.bean.CommentPojo;
import blog.bean.ReplyPojo;
import blog.bean.WordPojo;
import blog.dao.BlogMainDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import redisServer.service.CommentLimit;
import redisServer.service.IpLimit;
import server.PackingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("blog/service/BlogMainService")
public class BlogMainService {

    @Resource(name = "blog/dao/BlogMainDao")
    private BlogMainDao blogMainDao;

    @Resource(name = "server/PackingResult")
    private PackingResult packingResult;

    @Resource(name = "redisServer/service/IpLimit")
    private IpLimit ipLimit;

    @Resource(name="redisServer/service/CommentLimit")
    private CommentLimit commentLimit;

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
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月dd,yyyy");
        for (int i=0;i<list.size();i++){
            BlogMainPojo blogMainPojo=list.get(i);
            blogMainPojo.setFormatDate(simpleDateFormat.format(blogMainPojo.getDate()));
        }
        return packingResult.toSuccessMap(list);
    }

    public Boolean toWord(HttpServletRequest request,int id) {
        List<WordPojo> list=blogMainDao.toWord(id);
        int listSize=list.size();
        WordPojo mainWordPojo;
        if (listSize==2){
            if (list.get(0).getId()==id){
                mainWordPojo=list.get(0);
                request.setAttribute("lastWordId",id);
                request.setAttribute("lastWordTitle","没有上一篇了");
                request.setAttribute("nextWordId",list.get(1).getId());
                request.setAttribute("nextWordTitle",list.get(1).getTitle());
            }else {
                mainWordPojo=list.get(1);
                request.setAttribute("lastWordId",list.get(0).getId());
                request.setAttribute("lastWordTitle",list.get(0).getTitle());
                request.setAttribute("nextWordId",id);
                request.setAttribute("nextWordTitle","没有下一篇了");
            }
        }else if(listSize==1){
            return false;
        }else {
            mainWordPojo=list.get(1);
            request.setAttribute("lastWordId",list.get(0).getId());
            request.setAttribute("lastWordTitle",list.get(0).getTitle());
            request.setAttribute("nextWordId",list.get(2).getId());
            request.setAttribute("nextWordTitle",list.get(2).getTitle());
        }

        request.setAttribute("htmlSrc",mainWordPojo.getHtmlsrc());
        request.setAttribute("wordId",id);
        request.setAttribute("title",mainWordPojo.getTitle());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月dd,yyyy");
        request.setAttribute("date",simpleDateFormat.format(mainWordPojo.getDate()));
        return true;
    }

    public Map getComments(int id){
        List<CommentPojo> list= blogMainDao.getComments(id);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月dd,yyyy HH:mm:ss");
        JSONObject json=new JSONObject();
        for(int i=0;i<list.size();i++){
            CommentPojo comment=list.get(i);
            comment.setFormatDate(simpleDateFormat.format(comment.getDate()));
            if (comment.getIsmaincomment()==1){
                comment.viceComment=new ArrayList<>();
                json.put(String.valueOf(comment.getId()),comment);
            }else{
                int vice_main_id=comment.getVicecomment_maincomment_id();
                CommentPojo viceComment=(CommentPojo)json.get(String.valueOf(vice_main_id));
                viceComment.viceComment.add(comment);
            }
        }
        return packingResult.toSuccessMap(json);
    }

    public Map putReply(HttpServletRequest request,ReplyPojo replyPojo){
        if (commentLimit.isBanIp(request)){
            return packingResult.toWorngMap("您暂时还不能操作！");
        }
        if (replyPojo.getWordId().equals("")||replyPojo.getValue().equals("")||replyPojo.getIsNewMainComment().equals("")){
            return packingResult.toWorngMap("服务器出现错误！");
        }
        long timeNow=System.currentTimeMillis();
        if (replyPojo.getNickname().equals("")){
            replyPojo.setNickname("游客"+timeNow%10000000);
        }
        Map map=new HashMap();
        if (!(replyPojo.getIsNewMainComment().equals("1"))){
            if (replyPojo.getMainCommentId().equals("")){
                return packingResult.toWorngMap("服务器出现错误！");
            }
            map.put("toObserverName",replyPojo.getToObservername().equals("")?null:replyPojo.getToObservername());
            map.put("viceComment_mainComment_id",replyPojo.getMainCommentId());
        }
        map.put("wordId",replyPojo.getWordId());
        map.put("isMainComment",replyPojo.getIsNewMainComment().equals("1")?1:2);
        map.put("observerName",replyPojo.getNickname());
        map.put("date",new Date(System.currentTimeMillis()));
        map.put("value", StringEscapeUtils.escapeHtml4(replyPojo.getValue()).replace("\n","<br>") );
        map.put("email",replyPojo.getEmail());
        int result=blogMainDao.putReply(map);
        if (result>0){
            commentLimit.insertIp(request);
            return packingResult.toSuccessMap(new JSONObject());
        }else{
            return packingResult.toWorngMap("服务器出现错误！");
        }
    }



}
