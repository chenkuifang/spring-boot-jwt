package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Customer;

/**
 * @Description: 客户仓库信息
 * @author QuiFar
 * @date 2017年9月15日 下午9:24:13
 * @version V1.0
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
