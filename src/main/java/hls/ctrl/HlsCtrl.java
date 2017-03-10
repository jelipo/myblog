package hls.ctrl;

import com.alibaba.fastjson.JSONObject;
import hls.service.HlsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class HlsCtrl {

    @Resource(name = "hls/service/HlsService")
    private HlsService hlsService;

    @ResponseBody
    @PostMapping("m3u8Parm.do")
    public Map putM3u8Parm(@RequestParam("fileUrl") String fileUrl, @RequestParam("fileName") String fileName,@RequestParam("some") String some,
                            @RequestParam("videoTime") String videoTime, @RequestParam("num") String num,@RequestParam("formatTime") String formatTime) {
        Map result = hlsService.putM3u8Parm(fileUrl, fileName, some, videoTime, num,formatTime);
        return result;
    }


    @GetMapping("getM3u8.do")
    public void getM3u8(HttpServletResponse response) {
        hlsService.getM3u8(response);
    }

    @ResponseBody
    @GetMapping("switch.do")
    public JSONObject switchLED(){
        JSONObject result=hlsService.getSwitchResult();
        return result;
    }
}
