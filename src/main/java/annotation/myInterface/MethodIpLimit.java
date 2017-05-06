package annotation.myInterface;

import java.lang.annotation.*;

/**
 * Created by cao on 2017/5/6.
 * 方法级别的独立限制，表示如果被注解的方法超过一定次数，就触发限制
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodIpLimit {


    /**
     * 访问注解方法多少次时，触发ip限制
     * @return 返回int值，表示需要触发的次数
     */
    int tiggerTimes();

    /**
     * 触发ip限制时，所限制的时间，单位 秒。
     * @return 返回int值，表示触发后的限制的时间
     */
    int limitTime();

    /**
     * 触发长时间ip限制的次数,此处“次数”表示在一段时间内，被普通限制的次数超过一定“次数”时，则触发ip限制。
     * @return 返回boolean值，表示触发后的限制的时间
     */
    int longLimit() default 999;

}
