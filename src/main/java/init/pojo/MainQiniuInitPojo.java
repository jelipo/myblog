package init.pojo;

import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * Created by cao on 2017/1/24.
 */

public class MainQiniuInitPojo {

    private UploadManager uploadManager;
    private String mainBucketName;
    private Auth auth;

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

    public String getMainBucketName() {
        return mainBucketName;
    }

    public void setMainBucketName(String mainBucketName) {
        this.mainBucketName = mainBucketName;
    }


    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
