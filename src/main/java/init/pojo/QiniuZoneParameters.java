package init.pojo;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * Created by cao on 2017/1/24.
 */

public class QiniuZoneParameters {

    private UploadManager uploadManager;
    private String mainBucketName;
    private Auth auth;
    private String CDN_Prefix;
    private String cdnDomainName;

    public QiniuZoneParameters(UploadManager uploadManager, String mainBucketName, Auth auth, String CDN_Prefix,String cdnDomainName){
        this.uploadManager=uploadManager;
        this.mainBucketName=mainBucketName;
        this.auth=auth;
        this.CDN_Prefix=CDN_Prefix;
        this.cdnDomainName=cdnDomainName;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public String getMainBucketName() {
        return mainBucketName;
    }

    public Auth getAuth() {
        return auth;
    }

    public String getCDN_Prefix() {
        return CDN_Prefix;
    }

    public String getCdnDomainName() {
        return cdnDomainName;
    }
}
