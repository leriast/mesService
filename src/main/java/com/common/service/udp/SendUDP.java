package com.common.service.udp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by root on 12/5/16.
 */
@Component
public class SendUDP {
    @Autowired
    @Qualifier("loggerHost")
    String loggerHost;
    @Autowired
    @Qualifier("loggerPort")
    String loggerPort;
    private String log;

    public SendUDP(String log) {
        this.log = log;
        sendLog(this.log);
    }

    public SendUDP(){}



    public void sendLog(String log){
        try {

            String host ="172.27.172.208";
            int port = 999;

            byte[] message = log.getBytes();

            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName(host);

            // Initialize a datagram packet with data and address

            DatagramPacket packet = new DatagramPacket(message, message.length,
                    address, port);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
