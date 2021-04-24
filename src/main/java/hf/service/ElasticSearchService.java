package hf.service;

import java.util.List;

import hf.domain.OfficerentData;

public interface ElasticSearchService {

	List<OfficerentData> findBy시군구(String 시군구);

}
