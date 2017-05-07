package annotation.aop;

import annotation.myInterface.IpLimit;
import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    //拉入黑名单后的过期时间。秒为单位
    private int longBlackTime;
    //被临时限制的持续时间
    private int temporarilyLimitTime;

    @Resource(name = "jedisPool")
    private JedisPool pool;

    /**
     * 注解的具体实现方法，首先判断参数中是否有HttpServletRequest，RequestFacade类或者子类。没有返回null。
     * 从HttpServletRequest或者RequestFacade获取ip地址。然后根据ip判断是否被限制或者拉黑，是的话直接返回null。
     * 并更新redis的项具体数据。
     * 没有被限制或者拉黑，执行完方法，返回相应的结果。
     * @param pjp
     * @param ipLimit
     * @return
     * @throws Throwable
     */
    @Around(" @annotation(ipLimit)")
    public Object checkIp(ProceedingJoinPoint pjp, IpLimit ipLimit) throws Throwable {
        Jedis jedis = pool.getResource();
        try {
            //Class className = pjp.getSignature().getDeclaringType();
            Object[] args = pjp.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest||args[i] instanceof RequestFacade){
                    request=(HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) return null;
            String ip = request.getRemoteAddr();
            int blackTimes = getBlackTimes(ip, jedis);
            if (blackTimes > longBlackTimes) return null;
            if (isLimitIpAndUpdate(jedis, ip, ipLimit, blackTimes)) return null;
            //执行方法
            Object ob = pjp.proceed();
            return ob;
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据ip获得在单位时间内被临时限制的次数。
     *
     * @param ip
     * @param jedis
     * @return
     */
    private int getBlackTimes(String ip, Jedis jedis) {
        String blackIpValue = jedis.get("black_" + ip);
        int blackTimes = (blackIpValue == null) ? 0 : Integer.valueOf(blackIpValue);
        return blackTimes;
    }

    /**
     * 是否是被临时限制的ip，并更新缓存数据
     *
     * @return
     */
    private boolean isLimitIpAndUpdate(Jedis jedis, String ip, IpLimit ipLimit, int blackTimes) {
        Map<String, String> map = jedis.hgetAll("ipTimes_" + ip);
        int addTimes = ipLimit.value();
        if (map == null || map.size() == 0) {
            setIpTimes(jedis, ip, String.valueOf(addTimes), this.temporarilyLimitTime);
            return false;
        }
        int times = Integer.valueOf(map.get("times"));
        if (times >= totalValue) return true;
        times += addTimes;
        setIpTimes(jedis, ip, String.valueOf(times), this.temporarilyLimitTime);
        if (times >=totalValue) {
            setBlackTimes(jedis, ip, blackTimes + 1);
            return true;
        }
        return false;
    }

    /**
     * 向redis插入或者更新临时限制数据的方法。
     *
     * @param jedis
     * @param ip
     * @param times
     * @param expire
     */
    private void setIpTimes(Jedis jedis, String ip, String times, int expire) {
        jedis.hset("ipTimes_" + ip, "times", times);
        jedis.expire("ipTimes_" + ip, expire);
    }

    /**
     * 向redis插入或者更新黑名单数据的方法。
     * @param jedis
     * @param ip
     * @param blackTimes
     */
    private void setBlackTimes(Jedis jedis, String ip, int blackTimes) {
        jedis.set("black_" + ip, String.valueOf(blackTimes + 1));
        jedis.expire("black_" + ip,longBlackTime);
    }

    /**
     * hash
     * ipTimes_192.168.0.1:{ 过期时间5分钟
     * times:100;
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

    public void setLongBlackTime(int longBlackTime) {
        this.longBlackTime = longBlackTime;
    }
}
