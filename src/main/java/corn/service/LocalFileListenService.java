package corn.service;

import init.initService.InitConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import qiniu.RefreshFiles;
import qiniu.UploadFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service("corn/service/LocalFileListenService")
public class LocalFileListenService {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name = "qiniu/UploadFile")
    private UploadFile uploadFile;

    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Resource(name = "qiniu/RefreshFiles")
    private RefreshFiles refreshFiles;

    private static final Logger logger = LogManager.getLogger(LocalFileListenService.class);

    public void checkRedisAndUpload() {
        String startId = initConfig.getStartId();
        Jedis jedis = pool.getResource();
        String needToAddHashKey = startId + "_needToAddHash";
        String needToReplaceHashKey = startId + "_needToReplaceHash";
        Map<String, String> needToAdd = jedis.hgetAll(needToAddHashKey);
        Map<String, String> needToReplace = jedis.hgetAll(needToReplaceHashKey);
        Iterator<String> add = needToAdd.keySet().iterator();
        while (add.hasNext()) {
            String key = add.next();
            uploadFile.simpleUpload(needToAdd.get(key), key, initConfig.getMainQiniuZone());
            jedis.hdel(needToAddHashKey, key);
        }
        List<String> list=new ArrayList();
        Iterator<String> replace = needToReplace.keySet().iterator();
        while (replace.hasNext()) {
            String key = replace.next();
            String url=uploadFile.coverSimpleUpload(needToReplace.get(key), key, initConfig.getMainQiniuZone());
            list.add(url);
            jedis.hdel(needToReplaceHashKey, key);
        }
        if (needToReplace.size()!=0){
            refreshFiles.refreshCDNFiles(list);
        }
        jedis.close();
    }
}
