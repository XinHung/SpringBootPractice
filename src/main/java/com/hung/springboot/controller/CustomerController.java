package com.hung.springboot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hung.springboot.model.Customer;


@RestController
// 這個配置表示下面的網址對應都在/customers下
@RequestMapping(value = "/customers")
public class CustomerController {

    // 創建執行緒(thread)安全的Map
    static Map<Long, Customer> customers = Collections.synchronizedMap(new HashMap<Long, Customer>());

    // 處理"/customers/"的GET請求，查詢客戶列表
    @GetMapping("/")
    public List<Customer> getCustomerList() {
        List<Customer> r = new ArrayList<Customer>(customers.values());
        return r;
    }

    // 處理"/customers/"的POST請求，新增一個客戶
    @PostMapping("/")
    public String postCustomer(@ModelAttribute Customer customer) {
        customers.put(customer.getId(), customer);
        return "success";
    }

    // 處理"/customers/{id}"的GET請求，用來獲取URL中該id值的Customer資料
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customers.get(id);
    }

    // 處理"/customers/{id}"的PUT請求，用来更新Customer資料
    @PutMapping("/{id}")
    public String putCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        Customer u = customers.get(id);
        u.setName(customer.getName());
        u.setAge(customer.getAge());
        customers.put(id, u);
        return "success";
    }

    // 處理"/customers/{id}"的DELETE請求，用来删除Customer
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        customers.remove(id);
        return "success";
    }

}