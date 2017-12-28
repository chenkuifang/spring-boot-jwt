package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;

/**
 * @Description: 服务层接口实现类
 * @author QuiFar
 * @date 2017年9月15日 下午9:22:04
 * @version V1.0
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findByName(String name) {
		Iterable<Customer> customers = customerRepository.findAll();
		if (customers == null) {
			return null;
		}
		// 只取第一个名字的实体
		for (Customer u : customers) {
			if (u.getName().equals(name))
				return u;
		}

		return null;

	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);

	}

	@Override
	public void add(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Iterable<Customer> customers() {
		return customerRepository.findAll();
	}

}
