package com.common.dao.user;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.security.Role;
import com.common.dao.entity.security.User;

import java.util.ArrayList;

public interface UserDAO {
	User listContact(String name);
    void insertMessage(Message m);
	void insertMessageList(ArrayList<Message> list);
	void search();

	void insertUser(User user);
	Role getRoleBuId(int id);
}
