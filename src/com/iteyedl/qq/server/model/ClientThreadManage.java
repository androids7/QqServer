package com.iteyedl.qq.server.model;

import java.util.*;

public class ClientThreadManage {
	
	public static HashMap hm = new HashMap<String, ServerClientThread> ();
	
	//add a client communication thread in hm
	public static void addClientThread(String uId, ServerClientThread sct) {
		hm.put(uId, sct);
		hm.clear();
	}
	
	public static ServerClientThread getClientThread (String uId) {
		return (ServerClientThread)hm.get(uId);
	}
	
	//check and send back the exist online friends
	public static String getAllOnlineUserId() {
		//use iterator
		Iterator it = hm.keySet().iterator();
		String res = " ";
		while(it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}
