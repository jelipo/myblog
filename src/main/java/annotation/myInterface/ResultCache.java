package annotation.myInterface;

import java.lang.annotation.*;

/**
 * 对方法的结果进行缓存，value值代表缓存的时间，单位 （秒）
 * 被此注解的方法必须有返回值，并且返回值必须实现了Serializable接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultCache {

    /**
     * 对结果缓存的缓存时间
     * @return
     */
    int value();
}
