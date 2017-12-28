package com.example.demo.service;

import com.example.demo.domain.User;

/**
 * @Description: 用户服务接口
 * @author QuiFar
 * @date 2017年9月15日 下午9:19:15
 * @version V1.0
 */
public interface UserService {
	User get(String name);

	void delete(Long id);

	void add(User user);

	Iterable<User> users();

	boolean validate(String name, String pwd);
}
