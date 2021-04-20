package hf;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import hf.dao.UserInterest;
import hf.elasticsearch.UIRepository;

@SpringBootTest
class HomeFinderApplicationTests {

	@Resource 
	UIRepository uiRepository;

    @Test
    void test() {
    	UserInterest userInterest = new UserInterest(); 
    	userInterest.setUserId("133");
    	userInterest.setCity("도시");
    	userInterest.setAddress("주소");
    	userInterest.setNote("노트");
    	uiRepository.save(userInterest);

    }
}
