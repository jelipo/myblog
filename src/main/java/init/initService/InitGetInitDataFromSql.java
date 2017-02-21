package init.initService;

import init.initService.dao.GetDataFromSqlDao;
import init.initService.service.MyServletContex;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

@Service("init/initService/service/InitGetInitDataFromSql")
public class InitGetInitDataFromSql {

    @Resource(name="init/initService/service/MyServletContex")
    private MyServletContex myServletContex;

    @Resource(name = "init/initService/dao/GetDataFromSqlDao")
    private GetDataFromSqlDao getDataFromSqlDao;

    public void init(){
        ServletContext servletContext=myServletContex.getServletContext();
        servletContext.setAttribute("wordNum",getDataFromSqlDao.getWordNum(0));
        servletContext.setAttribute("messageNum",getDataFromSqlDao.getMessageNum(0));

    }
}
