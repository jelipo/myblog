package init.initService.service;

import init.initService.InitQiniuCdn;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileListenService extends FileAlterationListenerAdaptor {

    private static final Logger logger = LogManager.getLogger(FileListenService.class);

    private InitQiniuCdn initQiniuCdn;
    private String rootPath;
    private String CDN_Prefix;

    public FileListenService(InitQiniuCdn initQiniuCdn, String rootPath, String CDN_Prefix) {
        this.initQiniuCdn = initQiniuCdn;
        this.rootPath = rootPath;
        this.CDN_Prefix = CDN_Prefix;
    }

    @Override
    public void onFileCreate(File file) {
        String localPath = file.getAbsolutePath();
        String key = CDN_Prefix + localPath.replace("\\", "/").replace(rootPath,"");
        Map<String, String> map = new HashMap();
        map.put(key, localPath);
        initQiniuCdn.intoRedis(map, null);
    }

    @Override
    public void onFileChange(File file) {
        String localPath = file.getAbsolutePath();
        String key = CDN_Prefix + localPath.replace("\\", "/").replace(rootPath,"");
        Map<String, String> map = new HashMap();
        map.put(key, localPath);
        initQiniuCdn.intoRedis(null, map);
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("删除："+file.getAbsoluteFile());
        //fileIntoRedis(file);
    }

}
