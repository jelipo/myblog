import static org.junit.Assert.*;

import com.qiniu.storage.model.FileInfo;
import init.initService.dao.GetDataFromSqlDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.annotation.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qiniu.SimpleTools;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springmvc-servlet.xml","classpath:spring-mybatis.xml"})
public class SpringTest {
    @Resource(name="init/initService/dao/GetDataFromSqlDao")
    private GetDataFromSqlDao getDataFromSqlDao;

    @Test
    public void testAddOpinion1() {
        getDataFromSqlDao.getMessageNum(1);
        int a=0;
    }

    @Test
    public void testAddOpinion2() {

        System.out.println(2);
    }
}
