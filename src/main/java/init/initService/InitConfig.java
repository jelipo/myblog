package init.initService;

import com.qiniu.util.Auth;
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

    @Value("#{config['qiniu.ACCESS_KEY']}")
    private String ACCESS_KEY;

    @Value("#{config['qiniu.SECRET_KEY']}")
    private String SECRET_KEY;

    void init(){
        long startTime = System.currentTimeMillis();
        startId="startID_" + startTime;
        auth=Auth.create(ACCESS_KEY,SECRET_KEY);
        String currentPath = getClass().getResource(".").getFile().toString();
        rootPath = currentPath.split("WEB-INF")[0].replace("\\","/");
        if (rootPath.charAt(0)=='/'){
            rootPath=rootPath.substring(1);
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

}
