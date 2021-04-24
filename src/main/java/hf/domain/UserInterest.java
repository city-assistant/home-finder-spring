package hf.domain;



import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class UserInterest {
	private @Id @GeneratedValue Long id;
	private @ManyToOne @JoinColumn	Users userId;
	private @Field(name="city") String city;
	private @Field(name="address") String address;
	private @Field(name="note") String note;
}
