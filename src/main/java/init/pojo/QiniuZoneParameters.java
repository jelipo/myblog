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
    private String ACCESS_KEY;
    private String SECRET_KEY;

    public QiniuZoneParameters(UploadManager uploadManager, String mainBucketName, Auth auth, String CDN_Prefix,String cdnDomainName,String ACCESS_KEY,String SECRET_KEY){
        this.uploadManager=uploadManager;
        this.mainBucketName=mainBucketName;
        this.auth=auth;
        this.CDN_Prefix=CDN_Prefix;
        this.cdnDomainName=cdnDomainName;
        this.ACCESS_KEY=ACCESS_KEY;
        this.SECRET_KEY=SECRET_KEY;

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

    public String getACCESS_KEY() {
        return ACCESS_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }
}
