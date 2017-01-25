package init.initService;

import com.qiniu.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import qiniu.CompareLocalAndCDN;
import qiniu.SimpleTools;
import qiniu.UploadFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.FileTools;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cao on 2017/1/16.
 */
@Service("init/initService/initQiniuCdn")
public class InitQiniuCdn {

    private Boolean uploadFlag=false;
    private Map<String, String> needToAdd;
    private  Map<String, String> needToReplace;

    public void init() {
        String resPath = initConfig.getRootPath() + "res";
        Map localFileMap = FileTools.getAllFileMapFromFloder(resPath);
        FileInfo[] cdnFilesInfList;
        try {
            cdnFilesInfList = simpleTools.getCdnFileList(bucketName, null);
        }catch (Exception e){
            cdnFilesInfList = simpleTools.getCdnFileList(bucketName, null);
        }

        Map<String, FileInfo> cdnFilesInfoMap = SimpleTools.FileInfoArray2MapByKey(cdnFilesInfList);
        String CDN_Prefix = initConfig.getMainQiniuZone().getCDN_Prefix();
        CompareLocalAndCDN.CompareLocalAndCDNResult compareLocalAndCDNResult = compareLocalAndCDN.getCompareResult(localFileMap, cdnFilesInfoMap, CDN_Prefix);
        needToAdd = compareLocalAndCDNResult.getNeedToAdd();
        needToReplace = compareLocalAndCDNResult.getNeedToReplace();

        Jedis jedis = pool.getResource();
        intoJedis(needToAdd, needToReplace, jedis);

        uploadFlag=true;
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void dosomething() throws IOException {
        Iterator<String> iterator=needToAdd.keySet().iterator();
        while (iterator.hasNext()){
            if (uploadFlag){
                String key=iterator.next();
                uploadFile.simpleUpload(needToAdd.get(key),key,initConfig.getMainQiniuZone());
            }
        }



        uploadFlag=false;
    }

    private void intoJedis(Map<String, String> needToAdd, Map<String, String> needToReplace, Jedis jedis) {
        if (needToAdd.size()!=0){
            String needToAddKey = initConfig.getStartId() + "_needToAddHash";
            String re = jedis.hmset(needToAddKey, needToAdd);
            jedis.expire(needToAddKey, 86400);
        }
        if (needToReplace.size()!=0){
            String needToReplaceKey = initConfig.getStartId() + "_needToReplaceHash";
            String re1 = jedis.hmset(needToReplaceKey, needToReplace);
            jedis.expire(needToReplaceKey, 86400);
        }

    }

    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Value("#{config['qiniu.bucketName']}")
    private String bucketName;

    @Resource(name = "qiniu/SimpleTools")
    private SimpleTools simpleTools;

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name="qiniu/UploadFile")
    private UploadFile uploadFile;

    @Resource(name="qiniu/CompareLocalAndCDN")
    private CompareLocalAndCDN compareLocalAndCDN;
}
