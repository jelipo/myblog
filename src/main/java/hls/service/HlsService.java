package hls.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.PackingResult;

import javax.annotation.Resource;
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
    public Map putM3u8Parm(String fileUrl, String fileName, String some, String videoTime) {
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
        jedis.expire(key, 70);
        jedis.close();
        return PackingResult.toSuccessMap();
    }


    /**
     * 从redis中取得内容处理，并向客户端返回m3u8文件
     * 读取2个最近的key的内容
     * @return
     */
    public void getM3u8() {
        Jedis jedis = pool.getResource();
        List<String> list = jedis.lrange("hls_tsList", 0, 1);
        if (list.size() == 0) {
            return;
        }
        String firstKey = list.get(list.size() - 1);
        Map<String, String> firstMap = jedis.hgetAll(firstKey);
        String SEQUENCE = firstMap.get("fileName").replace("-", "").replace(".ts", "");
        StringBuilder result = new StringBuilder("#EXTM3U\n" +
                "#EXT-X-VERSION:3\n");
        result.append("#EXT-X-MEDIA-SEQUENCE:" + SEQUENCE + "\n").append("#EXT-X-TARGETDURATION:30\n");
        StringBuilder body = new StringBuilder("#EXTINF:" + firstMap.get("videoTime") + ",\n" + firstMap.get("fileName") + "\n");
        if (list.size()==2){
            Map<String ,String> secondMap=jedis.hgetAll(list.get(0));
            body.append("#EXTINF:" + secondMap.get("videoTime") + ",\n" + secondMap.get("fileName") + "\n");
        }
        result.append(body);
        System.out.println(result.toString());
    }

}
