package init.initService;

import init.initService.service.FileFilterImpl;
import init.initService.service.FileListenService;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.TimeUnit;

@Service("init/initService/initFileListen")
public class InitFileListen {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;
    @Resource(name = "init/initService/initQiniuCdn")
    private InitQiniuCdn initQiniuCdn;
    public void init(){
        String listenDir = initConfig.getRootPath()+"res/";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(5);
        FileAlterationObserver observer = new FileAlterationObserver(new File(listenDir),new FileFilterImpl());
        String CDN_Prefix = initConfig.getMainQiniuZone().getCDN_Prefix();
        observer.addListener(new FileListenService(initQiniuCdn,listenDir,CDN_Prefix));
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
