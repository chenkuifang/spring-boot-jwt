package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.User;

/**
 * @Description: 用户信息仓库层
 * @author QuiFar
 * @date 2017年9月15日 下午9:24:13
 * @version V1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

	// @Query(value="select id,name,pwd from user where name = ?1 and pwd=?2")索引参数
	@Query(value = "select id,name,pwd from User u where u.name = :name and u.pwd = :pwd", nativeQuery = true) // 命名参数，推荐使用
	User getUser(@Param("name") String name, @Param("pwd") String pwd);
}
