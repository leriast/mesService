package com.common.service.user;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.user.ContactsDictonary;
import com.common.dao.entity.user.Role;
import com.common.dao.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
	User listContact(String name);
	void insertMessage(Message m);
	void search();
	void insertMessageList(ArrayList<Message>list);
	User insertUser(User user);
	Role getRoleById(int id);
	List<ContactsDictonary> getContactsDictonary();
	void getContactsByType();
	User getUserByCompany(String companyName);
	List<User> getAllUsers();
	List<Role> getAllRoles();
	User getUserById(int id);
	User getUserByLogin(String name);
}
