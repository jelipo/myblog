package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import init.initService.InitConfig;
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


    //要上传的空间
    String bucketname = "Bucket_Name";
    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Zone z = Zone.zone0();
    Configuration c = new Configuration(z);
    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public void simpleUploadByAuth(String localPath,String CDNFileName,Auth auth) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(localPath,CDNFileName, auth.uploadToken(bucketname));
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
