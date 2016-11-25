package com.common.listener;

import com.common.service.user.UserService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 11/23/16.
 */
@EnableRabbit
@Component
public class RabbitJSON {

    @Autowired
    UserService userService;


}
