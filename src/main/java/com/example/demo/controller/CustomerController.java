package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;

/**
 * @Description: 客户信息控制层
 * @author QuiFar
 * @date 2017年9月9日 下午1:34:26
 * @version V1.0
 */
@Controller
@RequestMapping("/customer/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		Customer customer = new Customer();
		customer.setTel("15817864015");
		customer.setId(1L);
		customer.setName("quifar");
		customer.setEmail("314287696@qq.com");
		customer.setAddress("广西梧州市");
		customerService.add(customer);
		return "index";
	}

	/**
	 * 增加用户信息
	 * 
	 * @return
	 */
	@GetMapping("add")
	public ModelAndView add(@Valid Customer customer) {
		ModelAndView view = new ModelAndView("index");
		if (customer == null) {
			return view.addObject("ret", "姓名不能为空");
		}

		customerService.add(customer);
		return view.addObject("data", customerService.customers());
	}

	@GetMapping("edit")
	public String edit() {
		return "edit";
	}

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping("delete")
	public ModelAndView delete(@RequestParam Long id) {
		customerService.delete(id);
		return new ModelAndView("index", "data", customerService.customers());
	}

}
