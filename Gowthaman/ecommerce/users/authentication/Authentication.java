package com.ecommerce.users.authentication;

import java.sql.SQLException;

import com.ecommerce.users.Person;

public interface Authentication {
	int loginUser(long mobileNumber,String password);
	<T extends Person>boolean registerUser(T user);
}
