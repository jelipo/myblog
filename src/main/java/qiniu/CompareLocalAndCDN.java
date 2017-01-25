package qiniu;

import com.qiniu.storage.model.FileInfo;
import org.springframework.stereotype.Service;
import util.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cao on 2017/1/19.
 */

@Service("qiniu/CompareLocalAndCDN")
public class CompareLocalAndCDN {

    public CompareLocalAndCDNResult getCompareResult(Map<String, Object> localFileMap, Map<String, FileInfo> cdnFilesInfoMap, String CDN_Prefix) {
        CompareLocalAndCDNResult compareLocalAndCDNResult = new CompareLocalAndCDNResult();
        try {
            compare(localFileMap, cdnFilesInfoMap, "", CDN_Prefix, compareLocalAndCDNResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compareLocalAndCDNResult;
    }

    private void compare(Map<String, Object> localFileMap, Map<String, FileInfo> cdnFilesInfoMap,
                         String parentPath, String CDN_Prefix, CompareLocalAndCDNResult compareLocalAndCDNResult) throws IOException {
        Iterator<String> it = localFileMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object ob = localFileMap.get(key);
            if (ob instanceof File) {
                File file = (File) ob;
                String CDN_Path = CDN_Prefix + parentPath + file.getName();
                FileInfo fileInfo = cdnFilesInfoMap.get(CDN_Path);
                if (fileInfo == null) {
                    compareLocalAndCDNResult.needToAdd.put(CDN_Path, file.getPath());
                } else {
                    String localHash = FileTools.getQiniuFileHash(file);
                    String cdnHash = fileInfo.hash;
                    if (!localHash.equals(cdnHash)) {
                        compareLocalAndCDNResult.needToReplace.put(CDN_Path, file.getPath());
                    } else {
                        //无需做更改的文件
                    }
                }
            } else {
                String lastParentPath = new String(parentPath);
                parentPath = parentPath + key + "/";
                compare((Map) ob, cdnFilesInfoMap, parentPath, CDN_Prefix,compareLocalAndCDNResult);
                parentPath = lastParentPath;
            }
        }
    }

    public class CompareLocalAndCDNResult {
        Map<String, String> needToAdd;
        Map<String, String> needToReplace;

        public CompareLocalAndCDNResult() {
            this.needToAdd = new HashMap<>();
            this.needToReplace = new HashMap<>();
        }

        public Map<String, String> getNeedToAdd() {
            return needToAdd;
        }

        public Map<String, String> getNeedToReplace() {
            return needToReplace;
        }
    }

}
