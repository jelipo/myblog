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

    private final String prefix="comment_limit_";

    public Boolean isBanIp(HttpServletRequest httpServletRequest) {
        Jedis jedis = pool.getResource();
        String ip=httpServletRequest.getRemoteAddr();
        try {
            String result=jedis.get(prefix+ip);
            if (result==null){
                return false;
            }else {
                return true;
            }
        }finally {
            jedis.close();
        }
    }

    public Boolean insertIp(HttpServletRequest httpServletRequest) {
        String ip=httpServletRequest.getRemoteAddr();
        Jedis jedis = pool.getResource();
        jedis.set(prefix+ip,"");
        jedis.expire(prefix+ip,limitTime);
        jedis.close();
        return true;
    }


}
