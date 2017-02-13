package init.initService;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import init.pojo.QiniuZoneParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by cao on 2017/1/15.
 */

@Service("init/initService/initConfig")
public class InitConfig {

    private String startId;
    private Auth auth;
    private String rootPath;
    private QiniuZoneParameters mainQiniuZone;
    private String tempPath;
    @Value("#{config['qiniu.ACCESS_KEY']}")
    private String ACCESS_KEY;

    @Value("#{config['qiniu.SECRET_KEY']}")
    private String SECRET_KEY;

    @Value("#{config['qiniu.bucketName']}")
    private String mainBucketName;

    @Value("#{config['qiniu.projectName']}")
    private String projectName;

    @Value("#{config['qiniu.localResourceFloder']}")
    private String localResourceFloder;

    @Value("#{config['qiniu.cdnDomainName']}")
    private String cdnDomainName;

    @Value("#{config['blog.tempWin']}")
    private String tempWin;

    @Value("#{config['blog.tempLinux']}")
    private String tempLinux;

    void init() {
        long startTime = System.currentTimeMillis();
        startId = "startID_" + startTime;
        rootPath = System.getProperty("web.root").replace("\\","/");
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration c = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(c);
        String CDN_Prefix=(new StringBuilder(projectName).append("/").append(localResourceFloder).append("/")).toString();
        mainQiniuZone = new QiniuZoneParameters(uploadManager,mainBucketName,auth,CDN_Prefix,cdnDomainName);

        String os =System.getProperties().getProperty("os.name");
        if (os.contains("win") || os.contains("Win")){
            this.tempPath=tempWin;
        }else{
            this.tempPath=tempLinux;
        }
    }

    public String getRootPath() {
        return rootPath;
    }

    public Auth getAuth() {
        return auth;
    }

    public String getStartId() {
        return startId;
    }

    public QiniuZoneParameters getMainQiniuZone() {
        return mainQiniuZone;
    }

    public String getTempPath(){
        return tempPath;
    }
}
