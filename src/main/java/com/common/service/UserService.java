package com.common.service;

import com.common.dao.entity.message.Message1;
import com.common.dao.security.User;

import java.util.ArrayList;

public interface UserService {
	User listContact(String name);
	void insertMessage(Message1 m);
	void search();
	void insertMessageList(ArrayList<Message1>list);
}
