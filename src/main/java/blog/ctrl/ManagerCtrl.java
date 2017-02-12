package blog.ctrl;

import blog.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import util.PackingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


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
            return "redirect:/needLogin.do";
        }
        return "manager/manager";
    }

    @PostMapping("/uploadBlog.do")
    public String uploadBlog(HttpServletRequest request,HttpSession httpSession, @RequestParam("blogFile") MultipartFile blogFiles, @RequestParam("backgroundImage") MultipartFile backgroundImage,
                             @RequestParam String title, @RequestParam String writer,@RequestParam String summary, @RequestParam(required = false) Boolean allowComment) {
        Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");
        if (isLogin == null) {
            return "redirect:/needLogin.do";
        }
        Boolean isSuccess=managerService.uploadBlog(blogFiles,backgroundImage,title,writer,summary,allowComment);
        if (isSuccess){
            request.setAttribute("msg","上传成功！");
        }else {
            request.setAttribute("msg","上传失败，请检查上传是否为空，或者检查日志！");
        }
        return "redirect:/gotoManager.do";
    }

    @ResponseBody
    @PostMapping("/uploadFile.do")
    public Map uploadFile(HttpServletRequest request,HttpSession httpSession,@RequestParam("cdnFile") MultipartFile cdnFile,@RequestParam(required = false) String filename){
        Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");
        if (isLogin == null) {
            return PackingResult.toWorngMap("出现错误！");
        }
        System.out.println(filename);
        Map map=managerService.fileUpload(cdnFile,filename);
        return map;
    }

}
