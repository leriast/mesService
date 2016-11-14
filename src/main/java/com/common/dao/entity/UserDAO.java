package com.common.dao.entity;

import com.common.dao.entity.message.Message1;
import com.common.dao.security.User;

import java.util.ArrayList;

public interface UserDAO {
	public User listContact(String name);

	public void insertMessage(Message1 m);
	void insertMessageList(ArrayList<Message1> list);
	public void search();
}
