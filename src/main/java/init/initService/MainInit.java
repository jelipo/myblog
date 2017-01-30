package init.initService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by cao on 2017/1/15.
 */

@Service("init/initService/MainInit")
public class MainInit {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name = "init/initService/initQiniuCdn")
    private InitQiniuCdn initQiniuCdn;

    private static final Logger logger = LogManager.getLogger(MainInit.class);

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
