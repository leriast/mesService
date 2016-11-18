package com.common.service.user;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.security.Role;
import com.common.dao.entity.security.User;

import java.util.ArrayList;

public interface UserService {
	User listContact(String name);
	void insertMessage(Message m);
	void search();
	void insertMessageList(ArrayList<Message>list);
	void insertUser(User user);
	Role getRoleById(int id);
}
