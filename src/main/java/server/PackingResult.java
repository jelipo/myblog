package server;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by cao on 2017/1/14.
 */

@Service("server/PackingResult")
public class PackingResult {

    public Map toSuccessMap(List list){
        Map map=new HashMap();
        map.put("resultCode",200);
        map.put("data",list);
        return map;
    }
    public Map toSuccessMap(Map map){
        Map map1=new HashMap();
        map.put("resultCode",200);
        map.put("data",map);
        return map1;
    }

    public Map toWorngMap(String detailed ){
        Map map=new HashMap();
        map.put("resultCode",500);
        map.put("wrong",detailed);
        return map;
    }


}
