package com.tpe.dto;

import com.tpe.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {


    @NotNull(message = "First name can not be null")
    @NotBlank(message = "First name can not be space")
    @NotEmpty
    @Size(min = 2,max = 50)
    private String name;

    @NotNull(message = "First name can not be null")
    @NotBlank(message = "First name can not be space")
    @NotEmpty
    @Size(min = 2,max = 50)
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    private String phone;


    public CustomerDTO(Customer customer){
        this.name= customer.getName();
        this.lastName= customer.getLastName();
        this.email= customer.getEmail();
        this.phone= customer.getPhone();
    }








}
