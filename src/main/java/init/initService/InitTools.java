package init.initService;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by cao on 2017/1/15.
 */

@Service("init/initService/initTools")
public class InitTools  {

    @PostConstruct
    public void init(){
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("notepad.exe src/net/xsoftlab/baike/RuntimeDemo.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
