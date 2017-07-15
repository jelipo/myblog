import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Etag;
import org.junit.Test;
import qiniu.SimpleTools;
import util.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cao on 2017/1/19.
 */
public class CDNTest {

    @Test
    public void init() throws IOException {
        SimpleTools simpleTools = new SimpleTools();
        String res = "G:/IDEA/MyBlog/src/main/webapp/";
        String resPath = res + "res";
        Map localFileMap = FileTools.getAllFileMapFromFloder(resPath);
        FileInfo[] cdnFilesInfList = simpleTools.getCdnFileListByAuth("res-blog-springmarker-com", null, "gj58boOVyhKtrJqmgjnMk5UPrbJ-3ISG33CzgX4A", "3SoI5bpmrYEQbo4dLPi1LIay-dFisUS8up0Ow1f6");
        Map<String, FileInfo> cdnFilesInfoMap = SimpleTools.FileInfoArray2MapByKey(cdnFilesInfList);
        String parentPath = "blog/res/";

        for (int i = 0; i < 5; i++) {
            long a=System.currentTimeMillis();
            out(localFileMap, cdnFilesInfoMap, parentPath, "");
            System.out.println(System.currentTimeMillis()-a);
        }


    }

    private void out(Map<String, Object> localFileMap, Map<String, FileInfo> cdnFilesInfoMap, String parentPath, String SPath) throws IOException {
        Iterator<String> it = localFileMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object ob = localFileMap.get(key);
            if (ob instanceof File) {
                String cdnFileName = parentPath + SPath + ((File) ob).getName();
                FileInfo fileInfo = cdnFilesInfoMap.get(cdnFileName);

                if (fileInfo == null) {
                    //添加cdn文件
                    System.out.println("添加" + cdnFileName +((File) ob).getCanonicalPath());

                } else {
                    String localHash = Etag.file((File) ob);
                    String cdnHash = fileInfo.hash;
                    if (!localHash.equals(cdnHash)) {
                        //替换cdn文件
                        System.out.println("替换" + cdnFileName);
                    } else {
                        System.out.println("无需更改" + cdnFileName);
                    }
                }

            } else {
                String lastPath=new String(SPath);
                SPath = SPath + key + "/";
                out((Map) ob, cdnFilesInfoMap, parentPath, SPath);
                SPath = lastPath;
            }

        }
    }
}
