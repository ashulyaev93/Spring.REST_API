package com.geekbrains.spring.lesson6.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.geekbrains.spring.lesson6.data.CustomerData;
import com.geekbrains.spring.lesson6.entities.Customer;
import com.geekbrains.spring.lesson6.entities.views.CustomerView;
import com.geekbrains.spring.lesson6.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson6.facade.CustomerFacade;
import com.geekbrains.spring.lesson6.services.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers/api/v1")
public class CustomerRestController {

    private CustomerService customerService;
    private CustomerFacade customerFacade;

    public CustomerRestController(CustomerService customerService, CustomerFacade customerFacade) {
        this.customerService = customerService;
        this.customerFacade = customerFacade;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Customer> customersToXml() {
        return customerService.findAll();
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> customersToJson() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerById(
            @PathVariable("id") Long id
    ) {
        return customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer id=" + id + " not found"));
    }

    @PostMapping
    public Customer createCustomer(
            @RequestBody Customer customer
    ) {
        customer.setId(null);
        return customerService.saveOrUpdate(customer);
    }

    @PutMapping("/{id}")
    public Customer putCustomer(
            @PathVariable("id") Long id,
            @RequestBody Customer customer
    ) {
        return customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(
            @PathVariable("id") Long id
    ) {
        System.out.println("id = " + id);
        customerService.remove(id);
    }

    @GetMapping(value = "/jsonData/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerData customerDataToJson(
            @PathVariable Long id
    ){
        return customerFacade.getCustomerById(id);
    }

    //here new
    @GetMapping(value = "/jsonData")
    @ResponseBody
    public List<CustomerData> customerDataToJson(){
        return customerFacade.getAllCustomerDataFromRepository();
    }

    @GetMapping(value = "/id", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(CustomerView.Id.class)
    @ResponseBody
    public List<Customer> customerIdToJson(){
        return customerService.findAll();
    }

    @GetMapping(value = "/idName", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(CustomerView.IdName.class)
    @ResponseBody
    public List<Customer> customerIdNameToJson(){
        return customerService.findAll();
    }

    @GetMapping(value = "/fullPlain", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(CustomerView.FullCustomer.class)
    @ResponseBody
    public List<Customer> productFullPlainToJson(){
        return customerService.findAll();
    }

    @GetMapping(value = "/responseEntityToJson", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(CustomerView.FullCustomer.class)
    @ResponseBody
    public ResponseEntity responseEntityToJson(){
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Stackoverflow");
        map.put("name", "Alex");
        map.put("customers", customerService.findAll());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
//
//
//
//    @GetMapping(value = "/entityWithPaging", params = { "page", "size" })
//    @JsonView(ProductView.FullProduct.class)
//    public ResponseEntity entityWithPaging(
//            Pageable pageable,
//            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(value = "size", required = false, defaultValue = "10") int size
//    ){
//        PageRequest pageRequest1 = PageRequest.of(page, size);
//
//        System.out.println(pageable);
//        System.out.println(pageRequest1);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("products", productService.findAll());
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }
}