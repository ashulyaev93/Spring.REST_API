package com.geekbrains.spring.lesson6.controllers;

import com.geekbrains.spring.lesson6.entities.Customer;
import com.geekbrains.spring.lesson6.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson6.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private Customer customer;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/add")
    public String addCustomer(
            Model model
    ) {
        model.addAttribute("customer", new Customer());
        return "customer_add_form";
    }

    @PostMapping("/add")
    public String addCustomer(
            @Valid @ModelAttribute Customer customer,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "customer_add_form";
        }
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Customer getOneCustomerById(@PathVariable Long id) {
        return customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " doesn't exists"));
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer c = customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("customer", c);
        return "customer_edit_form";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute Customer customer) {
        customerService.saveOrUpdate(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
        return "ok";
    }
}

