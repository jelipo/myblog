package init.initService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by cao on 2017/2/3.
 */
public class InitRedis {
    @Resource(name = "jedisPool")
    private JedisPool pool;

    public void init(){
        Jedis jedis=pool.getResource();
        if (!jedis.exists("commentIpLimit")){
            jedis.hset("commentIpLimit","","");
        }
    }
}
