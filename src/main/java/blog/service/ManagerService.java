package blog.service;

import blog.dao.ManagerDao;
import init.initService.InitConfig;
import init.pojo.QiniuZoneParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qiniu.UploadFile;
import redisServer.service.IpLimit;
import util.PackingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("blog/service/ManagerService")
public class ManagerService {

    @Value("#{config['blog.userName']}")
    private String userName;

    @Value("#{config['blog.passWord']}")
    private String passWord;

    @Value("#{config['qiniu.CDNBlogHtmlPath']}")
    private String CDNBlogHtmlPath;

    @Value("#{config['qiniu.CDNBLogBackgroundImagePath']}")
    private String CDNBLogBackgroundImagePath;

    @Value("#{config['qiniu.otherPath']}")
    private String CDNOtherPath;

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

    /**
     * 文章上传，临时存放到本地，然后把html文件和背景图片上传到七牛CDN中
     *
     * @param blogFiles       html文件
     * @param backgroundImage 背景图片
     * @param title           标题
     * @param writer          作者
     * @param summary         简介
     * @param allowComment    允许评论
     * @return
     */
    public Boolean uploadBlog(MultipartFile blogFiles, MultipartFile backgroundImage, String title, String writer, String summary, Boolean allowComment) {
        Boolean isNull = blogFiles.isEmpty() || backgroundImage.isEmpty() || title.equals("") || writer.equals("") || summary.equals("");
        if (isNull) {
            return false;
        }
        long nowTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        int result;
        try {
            File htmlFile = new File(initConfig.getTempPath() + nowTime + ".html");
            blogFiles.transferTo(htmlFile);
            String imageRealName=backgroundImage.getOriginalFilename();
            String suffix = imageRealName.substring(imageRealName.lastIndexOf("."));
            File imageFile = new File(initConfig.getTempPath() + nowTime + suffix);
            backgroundImage.transferTo(imageFile);
            Document document=Jsoup.parse(htmlFile,"UTF-8");
            String wordBody=document.body().html();

            QiniuZoneParameters qiniuZoneParameters = initConfig.getMainQiniuZone();
            String imageCDNFileName = CDNBLogBackgroundImagePath + simpleDateFormat.format(nowTime) + suffix;
            String imageLink = uploadFile.simpleUpload(imageFile.getAbsolutePath(), imageCDNFileName, qiniuZoneParameters);
            Date date = new Date(nowTime);
            String wordTextId=String.valueOf(nowTime);
            result = managerDao.insertBlog(title, 1, summary, writer, imageLink,  date, 1, (allowComment == null) || (allowComment == false) ? 0 : 1,wordTextId);
            if (result==1){
                managerDao.insertWordText(wordBody,wordTextId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (result == 1) {
            return true;
        }
        return false;
    }

    public Map fileUpload(MultipartFile file, String fileName) {
        if (file.isEmpty()) {
            return PackingResult.toWorngMap("未上传文件！");
        }
        long nowTime = System.currentTimeMillis();
        String fileRealName = file.getOriginalFilename();
        String suffix = fileRealName.substring(fileRealName.lastIndexOf("."));
        File loaclFile = new File(initConfig.getTempPath() + nowTime + suffix);
        String link;
        try {
            file.transferTo(loaclFile);
            QiniuZoneParameters qiniuZoneParameters = initConfig.getMainQiniuZone();
            fileName = CDNOtherPath + ((fileName == null) || (fileName.equals("")) ? String.valueOf(nowTime) + suffix : fileName + suffix);
            link = uploadFile.simpleUpload(loaclFile.getAbsolutePath(), fileName, qiniuZoneParameters);
        } catch (IOException e) {
            e.printStackTrace();
            return PackingResult.toWorngMap("io错误");
        }
        Map map = new HashMap();
        map.put("link", link);
        return PackingResult.toSuccessMap(map);
    }



}
