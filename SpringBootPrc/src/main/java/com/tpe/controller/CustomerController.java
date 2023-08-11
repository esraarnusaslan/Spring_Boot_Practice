package com.tpe.controller;


import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //1-Spring boot u selamlama http://localhost:8080/customers/greet
    @GetMapping("/greet")
    public String greetSpringBook(){
        return "Hello Spring Boot";
    }


    //2-Customer oluşturma/kaydetme http://localhost:8080/customers/save
    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer customer){
        customerService.saveCustomer(customer);
        String message="Customer saved successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //3- Tum customer lari getirme http://localhost:8080/customers

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customerList=customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }


    //4- id ile customer getirme http://localhost:8080/customers/1

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        Customer customer=customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }


    //5- id ile customer getirme http://localhost:8080/customers/custom?id=1
//    @GetMapping("/custom")
//    public ResponseEntity<Customer> getCustomerById2(@RequestParam("id") Long id){
//        Customer customer=customerService.getCustomerById(id);
//        return ResponseEntity.ok(customer);
//    }

    // yukaridaki web service dto ile duzenlenirse

    @GetMapping("/custom")
    public ResponseEntity<CustomerDTO> getCustomerDTOById2(@RequestParam("id") Long id){
        CustomerDTO customerDTO=customerService.getCustomerDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }


    //6- id ile customer silme http://localhost:8080/customers/custom?id=1
    //customer is deleted successfully mesaji donsun
    @DeleteMapping("/custom")
    public ResponseEntity<String> deleteCustomerById(@RequestParam("id") Long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer is deleted successfully");
    }

    //7- id ile customer i updatete etme http://localhost:8080/customers/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO){

        customerService.updateCustomer(id,customerDTO);
        return ResponseEntity.ok("Customer is updated successfully");
    }

    //8-Tüm customerları page page getirme->http://localhost:8080/customers/page?page=1&size=3&sort=id&direction=ASC

    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getAllCustomersWithPage(@RequestParam("page") int page,
                                                                  @RequestParam("size") int size,
                                                                  @RequestParam("sort") String prop,
                                                                  @RequestParam("direction")Sort.Direction direction){

        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Customer>customerPage=customerService.getAllCustomersWithPage(pageable);
        return ResponseEntity.ok(customerPage);
    }















}
