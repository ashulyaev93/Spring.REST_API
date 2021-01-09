package com.geekbrains.spring.lesson6.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geekbrains.spring.lesson6.entities.Order;
import java.util.Date;
import java.util.List;

public class CustomerData {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String description;
    private List<Order> orders;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    public CustomerData() {
    }

    public CustomerData(Long id, String name, String email, String phone, String address, String description, List<Order> orders, Date createDate, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this. description = description;
        this.orders = orders;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone + '\'' +
                ", address=" + address + '\'' +
                ", description=" + description + '\'' +
                ", orders=" + orders +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}