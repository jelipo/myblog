package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import init.initService.InitConfig;
import init.pojo.QiniuZoneParameters;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by cao on 2017/1/23.
 */

@Service("qiniu/UploadFile")
public class UploadFile {

    public String simpleUpload(String localPath, String CDNFileName, QiniuZoneParameters qiniuZoneParameters) throws IOException {
        UploadManager uploadManager = qiniuZoneParameters.getUploadManager();
        Auth auth = qiniuZoneParameters.getAuth();
        String bucketName = qiniuZoneParameters.getMainBucketName();
        upload(localPath, CDNFileName, uploadManager, auth.uploadToken(bucketName));
        return qiniuZoneParameters.getCdnDomainName()+CDNFileName;
    }

    public void simpleUploadByCustom(String localPath, String CDNFileName, UploadManager uploadManager, Auth auth, String bucketName) {
        upload(localPath, CDNFileName, uploadManager, auth.uploadToken(bucketName));
    }

    public void coverSimpleUpload(String localPath, String CDNFileName, QiniuZoneParameters qiniuZoneParameters) {
        UploadManager uploadManager = qiniuZoneParameters.getUploadManager();
        Auth auth = qiniuZoneParameters.getAuth();
        String bucketName = qiniuZoneParameters.getMainBucketName();
        upload(localPath, CDNFileName, uploadManager, auth.uploadToken(bucketName,CDNFileName));
    }

    private void upload(String localPath, String CDNFileName, UploadManager uploadManager, String uploadToken) {
        try {
            Response res = uploadManager.put(localPath, CDNFileName, uploadToken);
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
