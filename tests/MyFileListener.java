import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * Created by cao on 2017/2/12.
 */
public class MyFileListener extends FileAlterationListenerAdaptor {
    @Override
    public void onFileCreate(File file) {
        System.out.println("[新建]:" + file.getAbsolutePath());
    }
    @Override
    public void onFileChange(File file) {
        System.out.println("[修改]:" + file.getAbsolutePath());
    }
    @Override
    public void onFileDelete(File file) {
        System.out.println("[删除]:" + file.getAbsolutePath());
    }
}
