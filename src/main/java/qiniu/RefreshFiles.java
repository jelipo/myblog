package qiniu;


import com.alibaba.fastjson.JSONObject;
import init.initService.InitConfig;
import init.initService.InitHttpClient;
import init.pojo.QiniuZoneParameters;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.List;

@Service("qiniu/RefreshFiles")
public class RefreshFiles {

    private static final Logger logger = LogManager.getLogger(RefreshFiles.class);

    @Resource(name = "init/initService/initConfig")
    private InitConfig initConfig;

    @Resource(name = "init/initService/initHttpClient")
    private InitHttpClient initHttpClient;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void refreshCDNFiles(List<String> list) {
        JSONObject json = new JSONObject();
        json.put("urls", list);
        send(json);
    }

    private void send(JSONObject json) {
        QiniuZoneParameters parameters = initConfig.getMainQiniuZone();
        String token = getToken(parameters.getACCESS_KEY(),parameters.getSECRET_KEY());
        //发送
        RequestBody requestBody = RequestBody.create(JSON, json.toJSONString());
        Request request = new Request.Builder()
                .url("http://fusion.qiniuapi.com/v2/tune/refresh")
                .post(requestBody)
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = this.initHttpClient.getHttpClient().newCall(request).execute();
            System.out.print(response.body().string());
        } catch (IOException e) {
            logger.error("上传错误");
        }
    }

    private String getToken(String ACCESS_KEY,String SECRET_KEY) {
        byte[] sign = new byte[0];
        try {
            sign = hmacSHA1Encrypt("/v2/tune/refresh\n", SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encodedSign = new String(Base64.encodeBase64(sign));
        String token="QBox "+ ACCESS_KEY + ":" +encodedSign;
        return token;
    }

    private static byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[] text = encryptText.getBytes();
        return mac.doFinal(text);
    }

}
