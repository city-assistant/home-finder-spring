package hf.controller;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ElasticSearchController {

  private  ElasticsearchOperations elasticsearchOperations;
  
  
}