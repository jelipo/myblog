package redisServer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cao on 2017/2/2.
 */
@Service("redisServer/service/CommentLimit")
public class CommentLimit {




    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Value("#{config['commentLimit.banTime']}")
    private int limitTime ;

    public Boolean isBanIp(HttpServletRequest httpServletRequest) {
        Jedis jedis = pool.getResource();
        try {
            String creatTime = jedis.hget("commentIpLimit", httpServletRequest.getRemoteAddr());
            if (creatTime == null) {
                return false;
            }else {
                if((System.currentTimeMillis()-Long.valueOf(creatTime))/1000>limitTime){
                    long a=jedis.hset("commentIpLimit",httpServletRequest.getRemoteAddr(),String.valueOf(System.currentTimeMillis()));
                    return false;
                }else {
                    return true;
                }
            }
        }finally {
            jedis.close();
        }



    }

    public Boolean insertIp(HttpServletRequest httpServletRequest) {
        Jedis jedis = pool.getResource();
        jedis.hset("commentIpLimit",httpServletRequest.getRemoteAddr(),String.valueOf(System.currentTimeMillis()));
        jedis.close();
        return true;
    }


}
