package init.initService;

import com.qiniu.util.Auth;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import qiniu.SimpleTools;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;

/**
 * Created by cao on 2017/1/15.
 */

@Service("init/initService/MainInit")
public class MainInit {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name = "init/initService/initQiniuCdn")
    private InitQiniuCdn initQiniuCdn;

    private static Logger logger = Logger.getLogger(MainInit.class);

    @PostConstruct
    public void initStart() {
        logger.info("配置信息初始化");
        initConfig.init();
        logger.info("配置信息初始化完成");

        logger.info("检验本地和CDN资源文件");
        initQiniuCdn.init();
        logger.info("检验完成，并开启定时上传任务");

    }




}
