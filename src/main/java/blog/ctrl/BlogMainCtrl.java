package blog.ctrl;

import blog.service.BlogMainService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by cao on 2017/1/10.
 */
@Controller
public class BlogMainCtrl {

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
        Map map=blogMainService.toWord(request,id);
        request.setAttribute("htmlSrc",map.get("htmlSrc"));
        return "word";
    }

    @ResponseBody
    @RequestMapping("/getComments.do")
    public JSONObject getComments(HttpServletRequest request, @RequestParam int id){

        return blogMainService.getComments(id);
    }



    @Resource(name = "blog/service/BlogMain")
    private BlogMainService blogMainService;



}
