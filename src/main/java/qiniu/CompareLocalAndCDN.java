package qiniu;

import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Etag;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by cao on 2017/1/19.
 */
public class CompareLocalAndCDN {

    private Map<String, String> needToAdd;
    private Map<String, String> needToReplace;

    private String CDN_Prefix;
    private Map<String, Object> localFileMap;
    private Map<String, FileInfo> cdnFilesInfoMap;

    public CompareLocalAndCDN(Map<String, Object> localFileMap, Map<String, FileInfo> cdnFilesInfoMap, String CDN_Prefix) {
        this.needToAdd = new HashMap<>();
        this.needToReplace = new HashMap<>();

        this.localFileMap = localFileMap;
        this.cdnFilesInfoMap = cdnFilesInfoMap;
        this.CDN_Prefix = CDN_Prefix;

        try {
            compare(localFileMap, cdnFilesInfoMap, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, String> getNeedToAdd() {
        return needToAdd;
    }

    public Map<String, String> getNeedToReplace() {
        return needToReplace;
    }

    public Map<String,File> getNeedToDel(){

        return null;
    }

    private void compare(Map<String, Object> localFileMap, Map<String, FileInfo> cdnFilesInfoMap, String parentPath) throws IOException {
        Iterator<String> it = localFileMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object ob = localFileMap.get(key);
            if (ob instanceof File) {
                File file = (File) ob;
                String CDN_Path = CDN_Prefix + parentPath + file.getName();
                FileInfo fileInfo = cdnFilesInfoMap.get(CDN_Path);

                if (fileInfo == null) {
                    needToAdd.put(CDN_Path, file.getPath());
                } else {
                    String localHash = Etag.file((File) file);
                    String cdnHash = fileInfo.hash;
                    if (!localHash.equals(cdnHash)) {
                        needToReplace.put(CDN_Path, file.getPath());
                    } else {
                        //无需做更改的文件
                    }
                }
            } else {
                String lastParentPath = new String(parentPath);
                parentPath = parentPath + key + "/";
                compare((Map) ob, cdnFilesInfoMap, parentPath);
                parentPath = lastParentPath;
            }

        }
    }
}
