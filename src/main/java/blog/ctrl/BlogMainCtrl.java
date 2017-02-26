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


@Controller
public class BlogMainCtrl {

    @Resource(name = "blog/service/BlogMainService")
    private BlogMainService blogMainService;

    @ModelAttribute
    public void init(HttpServletRequest request){

//        Jedis jedis = pool.getResource();
//        System.out.println(jedis.get("name"));
    }


    @ResponseBody
    @GetMapping("/getWord.do")
    public Map getWord(HttpServletRequest request,@RequestParam int pageNum, @RequestParam int getBlogNum,@RequestParam(required = false) String type){
        Map result=blogMainService.getBlogByPageNum(request,pageNum,getBlogNum,type);
        return result;
    }

    @GetMapping("/toWord.do")
    public String toWord(HttpServletRequest request,@RequestParam int id){
        Boolean isSuccess=blogMainService.toWord(request,id);
        if (isSuccess){
            return "word";
        } else{
            return "404";
        }
    }

    @ResponseBody
    @GetMapping("/getComments.do")
    public Map getComments(HttpServletRequest request, @RequestParam int id){

        return blogMainService.getComments(id);
    }

    @ResponseBody
    @PostMapping("/postComment.do")
    public Map postComment( HttpServletRequest httpServletRequest,@ModelAttribute("replyPojo")ReplyPojo replyPojo){
        Map resultMap=blogMainService.putReply(httpServletRequest,replyPojo);
        return resultMap;
    }

    @GetMapping("/moreWords.do")
    public String moreWords(HttpServletRequest request,@RequestParam int pageNum ,@RequestParam(required = false) String type){
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("type",type);
        return "wordList";
    }


    @GetMapping("/toNone.do")
    public String toNone(HttpServletRequest request){
        return "none";
    }

    @GetMapping("/message.do")
    public String toMessageBook(){
        return "messageBook";
    }

    @ResponseBody
    @GetMapping ("/getMessages.do")
    public Map getMessages(HttpServletRequest request){
        Map result=blogMainService.getMessages(request);
        return result;
    }

    @ResponseBody
    @PostMapping("/postMessage.do")
    public Map postMessage(HttpServletRequest request,@RequestParam("nickname") String nickname,
                           @RequestParam("contactway") String  contactway,@RequestParam("message") String  message){
        Map map=blogMainService.putMessage(request,nickname,message,contactway);
        return map;
    }


}
