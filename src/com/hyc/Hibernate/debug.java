package com.hyc.Hibernate;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hyc.Dao.UserDao;

public class debug {

	@Test
	public void test() {
		UserDao userDao = new UserDao();
		User user = new User("���ڳ�", "1234", "1234");
		userDao.save(user);
	}

}
