package corn.corn;

import corn.service.LocalFileListenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定时上传文件
 */
@Service("corn/corn/LocalFileListenCorn")
public class LocalFileListenCorn {

    @Resource(name = "corn/service/LocalFileListenService")
    private LocalFileListenService localFileListenService;

    private static final Logger logger = LogManager.getLogger(LocalFileListenCorn.class);

    @Scheduled(cron = "*/20 * * * * ?")
    public void check() {
        localFileListenService.checkRedisAndUpload();
    }

}
