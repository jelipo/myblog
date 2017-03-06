import org.junit.Test;

/**
 * Created by cao on 2017/3/6.
 */
public class Other {

    @Test
    public void test(){
        StringBuilder result = new StringBuilder("#EXTM3U\n" +
                "#EXT-X-VERSION:3\n" +
                "#EXT-X-TARGETDURATION:17\n" +
                "#EXT-X-MEDIA-SEQUENCE:01.ts\n");
        result =result.replace(47,49,"01.ts");
        System.out.println(result);
    }
}
