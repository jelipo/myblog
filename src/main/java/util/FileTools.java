package util;

import com.qiniu.util.Etag;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by cao on 2017/1/16.
 */
public class FileTools {

    public static String getQiniuFileHash(File file){
        try {
            return Etag.file(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某个文件夹下所有File的List，不包括filderPath文件夹和所有子文件夹
     * @param floderPath 文件夹路径
     * @return
     */
    public static List<File> getAllFileListFromFloder(String floderPath){
        List list=new ArrayList();
        File floder=new File(floderPath);
        check(floder,list);
        return list;
    }
    private static void check(File file,List list){
        File[] files=file.listFiles();
        for(int i=0;i<files.length;i++){
            if (files[i].isDirectory()){
                check(files[i],list);
            }else {
                list.add(file);
            }
        }
    }

    public static Map getAllFileMapFromFloder(String floderPath){
        Map<String,Object> map=new HashMap();
        File floder=new File(floderPath);
        Map map1=check(floder);
        return map1;
    }
    private static Map check(File file){
        Map childMap=new HashMap();
        File[] files=file.listFiles();
        for(int i=0;i<files.length;i++){
            if (files[i].isDirectory()){
                childMap.put(files[i].getName(),check(files[i]));
            }else {
                childMap.put(files[i].getName(),files[i]);
            }
        }
        return childMap;
    }
}
