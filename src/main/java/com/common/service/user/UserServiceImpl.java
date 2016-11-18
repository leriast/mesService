package com.common.service.user;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.security.Role;
import com.common.dao.entity.security.User;
import com.common.dao.user.UserDAO;
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
    public void insertMessage(Message m) {
        userDAO.insertMessage(m);
    }

    @Transactional
    public void search() {
        userDAO.search();
    }

    @Override
    public void insertMessageList(ArrayList<Message> list) {
        userDAO.insertMessageList(list);
    }

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public Role getRoleById(int id) {
       return userDAO.getRoleBuId(id);
    }

}
