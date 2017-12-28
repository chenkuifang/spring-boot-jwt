package com.example.demo.service;

import com.example.demo.domain.Customer;

/**
 * @Description: 客户服务接口
 * @author QuiFar
 * @date 2017年9月15日 下午9:19:15
 * @version V1.0
 */
public interface CustomerService {
	Customer findByName(String name);

	void delete(Long id);

	void add(Customer customer);

	Iterable<Customer> customers();
}
