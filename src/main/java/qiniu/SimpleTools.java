package qiniu;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import init.initService.InitConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cao on 2017/1/15.
 */

@Service("qiniu/SimpleTools")
public class SimpleTools {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    public FileInfo[] getCdnFileList(String bucketName, String prefix) {
        Auth auth = initConfig.getAuth();

        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);
        //要测试的空间和key，并且这个key在你空间中存在
        try {
            //调用listFiles方法列举指定空间的指定文件
            //参数一：bucket    空间名
            //参数二：prefix    文件名前缀
            //参数三：marker    上一次获取文件列表时返回的 marker
            //参数四：limit     每次迭代的长度限制，最大1000，推荐值 100
            //参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串

            FileListing fileListing = bucketManager.listFiles(bucketName, prefix, null, 100, null);
            FileInfo[] items = fileListing.items;
            return items;
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println("异常"+r.toString());
        }
        return null;
    }

    public FileInfo[] getCdnFileListByAuth(String bucketName, String prefix, String a, String b) throws QiniuException {
        Auth auth = Auth.create(a, b);

        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);
        //要测试的空间和key，并且这个key在你空间中存在

        //调用listFiles方法列举指定空间的指定文件
        //参数一：bucket    空间名
        //参数二：prefix    文件名前缀
        //参数三：marker    上一次获取文件列表时返回的 marker
        //参数四：limit     每次迭代的长度限制，最大1000，推荐值 100
        //参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串

        FileListing fileListing = bucketManager.listFiles(bucketName, prefix, null, 100, null);
        FileInfo[] items = fileListing.items;
        return items;

    }

    public static Map FileInfoArray2MapByKey(FileInfo[] fileInfos) {
        Map<String, FileInfo> map = new HashMap<>();
        for (int i = 0; i < fileInfos.length; i++) {
            map.put(fileInfos[i].key, fileInfos[i]);
        }
        return map;
    }

    public static void compareLocalAndCDN(Map<String,Object> localFileMap,Map<String,FileInfo> cdnFilesInfoMap,String parentPath,String SPath) throws IOException {

    }
}
