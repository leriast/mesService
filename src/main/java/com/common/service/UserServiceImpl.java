package com.common.service;

import com.common.dao.entity.UserDAO;
import com.common.dao.entity.message.Message1;
import com.common.dao.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User listContact(String name) {
        return userDAO.listContact(name);
    }


    @Transactional
    public void insertMessage(Message1 m) {
        userDAO.insertMessage(m);
    }

    @Transactional
    public void search() {
        userDAO.search();
    }

    @Override
    public void insertMessageList(ArrayList<Message1> list) {
        userDAO.insertMessageList(list);
    }

}
