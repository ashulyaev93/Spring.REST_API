package com.geekbrains.spring.lesson6.facade;

import com.geekbrains.spring.lesson6.data.CustomerData;
import com.geekbrains.spring.lesson6.data.ProductData;
import com.geekbrains.spring.lesson6.entities.Customer;
import com.geekbrains.spring.lesson6.entities.Product;
import com.geekbrains.spring.lesson6.populators.CustomerPopulator;
import com.geekbrains.spring.lesson6.populators.ProductPopulator;
import com.geekbrains.spring.lesson6.repositories.CustomerDataRepository;
import com.geekbrains.spring.lesson6.repositories.ProductDataRepository;
import com.geekbrains.spring.lesson6.repositories.ProductRepository;
import com.geekbrains.spring.lesson6.services.CustomerService;
import com.geekbrains.spring.lesson6.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFacade {

    private CustomerPopulator customerPopulator;
    private CustomerService customerService;
    private CustomerDataRepository customerDataRepository;

    public CustomerFacade(CustomerPopulator customerPopulator, CustomerService customerService, CustomerDataRepository customerDataRepository) {
        this.customerPopulator = customerPopulator;
        this.customerService = customerService;
        this.customerDataRepository= customerDataRepository;
    }

    public CustomerData getCustomerById(Long id){
        Customer customer = customerService.findById(id).get();
        CustomerData customerData = new CustomerData();
        customerData.setId(customer.getId());
        customerData.setName(customer.getName());
        customerData.setEmail(customer.getEmail());
        customerData.setPhone(customer.getPhone());
        customerData.setAddress(customer.getAddress());
        customerData.setDescription(customer.getDescription());
        customerData.setOrders(customer.getOrders());

        return customerData;
    }

    public List<CustomerData> getAllCustomerData(){
        return customerPopulator.convertAll(customerService.findAll());
    }

    public List<CustomerData> getAllCustomerDataFromRepository(){
        return customerDataRepository.findAllCustomerData();
    }
}

