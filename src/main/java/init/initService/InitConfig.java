package init.initService;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import init.pojo.MainQiniuInitPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by cao on 2017/1/15.
 */

@Service("init/initService/initConfig")
public class InitConfig {

    private String startId;
    private Auth auth;
    private String rootPath;
    private MainQiniuInitPojo mainQiniuInitPojo;

    @Value("#{config['qiniu.ACCESS_KEY']}")
    private String ACCESS_KEY;

    @Value("#{config['qiniu.SECRET_KEY']}")
    private String SECRET_KEY;

    @Value("#{config['qiniu.bucketName']}")
    private String mainBucketName;

    void init() {
        long startTime = System.currentTimeMillis();
        startId = "startID_" + startTime;

        String currentPath = getClass().getResource(".").getFile().toString();
        rootPath = currentPath.split("WEB-INF")[0].replace("\\", "/");
        if (rootPath.charAt(0) == '/') {
            rootPath = rootPath.substring(1);
        }
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        mainQiniuInitPojo = new MainQiniuInitPojo();
        mainQiniuInitPojo.setAuth(auth);
        mainQiniuInitPojo.setMainBucketName(mainBucketName);
        Configuration c = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(c);
        mainQiniuInitPojo.setUploadManager(uploadManager);

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

    public MainQiniuInitPojo getMainQiniuInitPojo(){
        return mainQiniuInitPojo;
    }
}
