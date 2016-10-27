package com.common.controller;

import java.security.Principal;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.common.dao.entity.incoming.Autor;
import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.incoming.Param;
import com.common.dao.entity.incoming.Recipient;
import com.common.dao.entity.queue.*;
import com.common.dao.entity.queue.Queue;
import com.common.service.workingThread.Pool;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.service.UserService;

@Controller
public class BasicController {
	@Autowired
	public UserService userService;


    //public Pool pool;
	ArrayList<String>chanel=new ArrayList<>();
	int i = 10000;
//	Logger logger = Logger.getLogger(String.valueOf(BasicController.class));
	@Autowired
	AmqpTemplate template;
	IncomingTask iTask = new IncomingTask();
	com.common.dao.entity.queue.Queue queue=new Queue();

	@RequestMapping("/emit/{a}")
	@ResponseBody
	String queue1(@PathVariable("a") Integer a) {

		long start = System.currentTimeMillis();
		for (int i = 0; i < a; i++) {
			byte[] data = SerializationUtils.serialize(generate());
			template.convertAndSend("queue1", data);
		}
		long finish = System.currentTimeMillis();
		long timeConsumedMillis = finish - start;

		return timeConsumedMillis + "";
	}

	public IncomingTask generate() {
		chanel.clear();
		Random r = new Random();
		int z = 5 + r.nextInt() % 2;
		int x = 1000000 + r.nextInt() % 1000000;
		ArrayList<Recipient> list = new ArrayList<Recipient>();
		 list.add(new Recipient("someName", 1000000 + r.nextInt() % 1000000 + ""));
		for(int q=0;q<z;q++) {
			list.add(new Recipient(1000000+r.nextInt()% 1000000 + "", new Param("name","someName")));
		}
		chanel.add("1");
		chanel.add("2");
		chanel.add("3");
		iTask.setChanel(chanel);
		iTask.setAutor(new Autor("login","password"));
		if(z%2==0) {
			iTask.setDepartureTime(new Date((new Date().getTime()+100*(120 + r.nextInt() % 120) )));
		}
		else{
			iTask.setDepartureTime(null);
		}
		iTask.setRelevant(new Date());
		iTask.setLanguage("ua");
		iTask.setRecipientList(list);
		iTask.setPriority(i);
		iTask.setEvent(111);
		iTask.setLoop(0);
		i--;
		return iTask;
	}
	
	@RequestMapping(value = { "/", "/log**" }, method = RequestMethod.GET)
	public ModelAndView start() {
		
		return new ModelAndView("/log");		
	
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(Map<String, Object> map,
			HttpServletRequest request) {
		map.put("User", userService.listContact(request
				.getUserPrincipal().getName()));
		return new ModelAndView("/index");
	}


}
