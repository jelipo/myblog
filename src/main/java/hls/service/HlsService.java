package hls.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hls.service.connect.SwitchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.PackingResult;
import util.TimeTools;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service("hls/service/HlsService")
public class HlsService {


    @Value("#{config['hls.some']}")
    private String some;

    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Resource(name = "hls/service/connect/SwitchService")
    private SwitchService switchService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH时mm分ss秒");

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

            float biggerTime = Float.valueOf(firstMap.get("videoTime"));
            StringBuilder body = new StringBuilder();
            //先把列表第一个放入m3u8的Body中
            body.append("#EXTINF:").append(firstMap.get("videoTime")).append(",\n").append(firstMap.get("url") + "\n");
            //把列表剩下的一次装入Body中
            for (int i = list.size() - 2; i >= 0; i--) {
                Map<String, String> map = jedis.hgetAll(list.get(i));
                body.append("#EXTINF:").append(map.get("videoTime")).append(",\n").append(map.get("url") + "\n");
                float formatTime = Float.valueOf(map.get("videoTime"));
                biggerTime = formatTime > biggerTime ? formatTime : biggerTime;
            }
            result.replace(47, 49, String.valueOf(biggerTime));
            result.append(body);
            wirteStr = result.toString();
            //结果缓存4s
            jedis.set(m3u8CacheKey, wirteStr);
            jedis.expire(m3u8CacheKey, 4);
        }
        try {
            OutputStream os = response.getOutputStream();
            response.setHeader("Cache-Control", "max-age=4");
            response.setHeader("Connection", "keep-alive");
            response.setHeader("Content-Type", "application/vnd.apple.mpegurl");
            os.write(wirteStr.getBytes());
            jedis.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public JSONObject getSwitchResult(HttpServletRequest request) {
        Jedis jedis = this.pool.getResource();
        String ip = request.getRemoteAddr();
        String nowTime = TimeTools.getDefaultFormatTime();
        jedis.hset("switchLog", nowTime, ip);
        try {
            String switchFlagKey = "switch";
            int switchTime = 24;
            String value = jedis.get(switchFlagKey);
            if (value == null) {
                try {
                    this.switchService.sendMessage("switch");
                } catch (IOException e) {
                    return JSON.parseObject("{'resultCode':200,'data':'服务器内部错误！'}");
                }
                value = this.simpleDateFormat.format(System.currentTimeMillis());
                jedis.set(switchFlagKey, value);
                jedis.expire(switchFlagKey, switchTime);
                return JSON.parseObject("{'resultCode':200,'data':'您控制了开关！将在视频中" + value + "开/关'}");

            } else {
                return JSON.parseObject("{'resultCode':200,'data':'已经由别人抢先了！将在视频中" + value + "开/关'}");
            }
        } finally {
            jedis.close();
        }

    }

    public JSONObject nextSwitchTime() {
        Jedis jedis = this.pool.getResource();
        try {
            String switchFlagKey = "switch";
            String value = jedis.get(switchFlagKey);
            if (value == null) {
                return JSONObject.parseObject("{'resultCode':200,'data':'没有人控制，试试点下面的按钮'}");
            }
            return JSONObject.parseObject("{'resultCode':200,'data':'" + value + "'}");
        } finally {
            jedis.close();
        }
    }


}
