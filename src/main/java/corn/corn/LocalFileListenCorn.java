package corn.corn;

import corn.service.LocalFileListenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定时上传文件
 */
@Service("corn/corn/LocalFileListenCorn")
public class LocalFileListenCorn {

    @Resource(name = "corn/service/LocalFileListenService")
    private LocalFileListenService localFileListenService;

    private static final Logger logger = LogManager.getLogger(LocalFileListenCorn.class);

    @Scheduled(cron = "*/20 * * * * ?")
    public void check() {
        localFileListenService.checkRedisAndUpload();
    }
//corn表达式样例
//	每隔5秒执行一次：*/5 * * * * ?
//
//	每隔1分钟执行一次：0 */1 * * * ?
//
//	每天23点执行一次：0 0 23 * * ?
//
//	每天凌晨1点执行一次：0 0 1 * * ?
//
//	每月1号凌晨1点执行一次：0 0 1 1 * ?
//
//	每月最后一天23点执行一次：0 0 23 L * ?
//
//	每周星期天凌晨1点实行一次：0 0 1 ? * L
//
//	在26分、29分、33分执行一次：0 26,29,33 * * * ?
//
//	每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
//0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
//0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时
//0 0 12 ? * WED 表示每个星期三中午12点
//"0 0 12 * * ?" 每天中午12点触发
//"0 15 10 ? * *" 每天上午10:15触发
//"0 15 10 * * ?" 每天上午10:15触发
//"0 15 10 * * ? *" 每天上午10:15触发
//"0 15 10 * * ? 2005" 2005年的每天上午10:15触发
//"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
//"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
//"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
//"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
//"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
//"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
//"0 15 10 15 * ?" 每月15日上午10:15触发
//"0 15 10 L * ?" 每月最后一日的上午10:15触发
//"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
//"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
//"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
}
