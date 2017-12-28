package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;

/**
 * @Description: 服务层接口实现类
 * @author QuiFar
 * @date 2017年9月15日 下午9:22:04
 * @version V1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User get(String name) {
		Iterable<User> users = userRepository.findAll();
		if (users == null) {
			return null;
		}
		// 只取第一个名字的实体
		for (User u : users) {
			if (u.getName().equals(name))
				return u;
		}

		return null;

	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);

	}

	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public Iterable<User> users() {
		return userRepository.findAll();
	}

	@Override
	public boolean validate(String name, String pwd) {
		User user = userRepository.getUser(name, pwd);
		if (user == null)
			return false;
		return true;
	}

}
