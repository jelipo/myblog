package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import init.initService.InitConfig;
import init.pojo.MainQiniuInitPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by cao on 2017/1/23.
 */

@Service("qiniu/UploadFile")
public class UploadFile {

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;


    public void simpleUploadByDefault(String localPath,String CDNFileName) throws IOException {
        MainQiniuInitPojo mainQiniuInitPojo=initConfig.getMainQiniuInitPojo();
        UploadManager uploadManager=mainQiniuInitPojo.getUploadManager();
        Auth auth=mainQiniuInitPojo.getAuth();
        String bucketName=mainQiniuInitPojo.getMainBucketName();
        upload(localPath,CDNFileName,uploadManager,auth,bucketName);
    }

    public void simpleUploadByCustom(String localPath,String CDNFileName,UploadManager uploadManager,Auth auth,String bucketName){
        upload(localPath,CDNFileName,uploadManager,auth,bucketName);
    }

    private void upload(String localPath,String CDNFileName,UploadManager uploadManager,Auth auth,String bucketName){
        try {
            Response res = uploadManager.put(localPath,CDNFileName, auth.uploadToken(bucketName));
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {

            }
        }
    }
}
