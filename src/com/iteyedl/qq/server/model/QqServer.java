/**
 * QqServer, listening, and waiting for qq client to connect.
 */

package com.iteyedl.qq.server.model;

import java.net.*;
import java.io.*;
import java.util.*;

import com.iteyedl.qq.common.Message;
import com.iteyedl.qq.common.User;

public class QqServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public QqServer() {
		try {
			//listen at port 9999
			System.out.println("Server is listening at port 9999");
			ServerSocket ss = new ServerSocket(9999);
			//Stock and wait for client to connect
			while(true) {
				Socket s = ss.accept();
				//receive package from client
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				System.out.println("Get use id: " + u.getUserId() +" password: " + u.getPassword());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPassword().equals("123456")){
					
					//reply a login success message
					m.setMesType("1");
					oos.writeObject(m);
					
					//initial a thread to communicate that user
					ServerClientThread sct = new ServerClientThread(s);
					ClientThreadManage.addClientThread(u.getUserId(), sct);
					sct.start();
					
					//then inform all the other online users about all online users list
					sct.notifyOther(u.getUserId());
					
				} else {
					m.setMesType("2");
					oos.writeObject(m);
					s.close();	//close socket
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
		}		
	}

	public void shut() {
		this.shut();
	}
	
}
