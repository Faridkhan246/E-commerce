package services;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor 
@Builder
@Getter
@Setter
@Validated 
public class Address {
	
	private String street;
	private String houseNumber;
	private String ZipCode;

}
