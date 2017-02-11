package blog.service;

import blog.dao.ManagerDao;
import init.initService.InitConfig;
import init.pojo.QiniuZoneParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qiniu.UploadFile;
import redisServer.service.IpLimit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service("blog/service/ManagerService")
public class ManagerService {

    @Value("#{config['blog.userName']}")
    private String userName;

    @Value("#{config['blog.passWord']}")
    private String passWord;

    @Resource(name = "redisServer/service/IpLimit")
    private IpLimit ipLimit;

    @Resource(name = "blog/dao/ManagerDao")
    private ManagerDao managerDao;

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name = "qiniu/UploadFile")
    private UploadFile uploadFile;

    private static final Logger logger = LogManager.getLogger(ManagerService.class);

    public Boolean checkLogin(HttpServletRequest request, String username, String password) {
        Boolean isBlackIp = ipLimit.isBlackIp(request, "50");
        if (isBlackIp) {
            return false;
        }
        Boolean isSuccess = username.equals(userName) && password.equals(passWord);
        return isSuccess;
    }

    public Boolean uploadBlog(MultipartFile blogFiles, MultipartFile backgroundImage, String title, String writer,String summary, Boolean allowComment) {
        Boolean isNull=blogFiles.isEmpty()||backgroundImage.isEmpty()||title.equals("")||writer.equals("")||summary.equals("");
        if (isNull){return false;}
        long nowTime=System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        int result;
        try {
            File htmlFile=new File(initConfig.getRootPath()+"/local/upload/"+nowTime+".html");
            blogFiles.transferTo(htmlFile);
            String[] name= backgroundImage.getOriginalFilename().split("\\.");
            String suffix="."+name[name.length-1];
            File imageFile=new File(initConfig.getRootPath()+"/local/upload/"+nowTime+suffix);
            backgroundImage.transferTo(imageFile);
            QiniuZoneParameters qiniuZoneParameters=initConfig.getMainQiniuZone();
            String htmlCDNFileName="blog/static/html/"+simpleDateFormat.format(nowTime)+".html";
            String imageCDNFileName="blog/static/image/"+simpleDateFormat.format(nowTime)+suffix;
            String htmlLink=uploadFile.simpleUpload(htmlFile.getAbsolutePath(),htmlCDNFileName,qiniuZoneParameters);
            String imageLink=uploadFile.simpleUpload(imageFile.getAbsolutePath(),imageCDNFileName,qiniuZoneParameters);
            Date date=new Date(nowTime);
            result=managerDao.insertBlog(title,1,summary,writer,imageLink,htmlLink,date,1,1);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (result==1){
            return true;
        }
        return false;
    }

}
