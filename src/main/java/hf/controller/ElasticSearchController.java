package hf.controller;

import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@CrossOrigin
@RestController
@Aspect
public class ElasticSearchController {

    @PostMapping("/prefixSearch")
    public Object prefixSearch(@RequestBody Map contents){
        RestTemplate restTemplate = new RestTemplate();
        String query = "{\"size\":0,\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"시군구\":\"" +
                (String) contents.get("city") +
                "\"}}]}},\"aggs\":{\"result\":{\"date_histogram\":{\"field\":\"@timestamp\",\"format\":\"yyyy-MM\",\"calendar_interval\":\"month\"},\"aggs\":{\"보증금\":{\"avg\":{\"field\":\"deposit\"}},\"면적\":{\"avg\":{\"field\":\"area\"}},\"translated\":{\"bucket_script\":{\"buckets_path\":{\"v1\":\"면적\",\"v2\":\"월세\",\"v3\":\"보증금\"},\"script\":\"(params.v2 + (params.v3 * 0.05 /12)) / params.v1 * 33\"}},\"월세\":{\"avg\":{\"field\":\"rent\"}}}}}}\n";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/" + (String) contents.get("homeType") + "-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }

    @PostMapping("/getAllCities")
    public Object getAllCities(@RequestBody Map contents){
        RestTemplate restTemplate = new RestTemplate();
        String query = "{\"size\":0,\"aggs\":{\"group_by_state\":{\"terms\":{\"size\":10000000,\"field\":\"시군구\"}}}}";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/" + (String) contents.get("homeType") + "-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }

    @PostMapping("/getTranslatedDataWithLocation")
    public Object getTranslatedDataWithLocation(@RequestBody Map contents){
        RestTemplate restTemplate = new RestTemplate();
        String query = "{\"size\":0,\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"시군구\":\"서울\"}},{\"match\":{\"전월세구분\":\"월세\"}}],\"filter\":[{\"range\":{\"yyyymmdd\":{\"gte\":20200000}}}]}},\"aggs\":{\"group_by_state\":{\"terms\":{\"size\":10000000,\"field\":\"시군구\"},\"aggs\":{\"면적\":{\"avg\":{\"field\":\"area\"}},\"보증금\":{\"avg\":{\"field\":\"deposit\"}},\"월세\":{\"avg\":{\"field\":\"rent\"}},\"location\":{\"geo_bounds\":{\"field\":\"location\"}},\"translated\":{\"bucket_script\":{\"buckets_path\":{\"v1\":\"면적\",\"v2\":\"월세\",\"v3\":\"보증금\"},\"script\":\"(params.v2 + params.v3 * 0.06 /12) / params.v1 * 33\"}}}}}}";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/" + (String) contents.get("homeType") + "-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }

    @PostMapping("/getChartData")
    public Object getChartData(@RequestBody Map contents){
        RestTemplate restTemplate = new RestTemplate();
        String query = "{\"size\":0,\"sort\":{\"@timestamp\":{\"order\":\"asc\"}},\"query\":{\"bool\":{\"must\":[{\"match\":{\"전월세구분\":\"월세\"}},{\"prefix\":{\"시군구\":{\"value\":\""+
                (String) contents.get("city")
                +"\"}}}]}},\"aggs\":{\"result\":{\"date_histogram\":{\"field\":\"@timestamp\",\"calendar_interval\":\"year\",\"format\":\"yyyy\"},\"aggs\":{\"면적\":{\"avg\":{\"field\":\"area\"}},\"보증금\":{\"avg\":{\"field\":\"deposit\"}},\"월세\":{\"avg\":{\"field\":\"rent\"}},\"10평당 월세\":{\"bucket_script\":{\"buckets_path\":{\"v1\":\"면적\",\"v2\":\"월세\"},\"script\":\"params.v2 / params.v1 * 33\"}},\"10평당 보증금\":{\"bucket_script\":{\"buckets_path\":{\"v1\":\"면적\",\"v2\":\"보증금\"},\"script\":\"params.v2 / params.v1 * 33\"}}}}}}";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/" + (String) contents.get("homeType") + "-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }

    @PostMapping("/searchQuery")
    public Object searchQuery(@RequestBody Map contents){
        RestTemplate restTemplate = new RestTemplate();
        String query = "{\"size\":1000,\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"시군구\":\""+
                contents.get("city")
                +"\"}},{\"match\":{\"전월세구분\":\""+
                contents.get("leaseType")
                +"\"}}]}}}\n";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/" + (String) contents.get("homeType") + "-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }

    @PostMapping("/searchCities")
    public Object searchCities(@RequestBody Map contents) {
        RestTemplate restTemplate = new RestTemplate();
        String query = "{\"size\":0,\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"시군구\":\""+
                (String) contents.get("city")
                +"\"}},{\"match\":{\"전월세구분\":\""+
                (String) contents.get("leaseType")
                +"\"}}],\"filter\":[{\"range\":{\"yyyymmdd\":{\"gte\":20200000}}},{\"geo_distance\":{\"distance\":\""+
                contents.get("distance")
                +"km\",\"location\":{\"lat\":" +
                contents.get("lat")
                + ",\"lon\":" +
                contents.get("lon")
                + "}}}]}},\"aggs\":{\"group_by_state\":{\"terms\":{\"size\":10000000,\"field\":\"시군구\"},\"aggs\":{\"location\":{\"geo_bounds\":{\"field\":\"location\"}},\"면적\":{\"avg\":{\"field\":\"area\"}},\"보증금\":{\"avg\":{\"field\":\"deposit\"}},\"월세\":{\"avg\":{\"field\":\"rent\"}},\"translated\":{\"bucket_script\":{\"buckets_path\":{\"v1\":\"면적\",\"v2\":\"월세\",\"v3\":\"보증금\"},\"script\":\"(params.v2 + params.v3 * 0.06 /12) / params.v1 * 33\"}},\"필터\":{\"bucket_selector\":{\"buckets_path\":{\"v1\":\"translated\"},\"script\":\""+
                contents.get("translatedMin")
                +" < params.v1 && params.v1 < "+
                contents.get("translatedMax")
                +"\"}}}}}}\n";
        JSONObject queryObject = new JSONObject(query);
        Object response = restTemplate.postForObject("http://localhost:9200/" + (String) contents.get("homeType") + "-rent-data/_search", queryObject.toMap(), Map.class);
        return response;
    }
}