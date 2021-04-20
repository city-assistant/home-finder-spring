package hf.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import hf.dao.UserInterest;

public interface UIRepository extends ElasticsearchRepository<UserInterest, String>{

}
