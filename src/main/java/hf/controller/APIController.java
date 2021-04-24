package hf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
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

//        JsonParser parser;
//        parser.parseMap()


        Object response = restTemplate.postForObject("http://localhost:9200/officetel-rent-data/_search", contents, Map.class);
        return response;
    }
}