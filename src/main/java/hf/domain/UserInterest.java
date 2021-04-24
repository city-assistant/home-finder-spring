package hf.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.elasticsearch.annotations.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class UserInterest {
	private @Id @GeneratedValue Long id;
	private @ManyToOne @JoinColumn(name="user_id")	Users userId;
	private @Field(name="city") String city;
	private @Field(name="address") String address;
	private @Field(name="note") String note;
}
