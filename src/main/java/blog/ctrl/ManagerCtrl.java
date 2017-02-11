package blog.ctrl;

import blog.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class ManagerCtrl {

    @Resource(name = "blog/service/ManagerService")
    private ManagerService managerService;

    @GetMapping("/needLogin.do")
    public String login() {
        return "manager/login";
    }

    @PostMapping("bossLogin.do")
    public String bossLogin(@RequestParam String username, @RequestParam String password, HttpSession httpSession, HttpServletRequest request) {
        Boolean isSuccess = managerService.checkLogin(request, username, password);
        if (isSuccess) {
            httpSession.setAttribute("isLogin", true);
            return "redirect:/gotoManager.do";
        }
        return "redirect:/needLogin.do";
    }

    @GetMapping("/gotoManager.do")
    public String gotoManager(HttpSession httpSession) {
        Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");
        if (isLogin == null ) {
            return "manager/login";
        }
        return "manager/manager";
    }

    @PostMapping("/uploadBlog.do")
    public String uploadBlog(HttpSession httpSession, @RequestParam("blogFile") MultipartFile blogFiles, @RequestParam("backgroundImage") MultipartFile backgroundImage,
                             @RequestParam String title, @RequestParam String writer,@RequestParam String summary, @RequestParam(required = false) Boolean allowComment) {
        Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");
        if (isLogin == null) {
            return "manager/manager";
        }
        managerService.uploadBlog(blogFiles,backgroundImage,title,writer,summary,allowComment);
        return "redirect:/gotoManager.do";
    }

}
