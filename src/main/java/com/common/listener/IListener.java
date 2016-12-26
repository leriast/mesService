package com.common.listener;

import com.common.dao.entity.user.User;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

/**
 * Created by root on 12/14/16.
 */
@Service
public interface IListener {
    void init(JSONArray arr, User user,String filename);
}
