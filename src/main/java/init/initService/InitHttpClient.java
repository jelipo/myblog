package init.initService;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

@Service("init/initService/initHttpClient")
public class InitHttpClient {
    private OkHttpClient client;
    public void init(){
        client=new OkHttpClient();
    }

    public OkHttpClient getHttpClient(){
        return client;
    }
}
