package hf;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import hf.domain.OfficerentData;
import hf.service.ElasticSearchService;

@SpringBootTest
class HomeFinderApplicationTests {

	@Autowired
	private ElasticSearchService elasticSearchService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Test
    void test() {
    	 List<OfficerentData> by시군구 = elasticSearchService.findBy시군구(OfficerentData.builder().시군구(get));
         assertThat(by시군구.size(),is(1));

    }
}
