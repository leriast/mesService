package com.common.service.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by root on 12/5/16.
 */
public class UDPClient extends Thread {
    int clientPort = 999;

    int buffer_size = 1024;

    public void run(){
        System.out.println(true);


        byte buffer[] = new byte[buffer_size];
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(clientPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            try {
                ds.receive(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //    System.out.println("udpClient"+new String(p.getData(), 0, p.getLength())+"/"+new Date());
        }
    }

}
