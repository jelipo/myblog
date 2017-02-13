package init.initService.service;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by cao on 2017/2/12.
 */
public class FileFilterImpl implements FileFilter {

    /*
     * (non-Javadoc)
     * @see java.io.FileFilter#accept(java.io.File)
     *
     * return true:返回所有目录下所有文件详细(包含所有子目录);return false:返回主目录下所有文件详细(不包含所有子目录)
     */
    @Override
    public boolean accept(File pathname) {

        return true;
    }
}
