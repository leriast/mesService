package com.common.service.user;

import com.common.dao.entity.JSONT;
import com.common.dao.entity.message.Message;
import com.common.dao.entity.user.Role;
import com.common.dao.entity.user.User;

import java.util.ArrayList;

public interface UserService {
	User listContact(String name);
	void insertMessage(Message m);
	void search();
	void insertMessageList(ArrayList<Message>list);
	User insertUser(User user);
	Role getRoleById(int id);
	void getContactsDictonary();
	void getContactsByType();
	void insertJ(JSONT j);
	User getUserByCompany(String companyName);
}
