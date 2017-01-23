package blog.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cao on 2017/1/15.
 */
@Controller
public class ResCtrl {

    @RequestMapping("/res/**")
    public String redirectRes(){

        return "redirect: https://www.baidu.com";
    }
}
