package blog.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BlogMainAop {

    @Before(value = "execution(* blog.ctrl.BlogMainCtrl.init(..))")
    public void som(){
        System.out.println("执行方法");
    }
}
