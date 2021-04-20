package hf.dao;



import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(indexName = "interest")
public class UserInterest {
	@Id
	private  String userId;
	private @Field(name="city") String city;
	private @Field(name="address") String address;
	private @Field(name="note") String note;
}
