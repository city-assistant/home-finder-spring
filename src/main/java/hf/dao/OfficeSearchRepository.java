package hf.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import hf.domain.OfficerentData;

public interface OfficeSearchRepository extends ElasticsearchRepository<OfficerentData, String>{
	List<OfficerentData> findBy시군구(String 시군구);
}
