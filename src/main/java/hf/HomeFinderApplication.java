package hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScans({@ComponentScan("hf.controller"),
				@ComponentScan("hf.logger"),
				@ComponentScan(basePackages = { "com.baeldung.spring.data.es.service" })})
@EnableJpaRepositories(basePackages="hf.dao")
@EntityScan("hf.domain")
public class HomeFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeFinderApplication.class, args);
    }

}
