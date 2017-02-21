package init.initService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Service("init/initService/service/MyServletContex")
public class MyServletContex implements ServletContextAware {

    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }

    public ServletContext getServletContext(){
        return servletContext;
    }
}
