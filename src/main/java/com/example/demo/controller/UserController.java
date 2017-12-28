package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

/**
 * @Description: 用户的控制层
 * @author QuiFar
 * @date 2017年9月9日 下午1:34:26
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String index(Map<String, Object> map) {
		map.put("data", userService.users());
		System.err.println("....." + userService.validate("quifar", "123"));
		return "index";
	}

	/**
	 * 增加用户信息
	 * 
	 * @return
	 */
	@GetMapping("add")
	public ModelAndView addUser(User user) {
		ModelAndView view = new ModelAndView("index");
		if (user == null) {
			return view.addObject("ret", "姓名不能为空");
		}

		userService.add(user);
		return view.addObject("data", userService.users());
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
		userService.delete(id);
		return new ModelAndView("index", "data", userService.users());
	}

}
