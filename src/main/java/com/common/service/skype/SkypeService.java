package com.common.service.skype;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.*;
import com.samczsun.skype4j.user.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by root on 12/5/16.
 */
@Service
public class SkypeService implements ISkypeService{
    @Autowired
    @Qualifier("skypeLogin")
    String skype_login;
    @Autowired
    @Qualifier("skypePassword")
    String skype_password;
    public SkypeService()  {

    }

    public void Start()  {
        System.out.println(skype_login+" / "+skype_password);
        Skype skype = new SkypeBuilder(skype_login, skype_password).withAllResources().build();
        try {

            skype.login();
            skype.subscribe();
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (NotParticipatingException e) {
            e.printStackTrace();
        }

        Contact juk = null;
        try {
            juk = skype.getOrLoadContact("sashko_.");
        } catch (ConnectionException e) {
            e.printStackTrace();
        }

//        for(Object o:skype.getAllContacts()){
//                Contact contact=(Contact) o;
//                System.out.println(((Contact) o).getUsername());
//            }

        if(!juk.isAuthorized()){
            try {
                juk.authorize();
                juk.sendRequest("qwe");


            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (NoSuchContactException e) {
                e.printStackTrace();
            }
        }

//        if(juk.isAuthorized()) {
            Chat chat = null;
            try {
                chat = juk.getPrivateConversation();
//                Message mes= Message.create();
//                mes.asPlaintext();
//                PlainText txt=PlainText.plain("hue moe");
//                mes.with(txt);
//                chat.sendMessage(mes);
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (ChatNotFoundException e) {
                e.printStackTrace();
            }
            try {
                chat.sendMessage("somthing wrong(....");
//                for(Object o:chat.getAllMessages()){
//                    Message m=(Message)o;
//                    System.out.println(m.asPlaintext());
//                }
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
       // }
            //System.out.println("username= "+juk.getUsername());
//

          /*  for (Object o : skype.getAllChats()) {
                Chat chat1 = (Chat) o;
                System.out.println("o=" + chat1.getIdentity());
            }*/

//        try {
//            skype.logout();
//        } catch (ConnectionException e) {
//            e.printStackTrace();
//        }


    }

}
