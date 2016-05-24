package com.hyc.Dao;

import org.hibernate.Query;

import com.hyc.Hibernate.User;

public class UserDao extends BaseDao{
	
	public boolean usernameIsExist(String username) {
		Query query = getSession().createQuery("FROM User WHERE username = ?");
		query.setString(0, username);
		if (query.list().isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean emailIsExist(String email) {
		Query query = getSession().createQuery("FROM User WHERE email = ?");
		query.setString(0, email);
		if (query.list().isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean usenameandpasswordIsExist(String username, String password) {
		Query query = getSession().createQuery("FROM User WHERE username = ? and password = ?");
		query.setString(0, username);
		query.setString(1, password);
		if (query.list().isEmpty())
			return false;
		else
			return true;
	}
	
	public void save(User user) {
		getSession().save(user);
		getTransaction().commit();
	}
}
