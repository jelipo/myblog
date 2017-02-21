package init.initService.dao;

import org.springframework.stereotype.Repository;

@Repository("init/initService/dao/GetDataFromSqlDao")
public interface GetDataFromSqlDao {

    int getMessageNum(int none);

    int getWordNum(int none);
}
