package services;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

public record CustomerResponse(
		
         String id,
		 
		 String firstname,
		 
		 String lastname,
		 
		 String email,
		 
		 Address address
		) {

}
