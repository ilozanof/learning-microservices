package com.ilozanof.learning.microservices.customerService.model;

import java.util.Random;

/**
 * Customer DTO
 */
public class CustomerDTO {
    private String login;
    private String name;
    private Integer age;

    // returns a new CustomerDTC instance with random data
    public static CustomerDTO newRandomCustomer() {
        int customerNumber = new Random().nextInt(CustomerDef.MAX_CUSTOMERS);
        return new CustomerDTO("login_" + customerNumber,
                "John_" + customerNumber,
                customerNumber);
    }

    // NOTE:
    // This Bean is used in our Controllers, so we need a default Constructor without any parameters, since this
    // constructor will be called by Spring when it automatically populates a POST request with the contents of this
    // bean
    public CustomerDTO() {}

    // constructor
    public CustomerDTO(String _login, String _name, int _age) {
        login = _login;
        name = _name;
        age = _age;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


}
