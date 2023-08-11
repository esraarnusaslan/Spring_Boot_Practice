package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;






    public void saveCustomer(Customer customer) {
        boolean isExistCustomer=customerRepository.existsByEmail(customer.getEmail());
        if(isExistCustomer){
            throw new ConflictException("Customer already exists by "+ customer.getEmail());
        }
        customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers() {
        List<Customer> customers=customerRepository.findAll();
        return customers;
    }


    public Customer getCustomerById(Long id) {
        Customer customer=customerRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Customer not found by id: "+id));
        return customer;
    }

    public void deleteCustomerById(Long id) {
        Customer customer=getCustomerById(id);//id yoksa daha once olusturulan methoddan dolayi exception firlatacak
        customerRepository.delete(customer);
    }

    public CustomerDTO getCustomerDTOById(Long id) {

        Customer customer=getCustomerById(id);
//
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());
//        customerDTO.setPhone(customer.getPhone());
//        customerDTO.setLastName(customer.getLastName());

        CustomerDTO customerDTO=new CustomerDTO(customer);//cons ile mapledik

        return customerDTO;
    }


    public void updateCustomer(Long id, CustomerDTO customerDTO) {

        Customer customer=getCustomerById(id);
        //emaili kontrol et
        boolean isExistsByEmail=customerRepository.existsByEmail(customerDTO.getEmail());
        if(isExistsByEmail&& !customer.getEmail().equals(customerDTO.getEmail())){
            throw new ConflictException("Email already exists by: "+customerDTO.getEmail());
        }
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setLastName(customerDTO.getLastName());

        customerRepository.save(customer);
    }


    public Page<Customer> getAllCustomersWithPage(Pageable pageable) {

        return customerRepository.findAll(pageable);
    }
}
