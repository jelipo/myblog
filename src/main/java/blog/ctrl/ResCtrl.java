package blog.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ResCtrl {

    @Value("#{config['qiniu.projectName']}")
    private String projectName;

    @Value("#{config['qiniu.localResourceFloder']}")
    private String localResourceFloder;

    @RequestMapping("/res/**")
    public String redirectRes(HttpServletRequest httpServletRequest) {
        String servletPath = httpServletRequest.getServletPath();
        return "redirect:https://res.springmarker.com/"+ projectName+"/"+ servletPath;
    }

    @RequestMapping("/other/**")
    public String redirectRes2(HttpServletRequest httpServletRequest) {
        String servletPath = httpServletRequest.getServletPath();
        return "redirect: https://res.springmarker.com/"+ projectName+"/"+localResourceFloder+"/"+ servletPath;
    }
}
