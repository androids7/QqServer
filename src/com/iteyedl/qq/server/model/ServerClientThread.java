/**
 * function: a communication thread between server and one client
 */
package com.iteyedl.qq.server.model;

import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import com.iteyedl.qq.common.Message;
import com.iteyedl.qq.common.MessageType;

public class ServerClientThread extends Thread{
	Socket s;
	
	public ServerClientThread(Socket s) {
		//grant s with socket
		this.s = s;
	}
	
	//let this thread to inform other users
	public void notifyOther(String iam) {
		//get all online users' thread
		HashMap hm = ClientThreadManage.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext()) {
			Message m = new Message();
			m.setContent(iam);
			m.setMesType(MessageType.message_response_onlineFriend);
			//get onlien user id
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManage.getClientThread(onLineUserId)
						.s.getOutputStream());
				m.setReceiver(onLineUserId);
				oos.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		
		while(true) {
			//this thread can receive message from client
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
//				System.out.println(m.getSender() +" sends a message to " + m.getReceiver() + " : " + m.getContent());
				
				//check message  type (from user client), then proceed
				if(m.getMesType().equals(MessageType.message_common_message)) {
					//codes to accomplish message forwarding
					//to get receiver's thread
					ServerClientThread sc = ClientThreadManage.getClientThread(m.getReceiver());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMesType().equals(MessageType.message_get_onlineFriend)) {
					//send back user client the online friends 
					String res = ClientThreadManage.getAllOnlineUserId();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_response_onlineFriend);
					m2.setContent(res);
					m2.setReceiver(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
