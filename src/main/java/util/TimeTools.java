package util;

import java.text.SimpleDateFormat;

/**
 * Created by cao on 2017/5/3.
 */
public class TimeTools {

    private final static SimpleDateFormat defaultFormatTime = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");

    public static String getDefaultFormatTime() {
        long nowTime = System.currentTimeMillis();
        return defaultFormatTime.format(nowTime);
    }

}
