package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import init.pojo.QiniuZoneParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by cao on 2017/1/23.
 */

@Service("qiniu/UploadFile")
public class UploadFile {
    private static final Logger logger = LogManager.getLogger(UploadFile.class);

    public String simpleUpload(String localPath, String CDNFileName, QiniuZoneParameters qiniuZoneParameters) {
        UploadManager uploadManager = qiniuZoneParameters.getUploadManager();
        Auth auth = qiniuZoneParameters.getAuth();
        String bucketName = qiniuZoneParameters.getMainBucketName();
        upload(localPath, CDNFileName, uploadManager, auth.uploadToken(bucketName));
        return qiniuZoneParameters.getCdnDomainName() + CDNFileName;
    }

    public void simpleUploadByCustom(String localPath, String CDNFileName, UploadManager uploadManager, Auth auth, String bucketName) {
        upload(localPath, CDNFileName, uploadManager, auth.uploadToken(bucketName));
    }

    public String coverSimpleUpload(String localPath, String CDNFileName, QiniuZoneParameters qiniuZoneParameters) {
        UploadManager uploadManager = qiniuZoneParameters.getUploadManager();
        Auth auth = qiniuZoneParameters.getAuth();
        String bucketName = qiniuZoneParameters.getMainBucketName();
        upload(localPath, CDNFileName, uploadManager, auth.uploadToken(bucketName, CDNFileName));
        return qiniuZoneParameters.getCdnDomainName() + CDNFileName;
    }

    private void upload(String localPath, String CDNFileName, UploadManager uploadManager, String uploadToken) {
        File file =new File(localPath);
        if (file.exists()){
            Response res = null;
            try {
                res = uploadManager.put(localPath, CDNFileName, uploadToken);
                Boolean isSuccess = res.isJson();
                if (isSuccess) {
                    System.out.println(new String(res.body()));
                }
            } catch (QiniuException e) {
                logger.error("上传失败,fileName:"+CDNFileName+",localPath:"+localPath);

            }
        }else {
            logger.error("本地文件不存在！ fileName:"+CDNFileName+",localPath:"+localPath);
        }
    }

}
