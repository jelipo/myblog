package annotation.aop;

import annotation.myInterface.ResultCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;

/**
 * Created by cao on 2017/5/11.
 */
@Component
@Aspect
public class ResultCacheAop {

    private static ByteArrayOutputStream bai;
    private static ObjectOutputStream obi;

    @Resource(name = "jedisPool")
    private JedisPool pool;


    @Around("@annotation(resultCache)")
    public Object cache(ProceedingJoinPoint pjp, ResultCache resultCache) throws Throwable {
        Jedis jedis = pool.getResource();
        try {
            String className = pjp.getSignature().getDeclaringType().toString();
            Object object = getResultCache(className, jedis);
            if (object == null) {
                object = pjp.proceed();
                updateResultCache(object, className, jedis, resultCache.value());
            }
            return object;
        } finally {
            jedis.close();
        }
    }


    /**
     * 从redis中获取缓存的对象的字节数组，并将其序列化为对象返回。
     *
     * @param className
     * @param jedis
     * @return
     */
    private Object getResultCache(String className, Jedis jedis) {
        byte[] resultByte = jedis.get(className.getBytes());
        if (resultByte == null || resultByte.length == 0) return null;
        Object obj = null;
        try {
            obj = byteToObject(resultByte);
        } catch (IOException e) {
            System.out.println("序列化失败,详细： " + className);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("序列化失败，没有找到类 ，详细 ：" + className);
            e.printStackTrace();
        }
        return obj;
    }

    private void updateResultCache(Object obj, String className, Jedis jedis, int cacheTime) {
        try {
            byte[] byt = objectToByte(obj);
            jedis.set(className.getBytes(), byt);
            jedis.expire(className.getBytes(), cacheTime);
        } catch (IOException e) {
            System.out.println("将对象序列化为数组失败，详细：" + className);
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException {
        this.bai = new ByteArrayOutputStream();
        this.obi = new ObjectOutputStream(bai);
    }

    private byte[] objectToByte(Object obj) throws IOException {
        this.obi.writeObject(obj);
        byte[] byt = bai.toByteArray();
        return byt;
    }

    private Object byteToObject(byte[] byt) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byt);
        ObjectInputStream oii = new ObjectInputStream(bis);
        Object obj = oii.readObject();
        return obj;
    }

}


