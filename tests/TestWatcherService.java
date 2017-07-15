import init.initService.service.*;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * @author kencs@foxmail.com
 */
public class TestWatcherService {



    public static void main(String args[]) throws Exception {
        // 监控目录
        String rootDir = "G:\\IDEA\\MyBlog\\target\\MyBlog\\res";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(5);
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir),new FileFilterImpl());
//        FileAlterationObserver observer = new FileAlterationObserver(
//                rootDir,
//                FileFilterUtils.and(FileFilterUtils.fileFileFilter()),
//                new FileFilterImpl()
//        );
        observer.addListener(new MyFileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();

    }
}