package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Customer;
import gr.codehub.teamOne.model.CustomerCategory;
import lombok.*;

import java.util.Date;

@Data
public class CustomerDTO {
    private String name;
    private String address;
    private Date dob;
    private CustomerCategory category;
    private String uri;

    public static Customer getCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setName(customerDTO.getName());
        customer.setDob(customerDTO.getDob());
        customer.setCategory(customerDTO.getCategory());
        return customer;
    }

    public static CustomerDTO getCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setName(customer.getName());
        customerDTO.setDob(customer.getDob());
        customerDTO.setCategory(customer.getCategory());
        customerDTO.setUri("http://localhost:9000/v1/customer/"+customer.getId());
        return customerDTO;
    }

}
