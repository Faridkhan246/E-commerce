package services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRespository repository;
	
	private final CustomerMapper mapper;
	
	public String createCustomer( CustomerRequest request) {
		var customer = repository.save(mapper.toCustomer(request));
		return customer.getId();
	}
	public void updateCustomer(CustomerRequest request) {
		var customer = repository.findById(request.id())
				.orElseThrow(() -> new CustomerNotFoundException(
					String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())	
						));
		mergeCustomer(customer,request);
		repository.save(customer);
		
	}
	private void mergeCustomer(Customer customer, CustomerRequest request) {
		if(StringUtils.isNotBlank(request.firstname())) {
			customer.setFirstname(request.firstname());
		}
		if(StringUtils.isNotBlank(request.lastname())) {
			customer.setFirstname(request.lastname());
		}
		if(StringUtils.isNotBlank(request.email())) {
			customer.setFirstname(request.email());
		}
		if(request.address()!=null) {
			customer.setAddress(request.address());
		}
	}
	public List<CustomerResponse> findAllCustomers() {
		return repository.findAll()
				.stream()
				.map(mapper::fromCustomer)
				.collect(Collectors.toList());
	}
	public Boolean  existsById(String customerId) {
		return repository.findById(customerId)
		.isPresent();
	}
	public CustomerResponse findById(String customerId) {
		return repository.findById(customerId)
				.map(mapper::fromCustomer)
				.orElseThrow(() ->new CustomerNotFoundException(format("No customer found with the provided ID:: %s", customerId)));
	}
	private String format(String string, String customerId) {//not in
		// TODO Auto-generated method stub
		return null;                                         //not in
	}
	public void deleteCustomer(String customerId) {
		repository.deleteById(customerId);
		
	}

}
