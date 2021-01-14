package com.geekbrains.spring.lesson6.controllers.rest;

import com.geekbrains.spring.lesson6.data.CustomerData;
import com.geekbrains.spring.lesson6.data.ProductData;
import com.geekbrains.spring.lesson6.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson6.facade.CustomerFacade;
import com.geekbrains.spring.lesson6.facade.ProductFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class CrudController {

    private  ProductFacade productFacade;
    private CustomerFacade customerFacade;

    public CrudController(ProductFacade productFacade, CustomerFacade customerFacade) {
        this.productFacade = productFacade;
        this.customerFacade = customerFacade;
    }

    //get          /demo      получить все данные
    //get          /demo/1    получить по идентификатору 1
    //post         /demo      создать данные
    //put          /demo/1    обновить данные объекта по идентификатору 1
    //patch        /demo/1    обновить конкретные поля для объекта по идентификатору 1
    //delete       /demo/1    удалить ресурс по идентификатору 1

    @GetMapping
    public List<ProductData> getDemo() {
        return productFacade.getAllProductData();
    }

    @GetMapping("/{id}")
    public ProductData getDemo(
            @PathVariable("id") Long id
    ) {
        return productFacade
                .getAllProductData()
                .stream()
                .filter(productData -> productData.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    @PostMapping
    public String postDemo(
            @ModelAttribute String demo
    ) {
        return "post demo " + demo;
    }

    @PutMapping("/{id}")
    public String putDemo(
            @PathVariable("id") String id,
            @ModelAttribute String demo
    ) {
        return "put demo " + id;
    }

    @PatchMapping("/{id}")
    public String patchDemo(
            @PathVariable("id") String id,
            @ModelAttribute String demo
    ) {
        return "patch demo " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteDemo(
            @PathVariable("id") String id
    ) {
        return "remove demo " + id;
    }

//    @GetMapping
//    public List<CustomerData> getDemoCustomer() {
//        return customerFacade.getAllCustomerData();
//    }
//
//    @GetMapping("/demoCustomer/{id}")
//    public CustomerData getDemoCustomer(
//            @PathVariable("id") Long id
//    ) {
//        return customerFacade
//                .getAllCustomerData()
//                .stream()
//                .filter(customerData -> customerData.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " doesn't exists"));
//    }
//
//    @PostMapping
//    public String postDemoCustomer(
//            @ModelAttribute String demoCustomer
//    ) {
//        return "post demoCustomer " + demoCustomer;
//    }
//
//
//    @PutMapping("/demoCustomer/{id}")
//    public String putDemoCustomer(
//            @PathVariable("id") String id,
//            @ModelAttribute String demo
//    ) {
//        return "put demoCustomer " + id;
//    }
//
//    @PatchMapping("/demoCustomer/{id}")
//    public String patchDemoCustomer(
//            @PathVariable("id") String id,
//            @ModelAttribute String demoCustomer
//    ) {
//        return "patch demoCustomer " + id;
//    }
//
//    @DeleteMapping("/demoCustomer/{id}")
//    public String deleteDemoCustomer(
//            @PathVariable("id") String id
//    ) {
//        return "remove demoCustomer " + id;
//    }
}
