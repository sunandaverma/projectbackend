package com.niit.dao;

import com.niit.model.User;

public interface UserDao {
	
	public void insertOrUpdateUser(User user);
	public User getUser(String email);

}
