package init.initService;

import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Etag;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import qiniu.CompareLocalAndCDN;
import qiniu.SimpleTools;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.FileTools;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cao on 2017/1/16.
 */
@Service("init/initService/initQiniuCdn")
public class InitQiniuCdn {

    public void init() {

        String resPath = initConfig.getRootPath() + "res";

        Map localFileMap = FileTools.getAllFileMapFromFloder(resPath);
        FileInfo[] cdnFilesInfList = simpleTools.getCdnFileList("res-blog-springmarker-com", null);
        Map<String, FileInfo> cdnFilesInfoMap = SimpleTools.FileInfoArray2MapByKey(cdnFilesInfList);
        String CDN_Prefix = "blog/res/";
        CompareLocalAndCDN compareLocalAndCDN = new CompareLocalAndCDN(localFileMap, cdnFilesInfoMap, CDN_Prefix);

        Map<String, String> needToAdd = compareLocalAndCDN.getNeedToAdd();
        Map<String, String> needToReplace = compareLocalAndCDN.getNeedToReplace();

        Jedis jedis = pool.getResource();

        intoJedis(needToAdd, needToReplace, jedis);
        jedis.close();

        
    }

    private void intoJedis(Map<String, String> needToAdd, Map<String, String> needToReplace, Jedis jedis) {
        String needToAddKey = initConfig.getStartId() + "_needToAddHash";
        String re = jedis.hmset(needToAddKey, needToAdd);
        jedis.expire(needToAddKey, 86400);

        String needToReplaceKey = initConfig.getStartId() + "_needToReplaceHash";
        String re1 = jedis.hmset(needToReplaceKey, needToReplace);
        jedis.expire(needToReplaceKey, 86400);


    }


    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Value("#{config['qiniu.bucketName']}")
    private String bucketName;


    @Resource(name = "qiniu/SimpleTools")
    private SimpleTools simpleTools;

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;
}
