package init.initService;

import com.qiniu.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import qiniu.CompareLocalAndCDN;
import qiniu.SimpleTools;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.FileTools;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service("init/initService/initQiniuCdn")
public class InitQiniuCdn {


    /**
     * 需要初始化之一，对比本地和CDN，并将比对结果放入Redis中，然后由定时任务实际上传到CDN
     * 此项目中只检测res文件夹的文件
     */
    public void init() {
        String resPath = initConfig.getRootPath() + "res/";
        Map localFileMap = FileTools.getAllFileMapFromFloder(resPath);
        FileInfo[] cdnFilesInfList;
        try {
            cdnFilesInfList = simpleTools.getCdnFileList(bucketName, null);
        } catch (Exception e) {
            cdnFilesInfList = simpleTools.getCdnFileList(bucketName, null);
        }
        Map<String, FileInfo> cdnFilesInfoMap = SimpleTools.FileInfoArray2MapByKey(cdnFilesInfList);
        String CDN_Prefix = initConfig.getMainQiniuZone().getCDN_Prefix();
        CompareLocalAndCDN.CompareLocalAndCDNResult compareLocalAndCDNResult = compareLocalAndCDN.getCompareResult(localFileMap, cdnFilesInfoMap, CDN_Prefix);
        intoRedis(compareLocalAndCDNResult.getNeedToAdd(),compareLocalAndCDNResult.getNeedToReplace());
    }


    /**
     * 此方法把需要添加和需要替换的条目放到Redis中
     * hash的名称 startID_(startId)_needToAddHash/needToReplaceHash
     * 例如startID_1486897618157_needToAddHash
     * hash的有效时间为24小时
     * 添加到redis中，key值为将要添加到CDN的文件名，value值为本地文件的实际路径
     *
     * @param needToAdd 需要添加到cdn的文件map
     * @param needToReplace 需要替换的文件map
     */
    public void intoRedis(Map<String, String> needToAdd, Map<String, String> needToReplace) {
        Jedis jedis=pool.getResource();
        if (needToAdd!=null){
            if (needToAdd.size()!=0){
                String needToAddKey = initConfig.getStartId() + "_needToAddHash";
                String re = jedis.hmset(needToAddKey, needToAdd);
                jedis.expire(needToAddKey, 86400);
            }
        }
        if (needToReplace!=null) {
            if (needToReplace.size() != 0) {
                String needToReplaceKey = initConfig.getStartId() + "_needToReplaceHash";
                String re1 = jedis.hmset(needToReplaceKey, needToReplace);
                jedis.expire(needToReplaceKey, 86400);
            }
        }
        jedis.close();
    }


    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Value("#{config['qiniu.bucketName']}")
    private String bucketName;

    @Resource(name = "qiniu/SimpleTools")
    private SimpleTools simpleTools;

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name="qiniu/CompareLocalAndCDN")
    private CompareLocalAndCDN compareLocalAndCDN;

}
