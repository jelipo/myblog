package annotation.myInterface;

import java.lang.annotation.*;

/**
 * 对全项目使用此注解设置ip监控，所有用此注解的方法，超过一定数值限制，所有方法的权重指数都会叠加，对所有用此注解的方法都会限制。
 * 例如，对方法a设置权重为50，方法b设置权重为100，总数值限制为500，如果一定时间内 访问a方法 4次，b方法3次，
 * 则到达总数值最大限制，一段时间内将无法访问a、b和其他使用此注解方法。时间和总数值在配置文件设置。
 *
 * 注意：使用本注解的方法必须有HttpServletRequest类型参数，需要用此对象获取ip地址
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpLimit {

    /**
     * 权重指数。
     * @return
     */
    int value() ;



}
