package com.common.dao.user;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.user.Role;
import com.common.dao.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    User listContact(String name);

    void insertMessage(Message m);

    void insertMessageList(ArrayList<Message> list);

    void search();

    User insertUser(User user);

    Role getRoleById(int id);

    List getContactsDictonary();

    void getContactsByType();


    User getUserByCompany(String companyName);

    void addUser(User user);

    void deleteUser(User user);

    List<User> getAllUsers();

    List<Role> getAllRoles();

    User getUserById(int id);

    User getUserByLogin(String name);
}
