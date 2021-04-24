package hf.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hf.dao.OfficeSearchRepository;
import hf.domain.OfficerentData;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService{
	@Autowired
	private OfficeSearchRepository officesearchRepo;


	@Override
	public List<OfficerentData> findBy시군구(String 시군구) {
		return officesearchRepo.findBy시군구(시군구);
	}
	
	
}
