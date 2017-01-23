package redisServer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by cao on 2017/1/13.
 */
@Service("redisServer/service/IpLimit")
public class IpLimit {

    @Value("#{config['ipLimit.temporarilyBlackIpTime']}")
    private int temporarilyBlackIpTime;

    @Value("#{config['ipLimit.longBlackIpTime']}")
    private int longBlackIpTime;

    @Value("#{config['ipLimit.ipLimitTimes']}")
    private int ipLimitTimes;

    @Value("#{config['ipLimit.ipBlackTimes']}")
    private int ipBlackTimes;

    /**
     * 用于判断请求的ip地址是否是被拉黑的ip地址
     * @param request 主要获取ip地址使用
     * @param addTimes  此次请求占用多少次次数
     * @return 返回Boolean值，true：是被拉黑的ip，false则不是
     */
    public Boolean isBlackIp(HttpServletRequest request, String addTimes) {
        String ip = request.getRemoteAddr();
        Jedis jedis = pool.getResource();
        try {
            String blackTimesStr = jedis.get("black_" + ip);
            int blackTimes = 0;
            if (blackTimesStr != null) {
                blackTimes = Integer.parseInt(blackTimesStr);
            }
            if (blackTimes >= ipBlackTimes) {
                return true;
            } else {
                long timeNow=System.currentTimeMillis();
                Map<String ,String> map=jedis.hgetAll("ipTimes_" + ip);
                String timesStr = map.get("times");
                if (map.size()==0) {
                    jedis.hset("ipTimes_" + ip, "times",addTimes);
                    jedis.hset("ipTimes_" + ip, "lastTime",String.valueOf(timeNow));
                    jedis.expire("ipTimes_" + ip, temporarilyBlackIpTime);
                    return false;
                } else {
                    int times = Integer.parseInt(timesStr);
                    if (times >= ipLimitTimes) {
                        return true;
                    } else {
                        int nowTimes = times + Integer.parseInt(addTimes);
                        if (nowTimes >= ipLimitTimes) {
                            jedis.set("blackTimes_" + ip, String.valueOf(blackTimes + 1));
                            jedis.expire("blackTimes_" + ip, longBlackIpTime);
                            jedis.hset("ipTimes_" + ip,"times",String.valueOf(nowTimes));
                            jedis.expire("ipTimes_" + ip,temporarilyBlackIpTime);
                            return true;
                        } else {
                            long lastTime=Long.valueOf(map.get("lastTime"));
                            int freeTime=temporarilyBlackIpTime-((int)(timeNow-lastTime)/1000);
                            if (freeTime>0){
                                jedis.hset("ipTimes_" + ip, "times",String.valueOf(nowTimes));
                                jedis.expire("ipTimes_" + ip, freeTime);
                            }
                            return false;
                        }
                    }
                }
            }

        } finally {
            jedis.close();
        }

    }

    /**
     * hash
     * ipTimes_192.168.0.1:{ 过期时间5分钟
     *     times:100;
     *     lastTime:15752150
     * }
     * String
     * blackTimes_192.168.0.1:1 过期时间24小时
     */

    @Resource(name = "jedisPool")
    private JedisPool pool;
}
