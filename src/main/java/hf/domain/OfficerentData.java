package hf.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(indexName = "Office-rental-data")
public class OfficerentData {
	
	@Id
	private String id;
	
	@Field(type = FieldType.Text)
	private String area;
	
	@Field(type = FieldType.Text)
	private String 전월세구분;
	
	@Field(type = FieldType.Text)
	private String 시군구;
	
	@Field(type = FieldType.Long)
	private Long builtyear;
	
	@Field(type = FieldType.Long)
	private Long rent;
	
	@Field(type = FieldType.Double)
	private Double withoutRent;
	
	@Field(type = FieldType.Text)
	private String 도로명;
}
