package hf.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class UserInterest {
	private @Id @Column(name="user_id", unique=true) String userId;
	private @Column(name="city") String city;
	private @Column(name="address") String address;
	private @Column(name="note") String note;
}
