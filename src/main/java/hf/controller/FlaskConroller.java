package hf.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Aspect
@CrossOrigin
@RestController
public class FlaskConroller {
    @PostMapping("/flask")
    public Object flask(@RequestBody Map contents) {
        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.postForObject("http://localhost:5000/", contents, Map.class);
        return response;
    }
}
