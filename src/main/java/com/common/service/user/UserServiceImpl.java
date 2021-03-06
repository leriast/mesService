package com.common.service.user;

//import com.common.dao.entity.JSONT;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.user.ContactsDictonary;
import com.common.dao.entity.user.Role;
import com.common.dao.entity.user.User;
import com.common.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    public User insertUser(User user) {return userDAO.insertUser(user);}

    @Override
    public Role getRoleById(int id) {
       return userDAO.getRoleById(id);
    }

    @Override
    public List<ContactsDictonary> getContactsDictonary() {return userDAO.getContactsDictonary();}

    @Override
    public void getContactsByType() {
        userDAO.getContactsByType();
    }

    @Override
    public User getUserByCompany(String companyName) {
        return userDAO.getUserByCompany(companyName);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public List<Role> getAllRoles() {
        return userDAO.getAllRoles();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByLogin(String name) {
        return userDAO.getUserByLogin(name);
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
    }
}
