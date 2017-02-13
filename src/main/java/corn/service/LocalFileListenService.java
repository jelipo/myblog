package corn.service;

import com.qiniu.common.QiniuException;
import init.initService.InitConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import qiniu.CompareLocalAndCDN;
import qiniu.SimpleTools;
import qiniu.UploadFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


@Service("corn/service/LocalFileListenService")
public class LocalFileListenService {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name = "qiniu/UploadFile")
    private UploadFile uploadFile;

    @Resource(name = "jedisPool")
    private JedisPool pool;

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
        Iterator<String> replace = needToReplace.keySet().iterator();
        while (replace.hasNext()) {
            String key = replace.next();
            uploadFile.coverSimpleUpload(needToReplace.get(key), key, initConfig.getMainQiniuZone());
            jedis.hdel(needToReplaceHashKey, key);

        }
        jedis.close();
    }
}
