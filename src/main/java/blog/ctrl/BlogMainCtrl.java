package blog.ctrl;

import blog.bean.ReplyPojo;
import blog.service.BlogMainService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by cao on 2017/1/10.
 */
@Controller
public class BlogMainCtrl {

    @Resource(name = "blog/service/BlogMain")
    private BlogMainService blogMainService;

    @ModelAttribute
    public void init(HttpServletRequest request){

//        Jedis jedis = pool.getResource();
//        System.out.println(jedis.get("name"));
    }


    @ResponseBody
    @RequestMapping("/getWord.do")
    public Map getWord(HttpServletRequest request,@RequestParam int pageNum, @RequestParam int getBlogNum){
        Map result=blogMainService.getBlogByPageNum(request,pageNum,getBlogNum);
        return result;
    }

    @RequestMapping("/toWord.do")
    public String toWord(HttpServletRequest request,@RequestParam int id){
        Boolean isSuccess=blogMainService.toWord(request,id);
        if (isSuccess){
            return "word";
        } else{
            return "404";
        }
    }

    @ResponseBody
    @RequestMapping("/getComments.do")
    public Map getComments(HttpServletRequest request, @RequestParam int id){

        return blogMainService.getComments(id);
    }

    @ResponseBody
    @PostMapping("/postComment.do")
    public Map postComment( HttpServletRequest httpServletRequest,@ModelAttribute("replyPojo")ReplyPojo replyPojo){
        Map resultMap=blogMainService.putReply(httpServletRequest,replyPojo);
        return resultMap;
    }

    @RequestMapping("/moreWords.do")
    public String moreWords(HttpServletRequest request,@RequestParam int pageNum){
        request.setAttribute("pageNum",pageNum);
        return "wordList";
    }

    @RequestMapping("/toNone.do")
    public String toNone(HttpServletRequest request){
        return "none";
    }





}
