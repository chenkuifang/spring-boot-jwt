package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试
 * @author QuiFar
 * @date 2017年12月27日 下午8:48:49
 * @version V1.0
 */
@RestController
@RequestMapping("/test")
public class RestTestController {
	@GetMapping("")
	public String test() {

		return "hello world";
	}

}
