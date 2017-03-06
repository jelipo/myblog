package hls.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.PackingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hls/service/HlsService")
public class HlsService {


    @Value("#{config['hls.some']}")
    private String some;

    @Resource(name = "jedisPool")
    private JedisPool pool;

    /**
     * 将上传来的参数存入redis中
     *
     * @param fileUrl   cdn的url
     * @param fileName  文件的名称
     * @param some      其它
     * @param videoTime ts文件视频时间
     * @return 返回结果（Map）
     */
    public Map putM3u8Parm(String fileUrl, String fileName, String some, String videoTime, String num, String formatTime) {
        if (!some.equals(this.some)) {
            return PackingResult.toWorngMap("不允许操作！");
        }
        Jedis jedis = pool.getResource();
        String key = "hls_ts_" + fileName;
        jedis.lpush("hls_tsList", key);
        jedis.expire("hls_tsList", 60);
        jedis.hset(key, "url", fileUrl);
        jedis.hset(key, "videoTime", videoTime);
        jedis.hset(key, "fileName", fileName);
        jedis.hset(key, "num", num);
        jedis.hset(key, "formatTime", formatTime);
        jedis.expire(key, 70);
        jedis.close();
        return PackingResult.toSuccessMap();
    }


    /**
     * 从redis中取得内容处理，并向客户端返回m3u8文件
     * 读取2个最近的key的内容
     *
     * @return
     */
    public void getM3u8(HttpServletResponse response) {
        String m3u8CacheKey = "m3u8Cache";
        Jedis jedis = pool.getResource();
        String wirteStr = jedis.get(m3u8CacheKey);
        //如果获取的缓存的值为null，执行if中内容
        if (wirteStr == null) {
            List<String> list = jedis.lrange("hls_tsList", 0, 1);
            if (list.size() == 0) {
                jedis.close();
                return;
            }
            String firstKey = list.get(list.size() - 1);
            Map<String, String> firstMap = jedis.hgetAll(firstKey);
            StringBuilder result = new StringBuilder("#EXTM3U\n#EXT-X-VERSION:3\n#EXT-X-TARGETDURATION:17\n" +
                    "#EXT-X-MEDIA-SEQUENCE:" + firstMap.get("fileName").replace(".ts", "") + "\n");

            int biggerTime = Integer.valueOf(firstMap.get("formatTime"));
            StringBuilder body = new StringBuilder();
            //先把列表第一个放入m3u8的Body中
            body.append("#EXTINF:").append(firstMap.get("videoTime")).append(",\n").append(firstMap.get("url") + "\n");
            //把列表剩下的一次装入Body中
            for (int i = 1; i < list.size(); i++) {
                Map<String, String> map = jedis.hgetAll(list.get(i));
                body.append("#EXTINF:").append(map.get("videoTime")).append(",\n").append(map.get("url") + "\n");
                int formatTime = Integer.valueOf(map.get("formatTime"));
                biggerTime = formatTime > biggerTime ? formatTime : biggerTime;
            }
            result.replace(47, 49, String.valueOf(biggerTime));
            result.append(body);
            wirteStr=result.toString();
            //结果缓存4s
            jedis.set(m3u8CacheKey,wirteStr);
            jedis.expire(m3u8CacheKey,4);
        }
        try {
            OutputStream os = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=1.m3u8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Max-Age", "2592000");
            os.write(wirteStr.getBytes());
            jedis.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
