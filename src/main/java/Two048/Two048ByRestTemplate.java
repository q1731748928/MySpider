package Two048;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @Author HeJie
 * @Date 2023/4/19
 * @Time 7:26
 */
public class Two048ByRestTemplate {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://bbs.zgogc.com/2048/thread.php?fid-15.html";
    }
}
