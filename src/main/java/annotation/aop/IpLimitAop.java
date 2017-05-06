package annotation.aop;

import annotation.myInterface.IpLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Aspect
public class IpLimitAop {

    //总数值
    private int totalValue;
    //触发长时间ip限制的次数
    private int longBlackTimes;

    //被临时限制的时间
    private int temporarilyLimitTime;

    @Resource(name = "jedisPool")
    private JedisPool pool;

    @Around(value = "@annotation(ipLimit)")
    public Object checkIp(ProceedingJoinPoint pjp, IpLimit ipLimit) throws Throwable {
        Jedis jedis = pool.getResource();
        Class className = pjp.getSignature().getDeclaringType();
        Object[] args = pjp.getArgs();
        HttpServletRequest request = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
            }
        }

        if (request == null) return null;
        String ip = request.getRemoteAddr();
        int blackTimes = getBlackTimes(ip, jedis);
        if (blackTimes > longBlackTimes) return null;
        if (isLimitIpAndUpdate(jedis, ip, ipLimit, longBlackTimes)) return null;
        Object ob = pjp.proceed();
        return ob;
    }

    /**
     * 是否是黑名单中的ip
     *
     * @param ip
     * @param jedis
     * @return
     */
    private int getBlackTimes(String ip, Jedis jedis) {
        String blackIpValue = jedis.get("black_" + ip);
        int blackTimes = (blackIpValue != null) ? Integer.valueOf(blackIpValue) : 0;
        return blackTimes;
    }

    /**
     * 是否是被限制的ip
     *
     * @return
     */
    private boolean isLimitIpAndUpdate(Jedis jedis, String ip, IpLimit ipLimit, int longBlackTimes) {
        Map<String, String> map = jedis.hgetAll("ipTimes_" + ip);
        int addTimes = ipLimit.value();
        if (map == null || map.size() == 0) {
            setIpTimes(jedis, ip, String.valueOf(addTimes), this.temporarilyLimitTime);
            return false;
        }
        int times = Integer.valueOf(map.get("times"));
        if (times > totalValue) {
            return true;
        }
        times += addTimes;
        setIpTimes(jedis, ip, String.valueOf(times), this.temporarilyLimitTime);
        if (times > totalValue) {
            jedis.set("black_" + ip, String.valueOf(longBlackTimes + 1));
            return true;
        }
        return false;
    }

    private void setIpTimes(Jedis jedis, String ip, String times, int expire) {
        jedis.hset("ipTimes_" + ip, "times", times);
        jedis.expire("ipTimes_" + ip, expire);
    }

    /**
     * hash
     * ipTimes_192.168.0.1:{ 过期时间5分钟
     * times:100;
     * lastTime:15752150
     * }
     * String
     * blackTimes_192.168.0.1:1 过期时间24小时
     */

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public void setLongBlackTimes(int longBlackTimes) {
        this.longBlackTimes = longBlackTimes;
    }

    public void setTemporarilyLimitTime(int temporarilyLimitTime) {
        this.temporarilyLimitTime = temporarilyLimitTime;
    }
}
