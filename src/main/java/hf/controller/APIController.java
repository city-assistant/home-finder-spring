package hf.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@Slf4j
public class APIController {
    @PostMapping("/postTest")
    public Object postForEntity(@RequestBody Map contents){
        RestTemplate restTemplate = new RestTemplate();
        String queryInjector = "";
        queryInjector = (String) contents.get("city");
        String query = "{\"size\":0,\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"시군구\":\"" +
                queryInjector +
                "\"}}]}},\"aggs\":{\"result\":{\"date_histogram\":{\"field\":\"@timestamp\",\"format\":\"yyyy-MM\",\"calendar_interval\":\"month\"},\"aggs\":{\"보증금\":{\"avg\":{\"field\":\"deposit\"}},\"면적\":{\"avg\":{\"field\":\"area\"}},\"translated\":{\"bucket_script\":{\"buckets_path\":{\"v1\":\"면적\",\"v2\":\"월세\",\"v3\":\"보증금\"},\"script\":\"(params.v2 + (params.v3 * 0.05 /12)) / params.v1 * 33\"}},\"월세\":{\"avg\":{\"field\":\"rent\"}}}}}}\n";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/officetel-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }
}